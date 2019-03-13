package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DaptDao;
import com.dao.EmpDao;
import com.impl.EmpImpl;
import com.pojo.Dapt;
import com.pojo.Emp;
import com.utils.EmptyUtils;
import com.utils.PageBean;

/**
 * 
 * Servlet implementation class EmpServlet
 */
@WebServlet("/EmpServlet")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmpServlet() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");

		if ("insert".equals(method)) {
			this.doInsert(request, response);
		} else if ("findDept".equals(method)) {
			this.doFindDept(request, response);
		} else if("findemp".equals(method)) {
			this.doFind(request, response);
		}else if ("update".equals(method)) {
			this.doUpdate(request, response);
		} else if("delete".equals(method)) {
			this.doDelete(request, response);
		}else{
			this.doList(request, response);
		}
	}

	public void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int did = -1;
		String ss = request.getParameter("did");
		if (EmptyUtils.isNotEmpty(ss)) {
			did = Integer.parseInt(ss);
		}
		String ename = request.getParameter("inquiry");
		Emp empquery = new Emp();
		empquery.setDid(did);
		empquery.setEname(ename);

		EmpDao dao = new EmpDao();
		PageBean pb = new PageBean();
		String page = request.getParameter("page");

		if (EmptyUtils.isEmpty(page)) {
			page = "1";
		}

		pb.setRows(dao.getRows(empquery));
		int pageNow = Integer.parseInt(page);
		pb.setPageNow(pageNow);

		
		pb.setList(dao.findList(pb.getPageNow(), pb.getPageSize(),empquery));

		DaptDao deptDao = new DaptDao();
		request.setAttribute("dlist", deptDao.findAll());

		request.setAttribute("empquery", empquery);
		request.setAttribute("pb", pb);

		request.getRequestDispatcher("Employee/EmpList.jsp").forward(request, response);
	}
	protected void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String ename=request.getParameter("ename");
	    int eage=Integer.parseInt(request.getParameter("eage"));
	    int esex=Integer.parseInt(request.getParameter("esex"));
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    Date date = null;
		try {
			date = sdf.parse(request.getParameter("ehiredate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    int did=Integer.parseInt(request.getParameter("did"));
	    int eid=Integer.parseInt(request.getParameter("eid"));
		//将提交的数据封装成一个type对象
	    Emp emp=new Emp();
	    emp.setEname(ename);
	    emp.setEage(eage);
	    emp.setEsex(esex);
	    emp.setEhireDate(date);
	    emp.setDid(did);
	    emp.setEid(eid);
		//2.调
	    EmpImpl empDao=new EmpDao();
		int r= empDao.update(emp);
		//3.转
	   if(r!=0){
		   //增加成功
		   //得到表中的所有记录[emp对象]
		   PrintWriter out=response.getWriter();
		   out.print("<script>alert('修改成功!');window.location.href='EmpServlet';</script>");
		   out.close();
	   }else{
		   PrintWriter out=response.getWriter();
		   out.print("<script>alert('修改出错!');window.location.href='EmpServlet';</script>");
		   out.close();
	   }
	}
	
	public void doInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ename = request.getParameter("ename");
		String age = request.getParameter("eage");
		String sex = request.getParameter("esex");
		String id = request.getParameter("did");
		String date = request.getParameter("ehiredate");

		int eage = Integer.parseInt(age);
		int esex = Integer.parseInt(sex);
		int did = Integer.parseInt(id);
		SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
		Date ehiredate = null;
		try {
			ehiredate = spf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Emp emp = new Emp();
		emp.setEname(ename);
		emp.setEage(eage);
		emp.setEsex(esex);
		emp.setEhireDate(ehiredate);
		emp.setDid(did);
		EmpImpl dao = new EmpDao();
		int t=dao.insert(emp);
		System.out.println(t);
		response.sendRedirect("EmpServlet");
	}

	public void doFindDept(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DaptDao dao = new DaptDao();
		List<Dapt> list = dao.findAll();

		request.setAttribute("dlist", list);
		request.getRequestDispatcher("Employee/EmpInsert.jsp").forward(request, response);

	}
	public void doFind(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String s = request.getParameter("id");
		int eid=Integer.parseInt(s);
		EmpDao dao=new EmpDao();
		Emp emp = dao.findById(eid);
		
		DaptDao deptDao = new DaptDao();
		request.setAttribute("dlist", deptDao.findAll());
		request.setAttribute("emp", emp);
		
		request.getRequestDispatcher("Employee/EmpUpdate.jsp").forward(request, response);
	}
	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ss = request.getParameter("id");
		int eid=Integer.parseInt(ss);
		EmpDao dao=new EmpDao();
		dao.delete(eid);
		
		this.doList(request, response);
	}
}
