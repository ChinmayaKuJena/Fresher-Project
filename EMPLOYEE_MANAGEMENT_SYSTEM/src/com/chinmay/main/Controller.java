package com.chinmay.main;

import java.util.Scanner;

public class Controller{
	
	public void front(){
		Scanner s = new Scanner(System.in);
		System.out.println();
		System.out.println("\t1. ADD EMPLOYEE.");
		System.out.println("\t2. SEARCH EMPLOYEE.");
		System.out.println("\t3. UPDATE EMPLOYEE.");
		System.out.println("\t4. DELETE EMPLOYEE.");
		System.out.println("\t5. EXIT.");
		System.out.println();
		System.out.print("\tENTER YOUR CHOICE  : ");
		int choice = s.nextInt();
		
		ControllerImpl ci = new ControllerImpl();
		
		switch(choice) {
		case 1:
			System.out.println("\tADD EMPLOYEE MODULE");
			System.out.println("\t===================");
			ci.addid();
			break;
		case 2:
			System.out.println("\tSEARCH EMPLOYEE MODULE");
			System.out.println("\t======================");	
			ci.search();
			break;
		case 3:
			System.out.println("\tUPDATE EMPLOYEE MODULE");
			System.out.println("\t======================");	
			ci.update();
			break;
		case 4:
			System.out.println("\tDELETE EMPLOYEE MODULE");
			System.out.println("\t======================");	
			ci.delete();
			break;
		case 5:
			System.out.println();
			System.out.println("\t+====================================+");
			System.out.println("\t|              THANK YOU             |");
			System.out.println("\t+====================================+");
			System.exit(0);
			break;
		default:
			System.err.println("\n\tPLEASE ENTER NUMBE WITHIN 1 TO 5\n");
			front();
			break;
		}
		
		s.close();
	}
}