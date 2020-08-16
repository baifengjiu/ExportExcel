package com.excel;

import java.util.ArrayList;
import java.util.List;

public class main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

//		EXConfig ll=new EXConfig();
//		ll.parse();
		
		String[][] data ={
				{"张三","201341027","男","18","123456"},
				{"李四","201341027","男","18", ""},
				{"如花","201341027","女","","123456"},
				{"如花","201341027","女","18","123456"}
		};
		
		String[][] data1 ={
				{"201341027","语文","26"},
				{"201341052","英语","65"},
				{"201345483","数学","18"},
				{"201349628","语文","18"}
		};

		List<List<List<String>>> dt = new ArrayList<List<List<String>>>();
		List<List<String>> datas = new ArrayList<List<String>>();
		for(int i=0;i<data.length;i++){
			List<String> temp = new ArrayList<String>();
			for(int j=0;j<data[i].length;j++){
				 temp.add(data[i][j]);
			}
			datas.add(temp);
		}

		dt.add(datas);
		datas = new ArrayList<List<String>>();
		for(int i=0;i<data1.length;i++){
			List<String> temp = new ArrayList<String>();
			for(int j=0;j<data1[i].length;j++){
				 temp.add(data1[i][j]);
			}
			datas.add(temp);
		}

		dt.add(datas);
		
		EXExport ex=new EXExport();
		ex.ExportToExcle(dt);

		
		
	}

}
