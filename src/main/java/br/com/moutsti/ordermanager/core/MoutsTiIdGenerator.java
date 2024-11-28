package br.com.moutsti.ordermanager.core;

import java.util.UUID;

public class MoutsTiIdGenerator {

	public static String generateId() {
		return UUID.randomUUID().toString();
	}

}
