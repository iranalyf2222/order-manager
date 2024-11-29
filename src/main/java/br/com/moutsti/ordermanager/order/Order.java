package br.com.moutsti.ordermanager.order;

import br.com.moutsti.ordermanager.core.auditing.MoutsTiAbstractEntity;
import br.com.moutsti.ordermanager.order.integration.OrderCreatedEvent;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

import static br.com.moutsti.ordermanager.core.MoutsTiIdGenerator.generateId;
import static br.com.moutsti.ordermanager.order.OrderStatusEnum.RECEIVED;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Entity
@EqualsAndHashCode
@Table(name = "orders")
@Builder(access = PRIVATE)
@ToString(exclude = "itens")
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class Order extends MoutsTiAbstractEntity<Order> {

	@Id
	private String id;

	@Column(name = "external_id")
	private String externalId;

	private BigDecimal amount;

	private String status;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private List<OrderItem> items;

	static Order of(OrderCreatedEvent event) {
		String orderId = generateId();
		return Order.builder().id(orderId).externalId(event.getExternalId()).status(RECEIVED.name())
				.items(event.getItems().stream()
						.map(item -> OrderItem.of(generateId(), item.getDescription(), item.getPaymentValue(), orderId))
						.toList())
				.build();
	}

}
