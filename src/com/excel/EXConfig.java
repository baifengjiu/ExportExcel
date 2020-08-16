package com.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author fuxue
 * @date 2017/8/21
 */
public class EXConfig {
	private String configFile = "./excel.cgf.xml";
	private String filename;
    private String outpath;
    private List<String> sheet;
    private List<List<String>> columns;
    private List<List<List<String>>> datas;
    
    EXConfig(){
    	this.initialize();
    };
    
	private void initialize() {
		// TODO Auto-generated method stub
		this.setFilename("");
		this.setOutpath("");
//		this.setSheet(new ArrayList());
//		this.setColumns(new ArrayList());
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getOutpath() {
		return outpath;
	}

	public void setOutpath(String outpath) {
		this.outpath = outpath;
	}
	
	public List<String> getSheet() {
		return sheet;
	}

	public void setSheet(List<String> sheet) {
		this.sheet = sheet;
	}
	
	public List<List<String>> getColumns() {
		return columns;
	}

	public void setColumns(List<List<String>> columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		return "EXConfig [\n  configFile=" + configFile + ",\n  filename=" + filename + ",\n  outpath=" + outpath + ",\n  sheet="
				+ sheet + ",\n  columns=" + columns + "\n]";
	}

	public void parse() throws Exception{
		File config=new File(configFile);
		if(!config.exists()){
			System.out.println("the config file oppen faild!");
			return;
		}
		
        SAXReader reader = new SAXReader();
        Document document = reader.read(config);
        Element node = document.getRootElement();  
        
		String name = node.element("filename").getText();
		String path = node.element("outpath").getText();
		this.setFilename(name);
		this.setOutpath(path+name+".xlsx");
		
		List<List<String>> columns=new ArrayList<List<String>>();
		List<String> st=new ArrayList<String>();		
		List sheets = node.elements("sheet");
		for(int i=0;i<sheets.size();i++){
			 Element sheet = (Element) sheets.get(i);
			 List<Element> it = sheet.elements();
			 st.add(it.get(0).getText());
			 List<String> col=new ArrayList<String>();
			 for(int j=1;j<it.size();j++){				 
				 col.add(it.get(j).getText());
			 }
			 columns.add(col);
		}
		this.setSheet(st);
		this.setColumns(columns);		

		System.out.println(this.toString());
		return;		
	}
}
