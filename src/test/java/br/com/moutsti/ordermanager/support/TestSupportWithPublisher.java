package br.com.moutsti.ordermanager.support;

import br.com.moutsti.ordermanager.core.BeanUtil;
import br.com.moutsti.ordermanager.core.MoutsTiIdGenerator;
import br.com.moutsti.ordermanager.core.event.MoutsTiEventPublisher;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import lombok.Getter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

public abstract class TestSupportWithPublisher extends TestSupport {

	@Getter
	private MockedStatic<MoutsTiIdGenerator> idGeneratorMock;

	@Mock
	@Getter
	private MoutsTiEventPublisher eventPublisher;

	@Getter
	private MockedStatic<BeanUtil> beanUtilMock;

	@BeforeAll
	public static void setUp() {
		FixtureFactoryLoader.loadTemplates("br.com.moutsti.ordermanager.templates");
	}

	@BeforeEach
	public void setUpTest() {
		MockitoAnnotations.openMocks(this);
		beanUtilMock = Mockito.mockStatic(BeanUtil.class);
		beanUtilMock.when(() -> BeanUtil.getBean(MoutsTiEventPublisher.class)).thenReturn(eventPublisher);

		idGeneratorMock = Mockito.mockStatic(MoutsTiIdGenerator.class);
		idGeneratorMock.when(MoutsTiIdGenerator::generateId).thenReturn(UUID.randomUUID().toString());
		this.init();
	}

}
