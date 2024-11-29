package br.com.moutsti.ordermanager.core.auditing;

import br.com.moutsti.ordermanager.core.event.DomainEvent;
import br.com.moutsti.ordermanager.core.event.MoutsTiEventPublisher;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;

import static br.com.moutsti.ordermanager.core.BeanUtil.getBean;

@Data
@SuperBuilder
@MappedSuperclass
@EqualsAndHashCode
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class MoutsTiAbstractEntity<T> implements Serializable {

	@CreatedDate
	@Column(name = "created_at", updatable = false)
	private Instant createdAt;

	@LastModifiedDate
	@Column(name = "updated_at")
	private Instant updatedAt;

	public T registerEvent(DomainEvent event) {
		getBean(MoutsTiEventPublisher.class).publishEvent(event);
		return (T) this;
	}

}
