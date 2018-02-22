package easytools.ch.bean.converter.impl;

import java.util.ArrayList;
import java.util.TreeMap;

public class Role {

	private String name;
	private int lv;
	private int money;
	private int vip;
	
	private ArrayList<String> items = new ArrayList<>();
	private TreeMap<String,Integer> progressMap = new TreeMap<>();
	
	public Role() {
		super();
	}
	
	public Role(String name, int lv, int money, int vip) {
		super();
		this.name = name;
		this.lv = lv;
		this.money = money;
		this.vip = vip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}
	
	public ArrayList<String> getItems() {
		return items;
	}

	public void setItems(ArrayList<String> items) {
		this.items = items;
	}

	public TreeMap<String, Integer> getProgressMap() {
		return progressMap;
	}

	public void setProgressMap(TreeMap<String, Integer> progressMap) {
		this.progressMap = progressMap;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", lv=" + lv + ", money=" + money + ", vip=" + vip + ", items=" + items
				+ ", progressMap=" + progressMap + "]";
	}

	

}
