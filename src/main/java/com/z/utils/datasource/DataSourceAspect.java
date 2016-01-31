package com.z.utils.datasource;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

public class DataSourceAspect {
	private static final Logger LOG = Logger.getLogger(DataSourceAspect.class);
	
	@Resource(name = "dataSource")
	private DynamicDataSource dataSourceHolder;
	/**
	 * 在开始前动态选择数据源(datasource)
	 * @param point 连接切点,其实也就是要在point对象上加一些业务逻辑
	 */
	public Object doAroundMethod(ProceedingJoinPoint pjp) throws Throwable {
		Object response = null;
		String method = pjp.getSignature().getName();
		boolean hasBinded = false;
		try {
			// 以下策略可以抽成一个类
			hasBinded = dataSourceHolder.hasBindedDataSourse();
			if (!hasBinded) {
				if (method.startsWith("query") || method.startsWith("select") || method.startsWith("find") || method.startsWith("total")
						|| method.startsWith("get") || method.startsWith("count") || method.startsWith("search")) {
					dataSourceHolder.markSlave();
				} else {
					dataSourceHolder.markMaster();
				}
			}
			response = pjp.proceed();
		} finally {
			if (!hasBinded) {
				dataSourceHolder.markRemove();
			}
		}
		return response;
	}
}
