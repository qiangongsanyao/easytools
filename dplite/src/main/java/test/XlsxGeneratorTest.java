package test;

import dplite.file.poi.XlsxGenerator;

public class XlsxGeneratorTest {

	public static void main(String[] args) {
		XlsxGenerator generator = new XlsxGenerator("out.xlsx");
		generator.write("role", new Role[]{
				new Role(1,"����ʿcc"),
				new Role(2,"��������"),
				new Role(3,"��������"),
				new Role(4,"�ǳ�����"),
		}, 
				new String[]{"���","����"}, 
				new String[]{"id","name"});
		generator.write("boss", new Boss[]{
				new Boss("��ͷ����",150, 50000000),
				new Boss("��˪����",180, 60000000),
				new Boss("ɨ��ɮ",200, 100000000),
		}, 
				new String[]{"����","�ȼ�","����"}, 
				new String[]{"name","lv","life"});
		generator.close();
	}
	
}
