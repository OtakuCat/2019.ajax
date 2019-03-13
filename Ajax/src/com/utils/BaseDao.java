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
 * ����Daoһ������
 * 
 * @author Administrator
 *
 */
public abstract class BaseDao<T> {

	/**
	 * ͨ��ִ��insert update   delete
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
			// 1.��ȡ���ݿ����Ӷ���
			connection = ConnectionUtils.getConn();
			// 2.��ȡpreparedStatement����
			// 1>��ȡ
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			// 2>��ռλ����ֵ
			if(args!=null)
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			// 1.�õ�ResultSet����
			resultSet = preparedStatement.executeQuery();
			// 2.�õ�ResultSetMetaData����
			resultSetMetaData = (ResultSetMetaData) resultSet.getMetaData();
			// 4.��������
			while (resultSet.next()) {
				// 3.����һ��Map<String,Object>���� ��=�б�����ֵ���е�ֵ��
				Map<String, Object> map = new HashMap<>();
				for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
					String columnname = resultSetMetaData.getColumnLabel(i + 1);
					Object obj = resultSet.getObject(i + 1);
					map.put(columnname, obj);
				}
				// 5.����map���÷����Class����ֵ
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
