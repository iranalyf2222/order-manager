package br.com.moutsti.ordermanager.order.integration;

import br.com.moutsti.ordermanager.core.exception.MoutsTiRuntimeException;
import br.com.moutsti.ordermanager.order.Order;
import br.com.moutsti.ordermanager.order.OrderCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderConsumer {

	private final OrderCommand command;

	@Bean
	public Consumer<OrderCreatedEvent> onOrderCreatedEvent() {
		return event -> {
			try {
				Order order = command.create(event);
				log.info("Order processed successfully: {}", order);
			}
			catch (MoutsTiRuntimeException ex) {
				log.error("Create Order error (MoutsTiRuntimeException): {}", ex.getMessage());
			}
			catch (Exception ex) {
				log.error("Create Order error: {}", getStackTrace(ex), ex);
			}
		};
	}

}
