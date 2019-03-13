package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.EmpDao;
import com.impl.EmpImpl;
import com.pojo.Emp;

/**
 * Servlet implementation class EmpAjaxServlet
 */
@Servlet("/EmpAjaxServlet")
public class EmpAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpAjaxServlet() {
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("delete".equals(method)) {
			this.domyDelete(request, response);
		}else if("updateName".equals(method)) {
			this.doDelete(request, response);
		}else {
			this.doList(request, response);
		}
	}
	public void doUpdata(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		int eid = Integer.parseInt(request.getParameter("eid"));
		String ename=request.getParameter("ename");
		EmpImpl empDao = new EmpDao();
		Emp emp=empDao.findById(eid);
		emp.setEname(ename);
		empDao.update(emp);
		PrintWriter out=response.getWriter();
		out.print("updateOk");
		out.close();
	}
	public void doList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
	}
	public void domyDelete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		// 删除一个员工编号 根据eid=5
		try {
			System.out.println("****************");
			int eid = Integer.parseInt(request.getParameter("eid"));
			EmpImpl empDao = new EmpDao();
			int i=empDao.delete(eid);
			System.out.println(i);
			//向客户端返回结果 delete ok
			out.write("deleteok");//向ajax请求返回处理结果 
		} catch (Exception ex) {
			ex.printStackTrace();
			out.write("deleteerror");
		}
			out.close();
	}
		/*PrintWriter out =response.getWriter();
		try {
		int eid=Integer.parseInt(request.getParameter("eid"));
		System.out.println(eid);
		EmpImpl empimpl=new EmpDao();
		empimpl.delete(eid);
		out.write("delete");
	} catch (NumberFormatException e) {
		e.printStackTrace();
		out.write("upDelete");
	}
		out.close();*/
	}
