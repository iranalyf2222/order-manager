package br.com.moutsti.ordermanager.core.event;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MoutsTiEventPublisher implements ApplicationEventPublisher {

	private final ApplicationEventPublisher publisher;

	@Override
	public void publishEvent(ApplicationEvent applicationEvent) {
		publisher.publishEvent(applicationEvent);
	}

	@Override
	public void publishEvent(Object event) {
		publisher.publishEvent(event);
	}

}
