package br.com.moutsti.ordermanager.support;

import br.com.moutsti.ordermanager.core.BeanUtil;
import br.com.moutsti.ordermanager.core.event.DomainEvent;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.cloud.function.context.config.ContextFunctionCatalogAutoConfiguration;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.cloud.stream.config.BindingServiceConfiguration;
import org.springframework.cloud.stream.function.FunctionConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.config.GlobalChannelInterceptorInitializer;
import org.springframework.messaging.Message;
import org.springframework.test.annotation.DirtiesContext;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;
import static org.springframework.messaging.support.MessageBuilder.withPayload;

@DirtiesContext
@AutoConfigureMessageVerifier
@SpringBootTest(webEnvironment = NONE)
@Import({ TestChannelBinderConfiguration.class, ContextFunctionCatalogAutoConfiguration.class,
		BindingServiceConfiguration.class, FunctionConfiguration.class, GlobalChannelInterceptorInitializer.class,

		BeanUtil.class, ContractTestObjectMapper.class })
public abstract class LocalConsumerTestSupport {

	@BeforeAll
	static void setup() {
		FixtureFactoryLoader.loadTemplates("br.com.moutsti.ordermanager.templates");

	}

	protected Message<DomainEvent> buildMessage(DomainEvent event) {
		return withPayload(event).setHeader("eventType", event.getEventType()).build();
	}

}
