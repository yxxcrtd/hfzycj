package com.zycj.test.async;

public class A {

	private A(){}
	private static A a=new A();
	public static A getIns(){
		return a;
	}
}
