package dplite.file.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import dplite.file.FileSaver;


public class PoiExcelSaver<T> extends FileSaver<T> {


	/**
	 * 
	 */
	private static final long serialVersionUID = -126557138432731469L;

	protected Sheet sheet;
	
	public PoiExcelSaver(String[] properties, String[] titles, Sheet sheet, boolean printTitle) {
		super(properties, titles, printTitle);
		this.sheet = sheet;
	}


	public PoiExcelSaver(String[] properties, String[] titles, Sheet sheet) {
		this(properties, titles, sheet, true);
	}


	protected int rowIndex;
	
	/**
	 * 支持Numerical、Boolean、String类型数据
	 */
	@Override
	protected void printLine(Object[] objects) {
		Row row = sheet.createRow(rowIndex++);
		for(int cellIndex=0;cellIndex<objects.length;cellIndex++){
			Object object = objects[cellIndex];
			Cell cell = row.createCell(cellIndex);
			if(object == null){
				
			}else if(object instanceof Double){
				Double number = (Double)object;
				cell.setCellValue(number);
			}else if(object instanceof Integer){
				Integer number = (Integer)object;
				cell.setCellValue(number);
			}else if(object instanceof Float){
				Float number = (Float)object;
				cell.setCellValue(number);
			}else if(object instanceof Long){
				Long number = (Long)object;
				cell.setCellValue(number);
			}else if(object instanceof Short){
				Short number = (Short)object;
				cell.setCellValue(number);
			}else if(object instanceof Boolean){
				boolean b = (boolean)object;
				cell.setCellValue(b);
			}else{
				cell.setCellValue(object.toString());
			}
		}
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}
	
}
