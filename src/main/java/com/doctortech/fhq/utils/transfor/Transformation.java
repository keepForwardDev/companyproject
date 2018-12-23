package com.doctortech.fhq.utils.transfor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class Transformation<T, V> implements Transfor<T, V> {
	

	private Class<V> clazz;

	public Transformation(Class<V> _clazz) {
		this.clazz = _clazz;
	}

	public V convert(T t) {
		try {
			V v = this.clazz.newInstance();
			BeanUtils.copyProperties(t, v);
			return v;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<V> convert(List<T> list) {
		try {
			List<V> vs = new ArrayList<V>();
			for (T t : list) {
				vs.add(convert(t));
			}
			return vs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
