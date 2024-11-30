package br.com.moutsti.ordermanager.order.integration;

import br.com.moutsti.ordermanager.order.OrderCommand;
import br.com.moutsti.ordermanager.support.LocalConsumerTestSupport;
import br.com.six2six.fixturefactory.Fixture;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifierSender;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.TestPropertySource;

import static br.com.moutsti.ordermanager.support.FixtureTemplates.VALID;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;

@TestPropertySource(properties = "spring.cloud.stream.bindings.onOrderCreatedEvent-in-0.destination=queueorder")
class OrderConsumerTest extends LocalConsumerTestSupport {

	@MockBean
	private OrderCommand command;

	@Autowired
	private MessageVerifierSender<Message<?>> sender;

	@Test
	void consume() {
		OrderCreatedEvent event = Fixture.from(OrderCreatedEvent.class).gimme(VALID.name());

		sender.send(MessageBuilder.withPayload(event).build(), "queueorder");

		InOrder inOrder = inOrder(command);
		inOrder.verify(command, times(1)).create(event);
		inOrder.verifyNoMoreInteractions();
	}

}
