package easytools.ch.bean.converter;

/**
 * conerter a javabean to targetjavabean.<br>
 * @author Ch
 * @version 1.0
 */
public interface BeanConverter {

	public <T> T convert(Object object,Class<T> targetClass);
	
}
