package br.com.moutsti.ordermanager.order.web;

import br.com.moutsti.ordermanager.order.Order;
import br.com.moutsti.ordermanager.order.OrderQuery;
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

class OrderRestServiceTest extends TestSupport {

	private static final int PAGE = 0;

	private static final int SIZE = 2;

	private OrderRestService restService;

	@Mock
	private OrderQuery query;

	@Override
	public void init() {
		this.restService = new OrderRestService(query);
	}

	@Test
	void should_retrieve_all() {
		List<Order> orders = Fixture.from(Order.class).gimme(3, VALID.name());
		Page<Order> page = new PageImpl<>(orders);

		when(query.findAll(any(Pageable.class))).thenReturn(page);

		assertEquals(page, restService.findAll(PageRequest.of(PAGE, SIZE)));

		InOrder inOrder = this.inOrder(query);
		inOrder.verify(query).findAll(any(Pageable.class));
		inOrder.verifyNoMoreInteractions();
	}

}
