package com.z.utils.datasource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	// 线程本地环境
	private static final ThreadLocal<String> dataSourceHolder = new ThreadLocal<String>();
	// 可选取slave的keys
	private List<String> slaveDataSourcesKeys;
	// 可选取master的keys
	private List<String> masterDataSourcesKeys;
	private Map<String, DataSource> slavetDataSources;
	private Map<String, DataSource> masterDataSources;
	private static final Logger LOG = Logger.getLogger(DynamicDataSource.class);

	@Override
	protected Object determineCurrentLookupKey() {
		return dataSourceHolder.get();
	}

	@Override
	public void afterPropertiesSet() {
		// 数据检验和合并
		LOG.info("开始向spring routing datasource 提供数据源选取");
		Map<Object, Object> allDataSources = new HashMap<Object, Object>();
		allDataSources.putAll(masterDataSources);
		if (slavetDataSources != null) {
			allDataSources.putAll(slavetDataSources);
		}
		super.setTargetDataSources(allDataSources);
		super.afterPropertiesSet();
		LOG.info("已经完成向spring routing datasource 提供数据源选取");
	}

	/**
	 * 注册slave datasource
	 * 
	 * @param slavetDataSources
	 */
	public void setSlavetDataSources(Map<String, DataSource> slavetDataSources) {
		if (slavetDataSources == null || slavetDataSources.size() == 0) {
			return;
		}
		LOG.info("提供可选取slave数据源：{}" + slavetDataSources.keySet());
		this.slavetDataSources = slavetDataSources;
		slaveDataSourcesKeys = new ArrayList<String>();
		for (Entry<String, DataSource> entry : slavetDataSources.entrySet()) {
			slaveDataSourcesKeys.add(entry.getKey());
		}
	}

	/**
	 * 注册master datasource
	 * 
	 * @param masterDataSources
	 */
	public void setMasterDataSources(Map<String, DataSource> masterDataSources) {
		if (masterDataSources == null) {
			throw new IllegalArgumentException(
					"Property 'masterDataSources' is required");
		}
		LOG.info("提供可选取master数据源：{}" + masterDataSources.keySet());
		this.masterDataSources = masterDataSources;
		this.masterDataSourcesKeys = new ArrayList<String>();
		for (Entry<String, DataSource> entry : masterDataSources.entrySet()) {
			masterDataSourcesKeys.add(entry.getKey());
		}
	}

	/**
	 * 标记选取slave数据源
	 */
	public void markSlave() {
		if (dataSourceHolder.get() != null) {
			// 从现在的策略来看,不允许标记两次,直接抛异常,优于早发现问题
			throw new IllegalArgumentException("当前已有选取数据源,不允许覆盖,已选数据源key:" + dataSourceHolder.get());
		}
		String dataSourceKey = selectFromSlave();
		setDataSource(dataSourceKey);
	}

	/**
	 * 标记选取master数据源
	 */
	public void markMaster() {
		if (dataSourceHolder.get() != null) {
			// 从现在的策略来看,不允许标记两次,直接抛异常,优于早发现问题
			throw new IllegalArgumentException("当前已有选取数据源,不允许覆盖,已选数据源key:" + dataSourceHolder.get());
		}
		String dataSourceKey = selectFromMaster();
		setDataSource(dataSourceKey);
	}

	/**
	 * 删除己标记选取的数据源
	 */
	public void markRemove() {
		dataSourceHolder.remove();
	}

	/**
	 * 是否已经绑定datasource 绑定：true 没绑定：false
	 * 
	 * @return
	 */
	public boolean hasBindedDataSourse() {
		boolean hasBinded = dataSourceHolder.get() != null;
		return hasBinded;
	}

	private String selectFromSlave() {
		if (slavetDataSources == null) {
			LOG.info("提供可选取slave数据源：{},将自动切换从主master选取数据源" + slavetDataSources);
			return selectFromMaster();
		} else {
			return slaveDataSourcesKeys.get(RandomUtils.nextInt(slaveDataSourcesKeys.size()));
		}
	}

	private String selectFromMaster() {
		String dataSourceKey = masterDataSourcesKeys.get(RandomUtils
				.nextInt(masterDataSourcesKeys.size()));
		return dataSourceKey;
	}

	private void setDataSource(String dataSourceKey) {
		LOG.info("dataSourceHolder set datasource keys:{}" + dataSourceKey);
		dataSourceHolder.set(dataSourceKey);
	}
}
