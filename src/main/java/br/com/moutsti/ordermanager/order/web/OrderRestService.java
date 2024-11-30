package br.com.moutsti.ordermanager.order.web;

import br.com.moutsti.ordermanager.order.Order;
import br.com.moutsti.ordermanager.order.OrderQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/orders")
class OrderRestService {

	private static final String CREATED_AT = "createdAt";

	private final OrderQuery query;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	Page<Order> findAll(@PageableDefault(sort = { CREATED_AT }, direction = DESC, value = 20) final Pageable pageable) {
		return query.findAll(pageable);
	}

}
