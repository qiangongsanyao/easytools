package easytools.ch.bean.converter.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Npc {

	private String name;
	private Integer lv;
	private Long money;
	private String detail;

	private Set<String> items = new HashSet<>();
	private Map<String,Integer> progressMap = new HashMap<>();
	
	public Npc() {
		super();
	}
	
	public Npc(String name, Integer lv, long money, String detail) {
		super();
		this.name = name;
		this.lv = lv;
		this.money = money;
		this.detail = detail;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLv() {
		return lv;
	}
	public void setLv(Integer lv) {
		this.lv = lv;
	}
	public Long getMoney() {
		return money;
	}

	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public void setMoney(Long money) {
		this.money = money;
	}

	public Map<String, Integer> getProgressMap() {
		return progressMap;
	}

	public void setProgressMap(Map<String, Integer> progressMap) {
		this.progressMap = progressMap;
	}

	public Set<String> getItems() {
		return items;
	}

	public void setItems(Set<String> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Npc [name=" + name + ", lv=" + lv + ", money=" + money + ", detail=" + detail + ", items=" + items
				+ ", progressMap=" + progressMap + "]";
	}

	

	
}
