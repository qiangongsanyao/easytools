package other.test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import org.junit.Test;

public class Number {

	private int intnum;
	private Integer integerNum;
	
	@Test
	public void test() throws IntrospectionException {
		Class<Number> clazz = Number.class;
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
		PropertyDescriptor[] ps = beanInfo.getPropertyDescriptors();
		for(PropertyDescriptor p:ps) {
			System.out.println(p.getPropertyType());
		}
	}

	public int getIntnum() {
		return intnum;
	}

	public void setIntnum(int intnum) {
		this.intnum = intnum;
	}

	public Integer getIntegerNum() {
		return integerNum;
	}

	public void setIntegerNum(Integer integerNum) {
		this.integerNum = integerNum;
	}
	
	
	
}
