package br.com.moutsti.ordermanager.order;

import br.com.moutsti.ordermanager.core.MoutsTiMessageSource;
import br.com.moutsti.ordermanager.core.exception.ConflictException;
import br.com.moutsti.ordermanager.order.integration.OrderCreatedEvent;
import br.com.moutsti.ordermanager.support.TestSupport;
import br.com.six2six.fixturefactory.Fixture;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import static br.com.moutsti.ordermanager.support.FixtureTemplates.VALID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

class OrderCommandTest extends TestSupport {

	private Order order;

	private OrderCreatedEvent orderCreatedEvent;

	private OrderCommand command;

	@Mock
	private OrderRepository repository;

	@Mock
	private MoutsTiMessageSource messageSource;

	@Override
	public void init() {
		this.order = Fixture.from(Order.class).gimme(VALID.name());
		this.orderCreatedEvent = Fixture.from(OrderCreatedEvent.class).gimme(VALID.name());
		this.command = new OrderCommand(repository, messageSource);
	}

	@Test
	void should_create_order_successful() {
		when(repository.save(any())).thenReturn(this.order);

		assertEquals(order, command.create(this.orderCreatedEvent));

		InOrder inOrder = this.inOrder(repository);
		inOrder.verify(repository).save(any());
		inOrder.verifyNoMoreInteractions();
	}

	@Test
	void should_throw_conflict_exception_when_create_order() {
		when(repository.isDuplicated(this.orderCreatedEvent.getExternalId())).thenReturn(true);

		assertThrows(ConflictException.class, () -> command.create(this.orderCreatedEvent));

		InOrder inOrder = this.inOrder(repository);
		inOrder.verify(repository).isDuplicated(this.orderCreatedEvent.getExternalId());
		inOrder.verifyNoMoreInteractions();
	}

}
