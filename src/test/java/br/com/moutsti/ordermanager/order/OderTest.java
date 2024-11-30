package br.com.moutsti.ordermanager.order;

import br.com.moutsti.ordermanager.order.integration.OrderCreatedEvent;
import br.com.moutsti.ordermanager.support.TestSupport;
import br.com.six2six.fixturefactory.Fixture;
import org.junit.jupiter.api.Test;

import static br.com.moutsti.ordermanager.core.MoutsTiIdGenerator.generateId;
import static br.com.moutsti.ordermanager.order.OrderStatusEnum.PENDING;
import static br.com.moutsti.ordermanager.support.FixtureTemplates.VALID;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OderTest extends TestSupport {

	private Order order;

	private OrderCreatedEvent orderCreatedEvent;

	@Override
	public void init() {
		this.order = Fixture.from(Order.class).gimme(VALID.name());
		this.orderCreatedEvent = Fixture.from(OrderCreatedEvent.class).gimme(VALID.name());
	}

	@Test
	void should_instantiate() {
		assertEquals(expected().getExternalId(), Order.of(orderCreatedEvent).getExternalId());
		assertEquals(expected().getItems().size(), Order.of(orderCreatedEvent).getItems().size());
	}

	@Test
	void should_calculate() {
		this.order.calculate();
		assertEquals(OrderStatusEnum.CALCULATED.name(), this.order.getStatus());
	}

	private Order expected() {
		String orderId = generateId();
		return Order.builder().id(orderId).externalId(this.orderCreatedEvent.getExternalId()).status(PENDING.name())
				.items(this.orderCreatedEvent.getItems().stream()
						.map(item -> OrderItem.of(generateId(), item.getDescription(), item.getPaymentValue(), orderId))
						.toList())
				.build();
	}

}
