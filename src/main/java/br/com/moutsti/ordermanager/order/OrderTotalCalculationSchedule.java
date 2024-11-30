package br.com.moutsti.ordermanager.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
class OrderTotalCalculationSchedule {

	private final OrderQuery orderQuery;

	@Scheduled(cron = "0 0/2 * * * *", zone = "GMT-3")
	@SchedulerLock(name = "order-total-calculation-schedule", lockAtMostFor = "PT5M", lockAtLeastFor = "PT5M")
	@Transactional
	public void process() {
		List<Order> pendingOrders = orderQuery.findByStatusPending();
		log.info("Processing calculation of {} pending Orders", pendingOrders.size());
		pendingOrders.forEach(Order::calculate);
		log.info("Process of calculating pending orders completed successfully");
	}

}
