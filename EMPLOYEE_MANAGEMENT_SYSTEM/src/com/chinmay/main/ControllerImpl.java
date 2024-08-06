package com.chinmay.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ControllerImpl {
	Scanner s = new Scanner(System.in);
	Bean bean = new Bean();
	Controller c = new Controller();

	String dbName = "com.mysql.cj.jdbc.Driver";
	String dburl = "jdbc:mysql://localhost:3306/employee?useSSL=false";
	String uname = "root";
	String pwd = "root";

	public void addid() {
		System.out.println();
		System.out.print("\tENTER EMPLOYEE ID       : ");
		try {
			bean.setId(s.nextInt());
			addname();

		} catch (Exception e) {
			System.err.println("\tENTER ID ONLY IN NUMBERS");
			s.nextLine();
			addid();
		}

	}

	public void addname() {
		System.out.print("\tENTER EMPLOYEE NAME     : ");
		bean.setName(s.next());
		s.nextLine();
		if (bean.getName().matches("[a-zA-Z ]+")) {
			addSal();
		} else {
			System.err.println("\tENTER VALID NAME\n");
			// s.nextLine();
			addname();
		}
	}

	public void addSal() {
		System.out.print("\tENTER EMPLOYEE SALARY   : ");
		try {
			bean.setSal(s.nextFloat());
			s.nextLine();
			addAddr();
		} catch (Exception e) {
			System.err.println("\n\tENTER VALID SALARY");
			s.nextLine();
			addSal();
		}

	}

	public void addAddr() {
		System.out.print("\tENTER EMPLOYEE ADDRESS  : ");
		bean.setEaddr(s.next());
		s.nextLine();
		if (bean.getEaddr().matches("[a-zA-Z ]+")) {
			dbAdd();
		} else {
			System.err.println("\tENTER VALID ADDRESS.\n");
			// s.nextLine();
			addAddr();
		}
	}

	public void dbAdd() {
		try {
			Class.forName(dbName);
			Connection con = DriverManager.getConnection(dburl, uname, pwd);
			PreparedStatement ps = con.prepareStatement("insert into emp3 values(?,?,?,?)");
			ps.setInt(1, bean.getId());
			ps.setString(2, bean.getName());
			ps.setFloat(3, bean.getSal());
			ps.setString(4, bean.getEaddr());
			try {
				int count = ps.executeUpdate();
				if (count > 0) {
					System.out.println("\t+====================================+");
					System.out.println("\t|   EMPLOYEE INSERTED SECCESSFULLY   |");
					System.out.println("\t+====================================+");
					c.front();
				} else {
					System.out.println("\t+====================================+");
					System.out.println("\t|      EMPLOYEE ALREADY EXIST        |");
					System.out.println("\t+====================================+");
					addid();
				}
			} catch (Exception e) {
				System.out.println("\t+====================================+");
				System.out.println("\t|      EMPLOYEE ALREADY EXIST        |");
				System.out.println("\t+====================================+");
				addid();
			}

		} catch (Exception e) {

		}
	}

	public void search() {

		System.out.println();
		System.out.print("\tENTER EMPLOYEE ID       : ");
		try {
			bean.setId(s.nextInt());
			s.nextLine();

		} catch (Exception e) {
			System.err.println("\tENTER ID ONLY IN NUMBERS");
			search();
		}

		try {
			Class.forName(dbName);
			Connection con = DriverManager.getConnection(dburl, uname, pwd);
			PreparedStatement ps = con.prepareStatement("select * from emp3 where eno=?");
			ps.setInt(1, bean.getId());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("\n\tEMPLOYEE DETAILS");
				System.out.println("\t======================");
				System.out.println("\tEMPLOYEE ID      : " + rs.getInt(1));
				System.out.println("\tEMPLOYEE NAME    : " + rs.getString(2));
				System.out.println("\tEMPLOYEE SALARY  : " + rs.getFloat(3));
				System.out.println("\tEMPLOYEE ADDRESS : " + rs.getString(4));
				System.out.println();
				System.out.println("\t+====================================+");
				System.out.println("\t|              THANK YOU             |");
				System.out.println("\t+====================================+");
				c.front();
			} else {
				System.out.println("\t+====================================+");
				System.out.println("\t|     EMPLOYEE DOES NOT EXIST        |");
				System.out.println("\t+====================================+");
				search();
			}
		} catch (Exception e) {
			System.out.println("\t+====================================+");
			System.out.println("\t|     EMPLOYEE DOES NOT EXIST        |");
			System.out.println("\t+====================================+");
			search();

		}
	}

	public void update() {
		System.out.println();
		System.out.print("\tENTER EMPLOYEE ID       : ");
		try {
			bean.setId(s.nextInt());
			s.nextLine();

		} catch (Exception e) {
			System.err.println("\tENTER ID ONLY IN NUMBERS");
			update();
		}
		try {
			Class.forName(dbName);
			Connection con = DriverManager.getConnection(dburl, uname, pwd);
			PreparedStatement ps = con.prepareStatement("select * from emp3 where eno=?");
			ps.setInt(1, bean.getId());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.print("\tYOUR OLD NAME IS    :: " + rs.getString(2) + " :: ENTER NEW NAME     : ");
				String name = s.nextLine();
				System.out.print("\tYOUR OLD SALARY IS  :: " + rs.getFloat(3) + "  :: ENTER NEW SALARY    : ");
				float sal = s.nextFloat();
				System.out.print("\tYOUR OLD ADDRESS IS :: " + rs.getString(4) + " :: ENTER NEW ADDRESS  : ");
				String addr = s.next();

				PreparedStatement ps1 = con.prepareStatement("update emp3 set ename=?,esal=?,eaddr=? where eno=?");
				ps1.setString(1, name);
				ps1.setFloat(2, sal);
				ps1.setString(3, addr);
				ps1.setInt(4, bean.getId());
				;

				int count = ps1.executeUpdate();

				if (count > 0) {
					System.out.println("\t+====================================+");
					System.out.println("\t|  EMPLOYEE UPDATATION SUCCESSFULLY  |");
					System.out.println("\t+====================================+");
					c.front();
				} else {
					System.out.println("\t+====================================+");
					System.out.println("\t|     EMPLOYEE UPDATATION FAILED     |");
					System.out.println("\t+====================================+");
					update();
				}
			}
		} catch (Exception e) {
			System.out.println("\t+====================================+");
			System.out.println("\t|     EMPLOYEE UPDATATION FAILED     |");
			System.out.println("\t+====================================+");
			update();
		}

	}

	public void delete() {
		System.out.println();
		System.out.print("\tENTER EMPLOYEE ID       : ");
		try {
			bean.setId(s.nextInt());
			s.nextLine();

		} catch (Exception e) {
			System.err.println("\tENTER ID ONLY IN NUMBERS");
			delete();
		}
		try {
			Class.forName(dbName);
			Connection con = DriverManager.getConnection(dburl, uname, pwd);
			PreparedStatement ps = con.prepareStatement("delete from emp3 where eno=?");
			ps.setInt(1, bean.getId());
			int done = ps.executeUpdate();
			if(done>0) {
				System.out.println("\t+====================================+");
				System.out.println("\t|   EMPLOYEE DELETION SUCCESSFULLY   |");
				System.out.println("\t+====================================+");
				c.front();
			}else {
				System.out.println("\t+====================================+");
				System.out.println("\t|      EMPLOYEE DOES NOT EXIST       |");
				System.out.println("\t+====================================+");
				delete();
			}
		}catch(Exception e) {
			System.out.println("\t+====================================+");
			System.out.println("\t|      EMPLOYEE DOES NOT EXIST       |");
			System.out.println("\t+====================================+");
			delete();
			
		}
	}
}