package br.com.moutsti.ordermanager.order.integration;

import lombok.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class OrderCreatedEvent {

	private String externalId;

	private List<OrderItemCreatedEvent> items;

}
