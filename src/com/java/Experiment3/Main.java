package com.java.Experiment3;


	class Person {
	String name;
	int age;
	public Person(String name, int age) {
	this.name = name;
	this.age = age;
	}
	public void displayInfo() {
	System.out.println("Name: " + name + ", Age: " + age);
	}
	}
	//Extend Person Class in Employee
	class Employee extends Person {
	double baseSalary;
	public Employee(String name, int age, double baseSalary) {
	super(name, age);
	this.baseSalary = baseSalary;
	}
	public double calculateSalary() {
	return baseSalary ;
	}
	public void updateEmployeeData(String newName) {
	this.name = newName;
	System.out.println("Updated Name: " + name);
	}
	public void updateEmployeeData(int newAge) {
	this.age = newAge;
	System.out.println("Updated Age: " + age);
	}
	public void updateEmployeeData(double newSalary) {
	this.baseSalary = newSalary;
	System.out.println("Updated Salary: " + baseSalary);
	}
	}
	//Extend Employee Class in Manager
	class Manager extends Employee {
	double bonus;
	public Manager(String name, int age, double baseSalary, double bonus) {
	super(name, age, baseSalary);
	this.bonus = bonus;
	}
	public double calculateSalary() {
	return baseSalary + bonus;
	}
	}
	// Main Class
	public class Main {
	public static void main(String[] args) {
	Employee emp = new Employee("Poonam", 21 , 80000);
	Manager mngr = new Manager("Pratibha", 23 , 80000, 20000);
	emp.displayInfo();
	System.out.println("Employee Salary: " + emp.calculateSalary());
	mngr.displayInfo();
	System.out.println("Manager Salary: " + mngr.calculateSalary());
	emp.updateEmployeeData("Shashikala");
	emp.updateEmployeeData(25);
	emp.updateEmployeeData(650000);
	}
	}
	