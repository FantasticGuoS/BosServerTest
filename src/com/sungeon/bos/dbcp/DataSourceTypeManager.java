package com.sungeon.bos.dbcp;

public class DataSourceTypeManager {

	private static final ThreadLocal<DataSourceType> dataSourceTypes = new ThreadLocal<DataSourceType>() {
		@Override
		protected DataSourceType initialValue() {
			return DataSourceType.DEFAULT;
		}
	};

	public static DataSourceType get() {
		return dataSourceTypes.get();
	}

	public static void set(DataSourceType dataSourceType) {
		dataSourceTypes.set(dataSourceType);
	}

	public static void reset() {
		dataSourceTypes.set(DataSourceType.DEFAULT);
	}

}
