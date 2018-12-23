package com.doctortech.fhq.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.Builder;

import com.google.common.base.Preconditions;

/**
 * {@linkplain Map} 生成器，实现 {@linkplain Builder} 接口通过 Builder 模式，最终调用 {@linkplain #build()} 生成 Map 对象
 * @author Helfen
 *
 * @param <K> 键类型
 * @param <V> 值类型
 */
public class MapBuilder<K, V> implements Builder<Map<K, V>>
{
	private Map<K, V> _target;
	private boolean _ignoreNullValue = false;
	
	/**
	 * 构造方法，默认的 Map 对象为 {@linkplain LinkedHashMap}
	 */
	public MapBuilder()
	{
		this(new LinkedHashMap<K, V>());
	}
	
	/**
	 * 构造方法，从一个现有的 Map 对象 Builder
	 * @param from
	 */
	public MapBuilder(Map<K, V> from)
	{
		Preconditions.checkArgument(from != null);
		
		_target = from;
	}
	
	/**
	 * 设置忽略为 <code>NULL</code> 的值
	 * @return
	 */
	public MapBuilder<K, V> ignoreNullValue()
	{
		_ignoreNullValue = true;
		
		return this;
	}
	
	public MapBuilder<K, V> put (K key, V value)
	{
		if (value != null || !_ignoreNullValue)
		{
			_target.put(key, value);
		}
		
		return this;
	}
	
	@Override
	public Map<K, V> build()
	{
		return _target;
	}
}
