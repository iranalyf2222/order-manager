package br.com.moutsti.ordermanager.core.auditing;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class SpringSecurityAuditor implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		String user = "UNKNOWN";
		return Optional.of(user);
	}

}