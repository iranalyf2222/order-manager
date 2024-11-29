package br.com.moutsti.ordermanager.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class OrderQueries {

	static final String CHECK_DUPLICATION = "SELECT CASE WHEN COUNT(*) > 0 THEN 'true' ELSE 'false' END From orders "
			+ "WHERE external_id = :externalId";

}
