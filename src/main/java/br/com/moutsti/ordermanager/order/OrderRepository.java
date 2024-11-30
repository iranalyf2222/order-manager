package br.com.moutsti.ordermanager.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import static br.com.moutsti.ordermanager.order.OrderQueries.CHECK_DUPLICATION;

interface OrderRepository extends JpaRepository<Order, String> {

	@Query(value = CHECK_DUPLICATION, nativeQuery = true)
	boolean isDuplicated(String externalId);

	List<Order> findByStatus(String status);

}
