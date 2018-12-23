package com.doctortech.framework.bean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.doctortech.fhq.utils.DateUtil;

public class PageData extends HashMap implements Map{
	
	private static final long serialVersionUID = 1L;
	
	private PropertyAccessor propertyAccessor = new PropertyAccessorImpl();
	
	Map map = null;
	HttpServletRequest request;
	
	public PageData(HttpServletRequest request){
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap(); 
		Iterator entries = properties.entrySet().iterator(); 
		Map.Entry entry; 
		while (entries.hasNext()) {
			String name = "";  
			String value = ""; 
			entry = (Map.Entry) entries.next(); 
			name = (String) entry.getKey(); 
			Object valueObj = entry.getValue(); 
			if(null == valueObj){ 
				value = ""; 
			}else if(valueObj instanceof String[]){ 
				String[] values = (String[])valueObj;
				for(int i=0;i<values.length;i++){ 
					 value += values[i] + ",";
				}
				value = value.substring(0, value.length()-1); 
			}else{
				value = valueObj.toString(); 
			}
			returnMap.put(name, value); 
		}
		map = returnMap;
	}
	
	public PageData(MultipartHttpServletRequest request){
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap(); 
		Iterator entries = properties.entrySet().iterator(); 
		Map.Entry entry; 
		while (entries.hasNext()) {
			String name = "";  
			String value = "";  
			entry = (Map.Entry) entries.next(); 
			name = (String) entry.getKey(); 
			Object valueObj = entry.getValue(); 
			if(null == valueObj){ 
				value = ""; 
			}else if(valueObj instanceof String[]){ 
				String[] values = (String[])valueObj;
				for(int i=0;i<values.length;i++){ 
					 value += values[i] + ",";
				}
				value = value.substring(0, value.length()-1); 
			}else{
				value = valueObj.toString(); 
			}
			returnMap.put(name, value); 
		}
		map = returnMap;
	}
	
	public PageData() {
		map = new HashMap();
	}
	
	@Override
	public Object get(Object key) {
		Object obj = null;
		if(map.get(key) instanceof Object[]) {
			Object[] arr = (Object[])map.get(key);
			obj = request == null ? arr:(request.getParameter((String)key) == null ? arr:arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}
	
	public String getString(Object key) {
		return (String)get(key);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}
	
	public PageData add(Object key,Object value){
		map.put(key, value);
		return this;
	}
	
	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return map.containsValue(value);
	}

	public Set entrySet() {
		// TODO Auto-generated method stub
		return map.entrySet();
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return map.isEmpty();
	}

	public Set keySet() {
		// TODO Auto-generated method stub
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		// TODO Auto-generated method stub
		map.putAll(t);
	}

	public int size() {
		// TODO Auto-generated method stub
		return map.size();
	}

	public Collection values() {
		// TODO Auto-generated method stub
		return map.values();
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public Object getEntity(String className) {
		Object bean = null;
		
		try {
            //创建类
            Class<?> objClass = Class.forName(className);

            //创建实例
            bean = objClass.newInstance();

            Field[] fields = objClass.getDeclaredFields() ;
            
            for(Field filed : fields){
            	String propertyName = filed.getName();
            	String key = propertyName;
            	
            	if (ClassUtils.isAssignable(filed.getType(), List.class)) {
            		key = propertyName+"[]";
            	}
            	
            	if(map.containsKey(key)){
            		String propertyValue = StringUtils.trim((String)map.get(key));
            		
            		if(StringUtils.isBlank(propertyValue)){
            			propertyAccessor.set(bean, propertyName, null);
            			continue;
            		}
            		
	            	if (ClassUtils.isAssignable(filed.getType(), String.class)) {
	            		propertyAccessor.set(bean, propertyName, propertyValue);
					}  else if (ClassUtils.isAssignable(filed.getType(), Long.class)) {
						propertyAccessor.set(bean, propertyName, Long.valueOf(propertyValue));
					}  else if (ClassUtils.isAssignable(filed.getType(), Double.class)) {
						propertyAccessor.set(bean, propertyName, Double.valueOf(propertyValue));
					} else if (ClassUtils.isAssignable(filed.getType(), Integer.class)) {
						propertyAccessor.set(bean, propertyName, Integer.valueOf(propertyValue));
					} else if (ClassUtils.isAssignable(filed.getType(), Boolean.class)) {
						if (ArrayUtils.contains(new String[] { "是", "有"  }, propertyValue)) {
							propertyAccessor.set(bean, propertyName, true);
						} else if (ArrayUtils.contains(new String[] { "否", "无" }, propertyValue)) {
							propertyAccessor.set(bean, propertyName, false);
						} else {
							propertyAccessor.set(bean, propertyName, Boolean.valueOf(propertyValue));
						}
					} else if (ClassUtils.isAssignable(filed.getType(), Date.class)) {
						//propertyAccessor.set(bean, propertyName, DateUtil.stringToDate(propertyValue));
						propertyAccessor.set(bean, propertyName, DateUtil.stringToDate(propertyValue));
					//	propertyAccessor.set(bean, propertyName, DateUtil.stringToDate(propertyValue));
					} else if (ClassUtils.isAssignable(filed.getType(), List.class)) {
						String[] array = StringUtils.split(propertyValue, ",");
						ArrayList<String> list = new ArrayList<String>(Arrays.asList(array));
						propertyAccessor.set(bean, propertyName, list);
					}
				}
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace() ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		return bean;
	}
	
	
}
