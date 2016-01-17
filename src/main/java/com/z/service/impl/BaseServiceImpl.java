package com.z.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.z.dao.MyBatisDao;
import com.z.service.BaseService;
import com.z.utils.Page;

public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	@Resource
	protected MyBatisDao myBatisDao;
	
	public void setMyBatisDAO(MyBatisDao myBatisDAO) {
		this.myBatisDao = myBatisDAO;
	}

	/**
	 * 根据主键Id，查询单个对象
	 * @param id  id
	 * @return
	 */
	public T getById(String id) {
		return myBatisDao.getById(getEntityName() + ".getById", id);
	}

	/**
	 * 根据复合条件查询对象
	 * @return
	 */
	public List<T> getList(Map<String,Object> paramMap, Page page){
		return myBatisDao.getList(getEntityName() + ".getList", paramMap, page);
	}
	

	/*******
	 * 根据父类ID,查询子类集合
	 * @param ownerId 父类ID
	 * @param page  分页
	 * @return
	 */
	public List<T> getListByParent(String paraentId, Page page){
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentId", paraentId);
		return myBatisDao.getList(getEntityName() + ".getListByParent", paramMap, page);
	}
	

	/**
	 * 添加定义
	 * @param definition
	 * @throws BasicException 异常信息
	 */
	public void save(T object){
		myBatisDao.save(getEntityName() + ".save", object);
	}

	/**
	 * 批量添加多个定义
	 * @param definition
	 * @throws BasicException 异常信息
	 */
	public void save(List<T> objects) {
		for(T t: objects){
			this.save(t);
		}
	}
	
	/**
	 * 修改定义
	 * @param definition
	 * @throws BasicException 异常信息
	 */
	public void update(T object) {
		myBatisDao.update(getEntityName() + ".update", object);
	}
	
	/**
	 * 删除定义
	 * @param id
	 * @param definition
	 * @throws BasicException 异常信息
	 */
	public void delete(String id) {
		myBatisDao.delete(getEntityName() + ".delete", id);
	}
	
	/**
	 * 根据一组ID，删除定义
	 * @param id，主键集合,以逗号分隔
	 * @param definition
	 * @throws BasicException 异常信息
	 */
	public void deleteAll(String ids) {
		myBatisDao.delete(getEntityName() + ".deleteAll", ids.split(","));
	}
	
	/********
	 * 根据主表ID,删除子表信息
	 * @param ownerId
	 * @throws BasicException
	 */
	public void deleteByParentId(String parentId) {
		myBatisDao.delete(this.getEntityName() + ".deleteByParent", parentId);
	}
	
	
	/**********
	 * 查询系统中当前对象的总条目数
	 * @return
	 * @throws BasicException
	 */
	public int totalNum(Map<String,Object> paramMap){
		return myBatisDao.getCount(getEntityName() + ".getCount", paramMap);
	}
	
	public int totalNum() {
		return myBatisDao.getCount(getEntityName() + ".getCount", null);
	}
	
	public int totalNum(String parentId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("propertyId", parentId);
		return myBatisDao.getCount(getEntityName() + ".getCount", map);
	}
	
	public abstract String getEntityName();
	
}
