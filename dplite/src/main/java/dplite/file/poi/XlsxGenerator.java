package dplite.file.poi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsxGenerator implements AutoCloseable{

	protected String path;
	protected XSSFWorkbook wb;

	public XlsxGenerator(String path) {
		super();
		this.path = path;
		this.wb = new XSSFWorkbook();
	}
	
	public <T> void write(String sheetName,List<T> entities,String[] titles,String[] properties){
		XSSFSheet sheet = wb.createSheet(sheetName);
		PoiExcelSaver<T> saver = new PoiExcelSaver<>(properties, titles, sheet);
		saver.save(entities);
	}
	
	public <T> void write(String sheetName,T[] entities,String[] titles,String[] properties){
		write(sheetName,Arrays.asList(entities),titles,properties);
	}

	public void close(){
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(path);
			wb.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
