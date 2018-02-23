package dplite.actor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public abstract class Saver<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2269898973847307337L;
	
	protected String[] properties;
	protected String[] titles;
	
	/**
	 * properties 属性名数组
	 * titles 列名数组
	 * @param properties
	 * @param titles
	 */
	public Saver(String[] properties, String[] titles) {
		super();
		if(properties.length!=titles.length){
			throw new RuntimeException("标题和属性数组长度不一致");
		}
		this.properties = properties;
		this.titles = titles;
	}


	public abstract void save(List<T> list);
	
	public void save(T[] ts){
		save(Arrays.asList(ts));
	}

	@Override
	public String toString() {
		return "Saver [properties=" + Arrays.toString(properties) + ", titles=" + Arrays.toString(titles) + "]";
	}


	public String[] getProperties() {
		return properties;
	}


	public void setProperties(String[] properties) {
		this.properties = properties;
	}


	public String[] getTitles() {
		return titles;
	}


	public void setTitles(String[] titles) {
		this.titles = titles;
	}

	

	
}
