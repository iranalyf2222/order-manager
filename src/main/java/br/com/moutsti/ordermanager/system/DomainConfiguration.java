package br.com.moutsti.ordermanager.system;

import br.com.moutsti.ordermanager.core.auditing.AuditingDateTimeProvider;
import br.com.moutsti.ordermanager.core.auditing.SpringSecurityAuditor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.Clock;
import java.time.ZoneId;

@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
@EntityScan(basePackages = { "br.com.moutsti.ordermanager" })
public class DomainConfiguration {

	private static final String TIMEZONE = "GMT-3";

	@Bean
	public DateTimeProvider dateTimeProvider(Clock systemClock) {
		return new AuditingDateTimeProvider(systemClock);
	}

	@Bean
	public Clock systemClock() {
		return Clock.system(ZoneId.of(TIMEZONE));
	}

	@Bean
	public AuditorAware<String> auditorProvider() {
		return new SpringSecurityAuditor();
	}

}