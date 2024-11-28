package br.com.moutsti.ordermanager.order;

import br.com.moutsti.ordermanager.core.auditing.MoutsTiAbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Entity
@EqualsAndHashCode
@Table(name = "orders")
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

}
