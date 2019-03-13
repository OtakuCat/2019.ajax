package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.impl.EmpImpl;
import com.pojo.Emp;
import com.pojo.EmpDname;
import com.utils.BaseDao;
import com.utils.ConnectionUtils;
import com.utils.EmptyUtils;

public class EmpDao extends BaseDao<Emp> implements EmpImpl {
	/*
	 * @Override public int insert(Emp t) { String sql =
	 * "insert into emp values(null,?,?,?,?,?)"; return executeUpdate(sql,
	 * t.getEname(), t.getEage(), t.getEsex(),new Date(t.getEhireDate().getTime()),
	 * t.getDid()); }
	 */
	@Override
	public int insert(Emp t) {
		String sql = "insert into emp values(null,?,?,?,?,?)";
		java.sql.Date date = new java.sql.Date(t.getEhireDate().getTime());
		Object[] params = { t.getEname(), t.getEage(), t.getEsex(), date, t.getDid() };
		return this.executeUpdate(sql, params);
	}

	@Override
	public int update(Emp t) {
		String sql = "update emp set ename=?,eage=?,esex=?,ehiredate=?,did=? where eid=?";
		return executeUpdate(sql, t.getEname(), t.getEage(), t.getEsex(), t.getEhireDate(), t.getDid(), t.getEid());
	}

	@Override
	public int delete(Integer id) {
		String sql = "delete from emp where eid=?";
		return executeUpdate(sql, id);
	}

	@Override
	public Emp findById(Integer id) {
		Connection conn = ConnectionUtils.getConn();
		String sql = "select * from emp where eid=?";
		PreparedStatement ps = null;
		ResultSet eq = null;
		Emp emp = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			eq = ps.executeQuery();
			if (eq.next()) {
				emp = (Emp) TableToClass(eq);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtils.close(conn, ps, eq);
		}
		return emp;
	}

	@Override
	public List<Emp> findAll() {
		Connection conn = ConnectionUtils.getConn();
		String sql = "select * from emp";
		PreparedStatement ps = null;
		ResultSet eq = null;
		List<Emp> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			eq = ps.executeQuery();
			while (eq.next()) {
				Emp emp = (Emp) TableToClass(eq);
				list.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtils.close(conn, ps, eq);
		}
		return list;
	}

	@Override
	public <T> List<T> find(Class<T> class1, String sql, Object... args) {

		return null;
	}

	@Override
	public int getRows() {
		Connection conn = ConnectionUtils.getConn();
		String sql = "select count(*) from emp";
		PreparedStatement ps = null;
		ResultSet eq = null;
		int num = 0;
		try {
			ps = conn.prepareStatement(sql);
			eq = ps.executeQuery();
			if (eq.next()) {
				num = eq.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtils.close(conn, ps, eq);
		}
		return num;
	}

	public int getRows(Emp empquery) {
		Connection conn = ConnectionUtils.getConn();
		String sql = "select count(*) from emp where 1=1";
		if (empquery.getDid() != -1) {// ��ѯ�����в���
			sql += " and did=" + empquery.getDid();
		}
		if (EmptyUtils.isNotEmpty(empquery.getEname())) {
			sql += " and ename like '%" + empquery.getEname() + "%'";
		}
		PreparedStatement ps = null;
		ResultSet eq = null;
		int num = 0;
		try {
			ps = conn.prepareStatement(sql);
			eq = ps.executeQuery();
			if (eq.next()) {
				num = eq.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtils.close(conn, ps, eq);
		}
		return num;
	}

	@Override
	public List<EmpDname> findList(int page, int pageSize) {
		Connection conn = ConnectionUtils.getConn();
		String sql = "SELECT e.* ,d.dname FROM emp e,dept d WHERE d.`did`=e.`did`  ORDER BY e.eid LIMIT ?,? ";
		PreparedStatement ps = null;
		ResultSet eq = null;
		List<EmpDname> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page - 1) * pageSize);
			ps.setInt(2, pageSize);
			eq = ps.executeQuery();
			while (eq.next()) {
				EmpDname emp = (EmpDname) setToClass(eq);
				list.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtils.close(conn, ps, eq);
		}
		return list;
	}

	public List<?> findList(int page, int pageSize, Emp empquery) {
		Connection conn = ConnectionUtils.getConn();
		String sql = "select e.*,d.dname from emp e,dept d where e.did=d.did";
		if (empquery.getDid() != -1) {// ��ѯ�����в���
			sql += " and e.did=" + empquery.getDid();
		}
		if (EmptyUtils.isNotEmpty(empquery.getEname())) {
			sql += " and e.ename like '%" + empquery.getEname() + "%'";
		}
		sql += " order by e.eid limit ?,?";
		PreparedStatement ps = null;
		ResultSet eq = null;
		List<EmpDname> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page - 1) * pageSize);
			ps.setInt(2, pageSize);
			eq = ps.executeQuery();
			while (eq.next()) {
				EmpDname emp = (EmpDname) setToClass(eq);
				list.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtils.close(conn, ps, eq);
		}
		return list;
	}

	@Override
	public Emp TableToClass(ResultSet rs) {
		Emp emp = new Emp();
		try {
			emp.setEid(rs.getInt("eid"));
			emp.setEname(rs.getString("ename"));
			emp.setEage(rs.getInt("eage"));
			emp.setEsex(rs.getInt("esex"));
			emp.setEhireDate(rs.getDate("ehireDate"));
			emp.setDid(rs.getInt("did"));
		} catch (SQLException e) {

		}
		return emp;
	}

	public EmpDname setToClass(ResultSet rs) {
		EmpDname emp = new EmpDname();
		try {
			emp.setEid(rs.getInt("eid"));
			emp.setEname(rs.getString("ename"));
			emp.setEage(rs.getInt("eage"));
			emp.setEsex(rs.getInt("esex"));
			emp.setEhireDate(rs.getDate("ehireDate"));
			emp.setDid(rs.getInt("did"));
			emp.setDname(rs.getString("dname"));
		} catch (SQLException e) {

		}
		return emp;
	}
}
