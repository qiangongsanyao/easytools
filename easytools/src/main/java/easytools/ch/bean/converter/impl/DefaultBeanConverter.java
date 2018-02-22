package easytools.ch.bean.converter.impl;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import easytools.ch.bean.converter.BeanConverter;
import easytools.ch.bean.converter.exception.ConverterException;

/**
 * conerter a javabean to targetjavabean.<br>
 * the targetClass must has a nullary constructor and constructor is accessible.<br>
 * the properties which have same name and same type will get the value from the originObject.<br>
 * byte -> short -> int -> long.<br>
 * byte -> short -> int -> float -> double.<br>
 * please make sure a collection or a map is type complete<br>
 * @author CH
 *
 */
public class DefaultBeanConverter implements BeanConverter {

	public final static Map<Class<?>,Class<?>> convertableclasses;
	
	static {
		Map<Class<?>,Class<?>> map = new HashMap<>();
		map.put(byte.class,Byte.class);
		map.put(Byte.class,Byte.class);
		map.put(short.class,Short.class);
		map.put(Short.class,Short.class);
		map.put(int.class,Integer.class);
		map.put(Integer.class,Integer.class);
		map.put(long.class,Long.class);
		map.put(Long.class,Long.class);
		map.put(float.class,Float.class);
		map.put(Float.class,Float.class);
		map.put(double.class,Double.class);
		map.put(Double.class,Double.class);
		convertableclasses = Collections.unmodifiableMap(map);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T> T convert(Object object, Class<T> targetClass) {
		try {
			final T t = targetClass.newInstance();
			Class<?> originClass = object.getClass();
			BeanInfo beanInfo = Introspector.getBeanInfo(originClass);
			Map<String,PropertyDescriptor> targetPropertyNameMap = Arrays.stream(Introspector.getBeanInfo(targetClass).getPropertyDescriptors())
					.filter(p -> p.getWriteMethod()!=null)
					.collect(Collectors.toMap(PropertyDescriptor::getName, (p)->p));	
			Arrays.stream(beanInfo.getPropertyDescriptors())
				.filter(p->
					p.getReadMethod()!=null&&
					targetPropertyNameMap.get(p.getName())!=null&&
					targetPropertyNameMap.get(p.getName()).getWriteMethod()!=null)
				.forEach(p->{
					PropertyDescriptor tp = targetPropertyNameMap.get(p.getName());
					Object arg = null;
					try {
						arg = p.getReadMethod().invoke(object);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
						e1.printStackTrace();
					}
					if(arg == null) {
						return;
					}
					Class<?> tc = tp.getPropertyType();
					Class<?> oc = p.getPropertyType();
					if( tc == oc || superclass(tc, oc)) {
						try {
							tp.getWriteMethod().invoke(t, arg);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
					} else {
						if(mapable(tc,oc)) {
							if(tp.getReadMethod()==null) {
								
							}else {
								try {
									Map map;
									map = ((Map)tp.getReadMethod().invoke(t));
									if(map!=null) {
										Map<?,?> originMap = (Map<?,?>)arg;
										originMap.forEach((k,v)->{
											map.put(k, v);
										});
									}
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									e.printStackTrace();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}else if(collectionable(tc,oc)) {
							if(tp.getReadMethod()==null) {
								
							}else {
								try {
									Collection collection;
									collection = ((Collection)tp.getReadMethod().invoke(t));
									if(collection!=null) {
										Collection<?> originCollections = (Collection<?>)arg;
										originCollections.forEach(oi->{
											collection.add(oi);
										});
									}
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									e.printStackTrace();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}else if(numberable(tc,oc,arg)) {
							try {
								String s = String.valueOf(arg);
								if(convertableclasses.get(tc) == Integer.class) {
									tp.getWriteMethod().invoke(t, Integer.parseInt(s));
								}else if(convertableclasses.get(tc) == Byte.class) {
									tp.getWriteMethod().invoke(t, Byte.parseByte(s));
								}else if(convertableclasses.get(tc) == Integer.class) {
									tp.getWriteMethod().invoke(t, Short.parseShort(s));
								}else if(convertableclasses.get(tc) == Long.class) {
									tp.getWriteMethod().invoke(t, Long.parseLong(s));
								}else if(convertableclasses.get(tc) == Float.class) {
									tp.getWriteMethod().invoke(t, Float.parseFloat(s));
								}else{
									tp.getWriteMethod().invoke(t, Double.parseDouble(s));
								}
							} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
								e.printStackTrace();
							}
						}
					}
			});
			return t;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new ConverterException(e);
		} catch (IntrospectionException e) {
			e.printStackTrace();
			throw new ConverterException(e);
		}
	}

	private boolean collectionable(Class<?> tc, Class<?> oc) {
		return getSupers(oc).contains(Collection.class)
				&&getSupers(tc).contains(Collection.class);
	}

	private boolean mapable(Class<?> tc, Class<?> oc) {
		return getSupers(oc).contains(Map.class)
				&&getSupers(tc).contains(Map.class);
	}

	private boolean superclass(Class<?> tc, Class<?> oc) {
		if(tc==Object.class) {
			return true;
		}
		List<Class<?>> supers = getSupers(oc);
		return supers.contains(tc);
	}

	private List<Class<?>> getSupers(Class<?> oc) {
		List<Class<?>> list = new ArrayList<>();
		list.add(oc);
		Class<?> superClass = oc.getSuperclass();
		if(superClass != null) {
			list.add(superClass);
			list.addAll(getSupers(superClass));
		}
		Class<?>[] interfaces = oc.getInterfaces();
		for(Class<?> ai:interfaces) {
			list.add(ai);
			list.addAll(getSupers(ai));
		}
		return list;
	}

	public static final List<Class<?>> list1 = Collections.unmodifiableList(Arrays.asList(Byte.class,Short.class,Integer.class,Long.class));
	public static final List<Class<?>> list2 = Collections.unmodifiableList(Arrays.asList(Byte.class,Short.class,Integer.class,Float.class,Long.class));
	
	private boolean numberable(Class<?> tc, Class<?> oc, Object arg) {
		tc = convertableclasses.get(tc);
		oc = convertableclasses.get(oc);
		if(list1.contains(tc)&&list1.contains(oc)) {
			if(list1.indexOf(tc)>=list1.indexOf(oc)) {
				return true;
			}else {
				
			}
		}else if(list2.contains(tc)&&list2.contains(oc)) {
			if(list2.indexOf(tc)>=list2.indexOf(oc)) {
				return true;
			}
		}
		return false;
	}

}
