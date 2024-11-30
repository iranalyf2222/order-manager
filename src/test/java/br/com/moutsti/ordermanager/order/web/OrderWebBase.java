package br.com.moutsti.ordermanager.order.web;

import br.com.moutsti.ordermanager.order.Order;
import br.com.moutsti.ordermanager.order.OrderQuery;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.util.List;

import static br.com.moutsti.ordermanager.support.FixtureTemplates.VALID;
import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { OrderWebBase.Config.class })
public class OrderWebBase {

	@MockBean
	private OrderQuery query;

	@BeforeAll
	public static void setUp() {
		FixtureFactoryLoader.loadTemplates("br.com.moutsti.ordermanager.templates");
	}

	@BeforeEach
	public void setup() {
		Pageable pageable = PageRequest.of(0, 10);
		List<Order> orders = Fixture.from(Order.class).gimme(1, VALID.name());
		PageImpl<Order> page = new PageImpl<>(orders, pageable, 1);
		when(query.findAll(any(Pageable.class))).thenReturn(page);

		StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(new OrderRestService(query))
				.setMessageConverters(hal()).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver());

		RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
	}

	private MappingJackson2HttpMessageConverter hal() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		MappingJackson2HttpMessageConverter halConverter = new MappingJackson2HttpMessageConverter();
		halConverter.setObjectMapper(objectMapper);
		halConverter.setSupportedMediaTypes(singletonList(MediaType.APPLICATION_JSON));
		return halConverter;
	}

	@Configuration
	static class Config {

	}

}
