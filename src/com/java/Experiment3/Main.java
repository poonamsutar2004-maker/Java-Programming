package com.java.Experiment3;


public class Main {

	    public static void main(String[] args) {

	        Employee emp = new Employee("Rutika", 25, 60000);
	        Manager mngr = new Manager("Poonam", 21, 80000, 10000);

	        emp.displayInfo();
	        System.out.println(emp.calculateSalary());

	        mngr.displayInfo();
	        System.out.println(mngr.calculateSalary());
	    }
	}



