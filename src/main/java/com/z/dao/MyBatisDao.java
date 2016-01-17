package com.z.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.z.utils.Page;

@Service
public interface MyBatisDao {

	public boolean save(String key, Object parameter);

	public boolean update(String key, Object parameter);

	public boolean delete(String key, Object parameter);

	public <T> T getById(String key, Object parameter);
	
	public Integer getCount(String key, Map<String, Object> map);

	public <T> List<T> getList(String key, Map<String, Object> map, Page ipage);
	
	public <T> T getObject(String key, Map<String, Object> map);

}
