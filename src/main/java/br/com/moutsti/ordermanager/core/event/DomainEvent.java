package br.com.moutsti.ordermanager.core.event;

public interface DomainEvent {

	String getRelationId();

	default String getEventType() {
		return this.getClass().getSimpleName();
	}

}
