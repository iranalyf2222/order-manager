package br.com.moutsti.ordermanager.system;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.redis.spring.RedisLockProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
public class ShedLockRedisConfig {

	@Bean
	public LockProvider lockProvider(RedisConnectionFactory redisConnectionFactory) {
		return new RedisLockProvider(redisConnectionFactory);
	}

}
