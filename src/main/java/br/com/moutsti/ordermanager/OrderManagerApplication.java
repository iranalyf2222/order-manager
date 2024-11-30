package br.com.moutsti.ordermanager;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT5M")
@SpringBootApplication(scanBasePackages = "br.com.moutsti.ordermanager")
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class OrderManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderManagerApplication.class, args);
	}

}
