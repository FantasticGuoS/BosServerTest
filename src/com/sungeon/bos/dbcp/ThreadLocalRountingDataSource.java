package com.sungeon.bos.dbcp;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class ThreadLocalRountingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return DataSourceTypeManager.get();
	}

}
