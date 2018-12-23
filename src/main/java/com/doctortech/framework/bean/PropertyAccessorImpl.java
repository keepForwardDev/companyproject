package com.doctortech.framework.bean;

import org.springframework.beans.BeanWrapperImpl;

/**
 * @see PropertyAccessor
 * 
 */
public class PropertyAccessorImpl implements PropertyAccessor {
	@Override
	public Object get(Object bean, String propertyName) {
		return new BeanWrapperImpl(bean).getPropertyValue(propertyName);
	}

	@Override
	public void set(Object bean, String propertyName, Object propertyValue) {
		new BeanWrapperImpl(bean).setPropertyValue(propertyName, propertyValue);
	}

	@Override
	public Class<?> getType(Object bean, String propertyName) {
		return new BeanWrapperImpl(bean).getPropertyType(propertyName);
	}
}
