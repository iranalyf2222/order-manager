package br.com.moutsti.ordermanager.order;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderQuery {

	private final OrderRepository repository;

	public Page<Order> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public List<Order> findByStatusPending() {
		return repository.findByStatus(OrderStatusEnum.PENDING.name());
	}

}
