package dplite.file;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


import dplite.actor.Saver;

public abstract class FileSaver<T> extends Saver<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4059346877884623149L;



	public FileSaver(String[] properties, String[] titles) {
		this(properties, titles, true);
	}

	protected boolean printTitle;
	
	
	
	public FileSaver(String[] properties, String[] titles, boolean printTitle) {
		super(properties, titles);
		this.printTitle = printTitle;
	}



	@Override
	public void save(List<T> list) {
		if(printTitle){
			printLine(titles);
		}
		for(T t:list){
			PropertyDescriptor[] propertyDescriptors = getpropertyDescriptors(t);
			Object[] objects = new Object[propertyDescriptors.length];
			for(int i=0;i<propertyDescriptors.length;i++){
				PropertyDescriptor propertyDescriptor = propertyDescriptors[i];
				objects[i] = getObject(propertyDescriptor,t);
			}
			printLine(objects);
		}
	}


	private Object getObject(PropertyDescriptor propertyDescriptor, T t) {
		Method method = propertyDescriptor.getReadMethod();
		try {
			return method.invoke(t);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private PropertyDescriptor[] propertyDescriptors;

	private PropertyDescriptor[] getpropertyDescriptors(T t) {
		if(propertyDescriptors == null){
			Class<? extends Object> ts = t.getClass();
			try {
				BeanInfo beanInfo = Introspector.getBeanInfo(ts);
				PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
				this.propertyDescriptors = new PropertyDescriptor[properties.length];
				A:for(int i=0;i<properties.length;i++){
					String propertyName = properties[i];
					for(PropertyDescriptor propertyDescriptor:propertyDescriptors){
						if(propertyDescriptor.getName().equals(propertyName)){
							this.propertyDescriptors[i] = propertyDescriptor;
							continue A;
						}
					}
					throw new RuntimeException(ts+"中找不到属性"+propertyName);
				}
			} catch (IntrospectionException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return propertyDescriptors;
	}



	protected abstract void printLine(Object[] objects);



	public boolean isPrintTitle() {
		return printTitle;
	}

	public void setPrintTitle(boolean printTitle) {
		this.printTitle = printTitle;
	}
	

}
