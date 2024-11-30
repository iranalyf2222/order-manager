package br.com.moutsti.ordermanager.templates;

import br.com.moutsti.ordermanager.order.integration.OrderCreatedEvent;
import br.com.moutsti.ordermanager.order.integration.OrderItemCreatedEvent;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import static br.com.moutsti.ordermanager.core.MoutsTiIdGenerator.generateId;
import static br.com.moutsti.ordermanager.support.FixtureTemplates.VALID;

public class OrderCreatedEventTemplateLoader implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(OrderCreatedEvent.class).addTemplate(VALID.name(), new Rule() {
			{
				add("externalId", generateId());
				add("items", has(3).of(OrderItemCreatedEvent.class, VALID.name()));
			}
		});
	}

}
