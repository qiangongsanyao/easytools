package easytools.ch.bean.converter.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

import org.junit.Test;

public class DefaultBeanConverterTest {

	DefaultBeanConverter converter = new DefaultBeanConverter();
	
	@Test
	public void testConvert() {
		HashMap<String, Integer> npcmap = new HashMap<>();
		TreeMap<String, Integer> rolemap = new TreeMap<>();
		npcmap.put("role", 100);
		rolemap.put("npc", 50);
		
		Role role = new Role("role", 99, 10000, 13);
		role.setProgressMap(rolemap);
		
		Npc npc = new Npc("npc", 150, 10000, "a npc");
		npc.setProgressMap(npcmap);
		
		npc.setItems(new HashSet<>(Arrays.asList("npc id card")));
		role.setItems(new ArrayList<>(Arrays.asList("role id card")));
		Role tr = converter.convert(npc, Role.class);
		Npc tn = converter.convert(role, Npc.class);
		System.out.println(role+" -> "+tn);
		System.out.println(npc+" -> "+tr);
	}

}
