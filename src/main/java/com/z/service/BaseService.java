package com.z.service;

import java.util.List;
import java.util.Map;

import com.z.utils.Page;

public interface BaseService<T> {
	
	/**
	 * 根据主键Id，查询单个对象
	 * @param id  id
	 * @return
	 */
	public T getById(String id) ; 
	
	/**
	 * 根据复合条件查询对象
	 * @return
	 */
	public List<T> getList(Map<String, Object> paramMap, Page page);
	
	/*******
	 * 根据父类ID,查询子类集合
	 * @param ownerId 父类ID
	 * @param page  分页
	 * @return
	 */
	public List<T> getListByParent(String paraentId, Page page);
	

	/**
	 * 添加定义
	 * @param definition
	 */
	public void save(T object);

	/**
	 * 批量添加多个定义
	 * @param definition
	 */
	public void save(List<T> objects);
	
	/**
	 * 修改定义
	 * @param definition
	 */
	public void update(T object);
	
	
	/**
	 * 删除定义
	 * @param id
	 * @param definition
	 */
	public void delete(String id); 
	
	/**
	 * 根据一组ID，删除定义
	 * @param ids，主键集合,以逗号分隔
	 * @param definition
	 */
	public void deleteAll(String ids); 
	
	/********
	 * 根据主表ID,删除子表信息
	 * @param ownerId
	 */
	public void deleteByParentId(String parentId);
	
	
	/**********
	 * 查询系统中当前对象的总条目数
	 * @return
	 * @throws BasicException
	 */
	public int totalNum(Map<String,Object> paramMap); 
	
	
	/**********
	 * 查询系统中当前对象的总条目数
	 * @return
	 * @throws BasicException
	 */
	public int totalNum(); 
	
	/**********
	 * 根据父类ID, 查询系统中子类对象的总条目数
	 * @param parentId
	 * @return
	 */
	public int totalNum(String parentId);
}
