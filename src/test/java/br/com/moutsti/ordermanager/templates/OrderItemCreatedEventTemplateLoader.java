package br.com.moutsti.ordermanager.templates;

import br.com.moutsti.ordermanager.order.integration.OrderItemCreatedEvent;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.math.BigDecimal;

import static br.com.moutsti.ordermanager.support.FixtureTemplates.VALID;

public class OrderItemCreatedEventTemplateLoader implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(OrderItemCreatedEvent.class).addTemplate(VALID.name(), new Rule() {
			{
				add("description", random("Teclado", "Mouse", "Monitor 4K", "Microfone HyperX"));
				add("paymentValue", random(BigDecimal.class, range(500, 10000)));
			}
		});
	}

}
