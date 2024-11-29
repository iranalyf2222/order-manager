package br.com.moutsti.ordermanager.order;

import br.com.moutsti.ordermanager.core.auditing.MoutsTiAbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Entity
@EqualsAndHashCode
@Table(name = "order_item")
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PACKAGE, staticName = "of")
public class OrderItem extends MoutsTiAbstractEntity<OrderItem> {

	@Id
	private String id;

	private String description;

	@Column(name = "payment_value")
	private BigDecimal paymentValue;

	@Column(name = "order_id")
	private String orderId;

}
