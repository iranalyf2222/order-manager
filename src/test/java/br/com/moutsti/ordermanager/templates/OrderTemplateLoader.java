package br.com.moutsti.ordermanager.templates;

import br.com.moutsti.ordermanager.order.Order;
import br.com.moutsti.ordermanager.order.OrderItem;
import br.com.moutsti.ordermanager.order.OrderStatusEnum;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.math.BigDecimal;
import java.time.Instant;

import static br.com.moutsti.ordermanager.core.MoutsTiIdGenerator.generateId;
import static br.com.moutsti.ordermanager.support.FixtureTemplates.VALID;

public class OrderTemplateLoader implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Order.class).addTemplate(VALID.name(), new Rule() {
			{
				add("id", generateId());
				add("externalId", generateId());
				add("status", OrderStatusEnum.PENDING.name());
				add("items", has(3).of(OrderItem.class, VALID.name()));
				add("createdAt", Instant.now());
				add("updatedAt", Instant.now());
			}
		});
	}

}
