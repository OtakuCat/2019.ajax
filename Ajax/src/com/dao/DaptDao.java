package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.impl.DeptImpl;
import com.pojo.Dapt;
import com.utils.BaseDao;
import com.utils.ConnectionUtils;

public class DaptDao extends BaseDao<Dapt> implements DeptImpl {

	@Override
	public int insert(Dapt t) {
		
		return 0;
	}

	@Override
	public int update(Dapt t) {
		
		return 0;
	}

	@Override
	public int delete(Integer id) {
		
		return 0;
	}

	@Override
	public Dapt findById(Integer id) {
		Connection conn = ConnectionUtils.getConn();
		String sql="select * from dept where did=?";
		PreparedStatement ps=null;
		ResultSet eq=null;
		Dapt dapt=null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			eq = ps.executeQuery();
			if(eq.next()) {
				dapt=(Dapt) TableToClass(eq);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionUtils.close(conn, ps, eq);
		}
		return dapt;
	}

	@Override
	public List<Dapt> findAll() {
		Connection conn = ConnectionUtils.getConn();
		String sql="select * from dept";
		PreparedStatement ps=null;
		ResultSet eq=null;
		List<Dapt> list=new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			eq = ps.executeQuery();
			while(eq.next()) {
				Dapt dapt=(Dapt) TableToClass(eq);
				list.add(dapt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionUtils.close(conn, ps, eq);
		}
		return list;
	}
	
	

	@Override
	public <T> List<T> find(Class<T> class1, String sql, Object... args) {
		
		return null;
	}

	@Override
	public Dapt TableToClass(ResultSet rs) {
		Dapt dapt=new Dapt();
		try {
			dapt.setDid(rs.getInt("did"));
			dapt.setDname(rs.getString("dname"));
			dapt.setDadder(rs.getString("daddr"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dapt;
	}

}
