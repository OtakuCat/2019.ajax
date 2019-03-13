package com.utils;

import java.util.List;
/**
 * ÿһ��Dao��Ҫʵ������ӿ�
 * @author Administrator
 *
 * @param <T>
 */
public interface Dao<T> {
	public int insert(T t);
	
	public int update(T t);
	
	public int delete(Integer id);
	
	public T findById(Integer id);
	
	public List<T> findAll();
	
	public <T> List<T> find(Class<T> class1, String sql, Object... args);
}
