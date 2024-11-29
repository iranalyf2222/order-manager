package br.com.moutsti.ordermanager.order;

import br.com.moutsti.ordermanager.core.MoutsTiMessageSource;
import br.com.moutsti.ordermanager.core.exception.ConflictException;
import br.com.moutsti.ordermanager.order.integration.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class OrderCommand {

	private final OrderRepository repository;

	private final MoutsTiMessageSource messageSource;

	public Order create(OrderCreatedEvent event) {
		if (isDuplicated(event.getExternalId())) {
			throw new ConflictException(
					messageSource.getMessage("order.error.conflict", new Object[] { event.getExternalId() }), true);
		}
		return repository.save(Order.of(event));
	}

	private boolean isDuplicated(String externalId) {
		return repository.isDuplicated(externalId);
	}

}
