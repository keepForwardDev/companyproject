package com.doctortech.fhq.utils.transfor;

import java.util.List;

public interface Transfor<T,V> {
	
	 V convert(T t);
	 
	 List<V> convert(List<T> list);

}
