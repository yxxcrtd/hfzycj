package com.zycj.sdk.base.dao;
/**
 * 使用 :name 占位符的参数封装类
 * name为参数名
 * T 为参数的值
 * @author yangsw
 *
 * @param <T>
 */
public class ParamPair<T> {
	/**
	 * 参数名
	 */
	private String name;

	/**
	 * 参数值
	 */
	private T value;

	public ParamPair(String name, T value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	/**
	 * 静态的创建方法,使用泛型推断,使用new必须指定T,否则会警告
	 * @param <T>
	 * @param name
	 * @param value
	 * @return
	 */
	public static <T> ParamPair<T> create(String name, T value) {
		return new ParamPair<T>(name, value);
	}

}
