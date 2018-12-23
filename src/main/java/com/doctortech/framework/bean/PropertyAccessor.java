package com.doctortech.framework.bean;

/**
 * 该接口表示一个对象属性访问器
 * 
 * 
 */
public interface PropertyAccessor {
	/**
	 * @para bean 取得对象属性值
	 * @param propertyName
	 * @return
	 */
	Object get(Object bean, String propertyName);

	/**
	 * @para bean 设置对象属性值
	 * @param propertyName
	 * @param propertyValue
	 */
	void set(Object bean, String propertyName, Object propertyValue);
	
	Class<?> getType(Object bean, String propertyName);
}
