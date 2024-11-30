package br.com.moutsti.ordermanager.order;

import br.com.moutsti.ordermanager.support.TestSupport;
import br.com.six2six.fixturefactory.Fixture;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static br.com.moutsti.ordermanager.support.FixtureTemplates.VALID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class OrderQueryTest extends TestSupport {

	private static final int PAGE = 0;

	private static final int SIZE = 2;

	private OrderQuery query;

	@Mock
	private OrderRepository repository;

	@Override
	public void init() {
		this.query = new OrderQuery(repository);
	}

	@Test
	void should_retrieve_all_orders() {
		List<Order> orders = Fixture.from(Order.class).gimme(3, VALID.name());
		Page<Order> page = new PageImpl<>(orders);

		when(repository.findAll(any(Pageable.class))).thenReturn(page);

		assertEquals(page, query.findAll(PageRequest.of(PAGE, SIZE)));

		InOrder inOrder = this.inOrder(repository);
		inOrder.verify(repository).findAll(any(Pageable.class));
		inOrder.verifyNoMoreInteractions();
	}

	@Test
	void should_retrieve_all_orders_by_status_pending() {
		List<Order> orders = Fixture.from(Order.class).gimme(3, VALID.name());

		when(repository.findByStatus(OrderStatusEnum.PENDING.name())).thenReturn(orders);

		assertEquals(orders, query.findByStatusPending());

		InOrder inOrder = this.inOrder(repository);
		inOrder.verify(repository).findByStatus(OrderStatusEnum.PENDING.name());
		inOrder.verifyNoMoreInteractions();
	}

}
