package com.utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 所有Dao一个基类
 * 
 * @author Administrator
 *
 */
public abstract class BaseDao<T> {

	/**
	 * 通过执行insert update   delete
	 * @param sql
	 * @param params
	 * @return
	 */
	public int executeUpdate(String sql, Object... params) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		int result = 0;
		try {
			conn = ConnectionUtils.getConn();
			pstmt = conn.prepareStatement(sql);
			if (params != null && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i + 1, params[i]);
				}
			}
			result = pstmt.executeUpdate();
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			ConnectionUtils.close(conn, pstmt);
		}
		return result;
	}

	public abstract T TableToClass(ResultSet rs);

	public <T> List<T> find(Class<T> class1, String sql, Object... args) {
		// TODO Auto-generated method stub
		T object = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSetMetaData resultSetMetaData = null;
		List<T> list = new ArrayList<T>();
		try {
			// 1.获取数据库链接对象
			connection = ConnectionUtils.getConn();
			// 2.获取preparedStatement对象。
			// 1>获取
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			// 2>给占位符传值
			if(args!=null)
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			// 1.得到ResultSet对象
			resultSet = preparedStatement.executeQuery();
			// 2.得到ResultSetMetaData对象
			resultSetMetaData = (ResultSetMetaData) resultSet.getMetaData();
			// 4.处理结果集
			while (resultSet.next()) {
				// 3.创建一个Map<String,Object>对象 键=列别名，值：列的值。
				Map<String, Object> map = new HashMap<>();
				for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
					String columnname = resultSetMetaData.getColumnLabel(i + 1);
					Object obj = resultSet.getObject(i + 1);
					map.put(columnname, obj);
				}
				// 5.遍历map利用反射给Class对象赋值
				if (map.size() > 0) {
					object = class1.newInstance();
					for (Map.Entry<String, Object> entry : map.entrySet()) {
						String fieldName = entry.getKey();
						Object value = entry.getValue();
						Field field = class1.getDeclaredField(fieldName);
						field.setAccessible(true);
						field.set(object, value);
					}
				}
				list.add(object);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtils.close(connection, preparedStatement, resultSet);
			return list;
		}

	}

}
