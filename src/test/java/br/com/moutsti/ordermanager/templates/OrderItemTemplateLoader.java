package br.com.moutsti.ordermanager.templates;

import br.com.moutsti.ordermanager.order.OrderItem;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.math.BigDecimal;
import java.time.Instant;

import static br.com.moutsti.ordermanager.core.MoutsTiIdGenerator.generateId;
import static br.com.moutsti.ordermanager.support.FixtureTemplates.VALID;

public class OrderItemTemplateLoader implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(OrderItem.class).addTemplate(VALID.name(), new Rule() {
			{
				add("id", generateId());
				add("description", random("Teclado", "Mouse", "Monitor 4K", "Microfone HyperX"));
				add("paymentValue", random(BigDecimal.class, range(500, 10000)));
				add("createdAt", Instant.now());
				add("updatedAt", Instant.now());
				add("orderId", generateId());
			}
		});
	}

}
