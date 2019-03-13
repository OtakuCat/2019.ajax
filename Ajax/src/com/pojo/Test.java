package com.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dao.EmpDao;

public class Test {
	public static void main(String[] args) {
		EmpDao dao = new EmpDao();
		Emp emp = null;
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse("2001-01-11");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		emp = new Emp();
		emp.setEname("еб");
		emp.setEage(23);
		emp.setEsex(1);
		emp.setEhireDate(date);
		emp.setDid(1);

		int i = dao.insert(emp);
		System.out.println(i);
	}
}
