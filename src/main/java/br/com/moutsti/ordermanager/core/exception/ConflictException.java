package br.com.moutsti.ordermanager.core.exception;

import br.com.moutsti.ordermanager.core.exception.model.MessageType;
import org.springframework.http.HttpStatus;

import java.util.List;

import static java.util.Collections.singletonList;

public class ConflictException extends MoutsTiRuntimeException {

	public ConflictException(String notification) {
		super(MessageType.Conflict_Error, singletonList(notification), HttpStatus.CONFLICT.value());
	}

	public ConflictException(String notification, boolean enableSuppression) {
		super(MessageType.Conflict_Error, singletonList(notification), HttpStatus.CONFLICT.value(), enableSuppression);
	}

	public ConflictException(List<String> notifications) {
		super(MessageType.Conflict_Error, notifications, HttpStatus.CONFLICT.value());
	}

	public ConflictException(List<String> notifications, boolean enableSuppression) {
		super(MessageType.Conflict_Error, notifications, HttpStatus.CONFLICT.value(), enableSuppression);
	}

	public ConflictException(List<String> notifications, Throwable cause) {
		super(MessageType.Conflict_Error, notifications, cause, HttpStatus.CONFLICT.value());
	}

}
