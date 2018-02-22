package easytools.ch.bean.converter;

public interface BeanConverter {

	public <T> T convert(Object object,Class<T> targetClass);
	
}
