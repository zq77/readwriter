package com.z.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.z.dao.MyBatisDao;
import com.z.utils.Page;

public class MyBatisDaoImpl extends SqlSessionDaoSupport implements MyBatisDao {

	public boolean save(String key, Object parameter) {
		this.getSqlSession().insert(key, parameter);
		return true;
	}

	public boolean update(String key, Object parameter) {
		this.getSqlSession().update(key, parameter);
		return true;
	}

	public boolean delete(String key, Object parameter) {
		this.getSqlSession().delete(key, parameter);
		return true;
	}

	@SuppressWarnings("unchecked")
	public <T> T getById(String key, Object parameter) {
		return (T) this.getSqlSession().selectOne(key, parameter);
	}

	public Integer getCount(String key, Map<String, Object> map) {
		return (Integer) this.getSqlSession().selectOne(key, map);
	}

	public <T> List<T> getList(String key, Map<String, Object> map, Page page) {
		if (page != null) {
			map = (map == null) ? new HashMap<String, Object>(): map;
			map.put("page", page.getNowStartNumber());
			map.put("pageCount", page.getSizePerPage());
		}
		return this.getSqlSession().selectList(key, map);
	}

	@SuppressWarnings("unchecked")
	public <T> T getObject(String key, Map<String, Object> map) {
		return (T) this.getSqlSession().selectOne(key, map);
	}

}
