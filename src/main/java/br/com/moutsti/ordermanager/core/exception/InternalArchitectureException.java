package br.com.moutsti.ordermanager.core.exception;

import br.com.moutsti.ordermanager.core.exception.model.MessageType;
import org.springframework.http.HttpStatus;

import java.util.List;

public class InternalArchitectureException extends MoutsTiRuntimeException {

	public InternalArchitectureException(List<String> notifications) {
		super(MessageType.Internal_Architecture_Error, notifications, HttpStatus.INTERNAL_SERVER_ERROR.value());
	}

	public InternalArchitectureException(List<String> notifications, Throwable cause) {
		super(MessageType.Internal_Architecture_Error, notifications, cause, HttpStatus.INTERNAL_SERVER_ERROR.value());
	}

}
