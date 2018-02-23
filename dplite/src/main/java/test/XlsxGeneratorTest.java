package test;

import dplite.file.poi.XlsxGenerator;

public class XlsxGeneratorTest {

	public static void main(String[] args) {
		XlsxGenerator generator = new XlsxGenerator("out.xlsx");
		generator.write("role", new Role[]{
				new Role(1,"龙骑士cc"),
				new Role(2,"无名高手"),
				new Role(3,"三生三世"),
				new Role(4,"非诚勿扰"),
		}, 
				new String[]{"编号","名字"}, 
				new String[]{"id","name"});
		generator.write("boss", new Boss[]{
				new Boss("无头将军",150, 50000000),
				new Boss("冰霜巨龙",180, 60000000),
				new Boss("扫地僧",200, 100000000),
		}, 
				new String[]{"名字","等级","生命"}, 
				new String[]{"name","lv","life"});
		generator.close();
	}
	
}
