package excel;

import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jni.File;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import commonUtils.CreateChart;
import commonUtils.MarketEnvirentAss;
import commonUtils.Utils;
import jxl.Sheet;
import jxl.Workbook;

public class Test {/*
	private void temp() {
		// TODO Auto-generated method stub

    	Workbook wrb = Workbook.getWorkbook(new File(savePath + "temp.xls"));
    	Sheet sheet = wrb.getSheet(0);
    	sumStock = sheet.getRows()-1;
		for(int i = 1 ; i<sheet.getRows();i++){	            			
			String contents = sheet.getCell(4,i).getContents();	
			if(Utils.strIsNum(contents)){
				BigDecimal decimal = new BigDecimal(contents).movePointLeft(2);
				double longStr = decimal.doubleValue();	 
				BigDecimal yesDecimal = new BigDecimal(sheet.getCell(22,i).getContents());
				BigDecimal todayDecimal = new BigDecimal(sheet.getCell(5,i).getContents());
				
				if(decimal.compareTo(new BigDecimal(0)) == 1){  //股票上涨
					int upValue = todayDecimal.compareTo(Utils.getNumOf2(String.valueOf(yesDecimal.doubleValue()*1.1)));
					if(upValue == 0 || (decimal.compareTo(new BigDecimal(0.4))==1)){  
						//System.out.println("涨停");
						stockTrading++;
						//涨停一字板
						BigDecimal openOnContents = new BigDecimal(sheet.getCell(21,i).getContents());	//开盘
						BigDecimal openOffContents = new BigDecimal(sheet.getCell(5,i).getContents());	//收盘
						BigDecimal highContents = new BigDecimal(sheet.getCell(23,i).getContents());  //最高
						BigDecimal lowerContents = new BigDecimal(sheet.getCell(24,i).getContents());	//最低

						if(openOnContents.compareTo(openOffContents) == 0 && openOnContents.compareTo(highContents) == 0 && openOnContents.compareTo(lowerContents) == 0){
							stockTradingYiBan++;
						}
						if(openOnContents.compareTo(openOffContents) <0 && lowerContents.compareTo(highContents) < 0){
							stockTradingHuan++;
						}
					}else{	            						
						if(decimal.compareTo(new BigDecimal(0.09)) > 0){
							riseOf10++;
						}else if(decimal.compareTo(new BigDecimal(0.09)) <= 0 && decimal.compareTo(new BigDecimal(0.08)) >0){
							riseOf9++;
						}else if(decimal.compareTo(new BigDecimal(0.08)) <= 0 && decimal.compareTo(new BigDecimal(0.07)) >0){
							riseOf8++;
						}else if(decimal.compareTo(new BigDecimal(0.07)) <= 0 && decimal.compareTo(new BigDecimal(0.06)) >0){
							riseOf7++;
						}else if(decimal.compareTo(new BigDecimal(0.06)) <= 0 && decimal.compareTo(new BigDecimal(0.05)) >0){
							riseOf6++;
						}else if(decimal.compareTo(new BigDecimal(0.05)) <= 0 && decimal.compareTo(new BigDecimal(0.04)) >0){
							riseOf5++;
						}else if(decimal.compareTo(new BigDecimal(0.04)) <= 0 && decimal.compareTo(new BigDecimal(0.03)) >0){
							riseOf4++;
						}else if(decimal.compareTo(new BigDecimal(0.03)) <= 0 && decimal.compareTo(new BigDecimal(0.02)) >0){
							riseOf3++;
						}else if(decimal.compareTo(new BigDecimal(0.02)) <= 0 && decimal.compareTo(new BigDecimal(0.01)) >0){
							riseOf2++;
						}else if(decimal.compareTo(new BigDecimal(0.01)) <= 0 && decimal.compareTo(new BigDecimal(0.00)) >0){
							riseOf1++;
						}
					}	            					
				}else if(decimal.compareTo(new BigDecimal(0)) == -1){   //股票下跌
					int downValue = todayDecimal.compareTo(Utils.getNumOf2(String.valueOf(yesDecimal.doubleValue()*0.9)));
					if(downValue == 0){
						stockLimit ++;
						BigDecimal openOnContents = new BigDecimal(sheet.getCell(21,i).getContents());	//开盘
						BigDecimal openOffContents = new BigDecimal(sheet.getCell(5,i).getContents());	//收盘
						BigDecimal highContents = new BigDecimal(sheet.getCell(23,i).getContents());  //最高
						BigDecimal lowerContents = new BigDecimal(sheet.getCell(24,i).getContents());	//最低

						if(openOnContents.compareTo(openOffContents) == 0 && openOnContents.compareTo(highContents) == 0 && openOnContents.compareTo(lowerContents) == 0){
							stockLimitYiBan++;
						}
						if(openOnContents.compareTo(openOffContents) <0 && lowerContents.compareTo(highContents) < 0){
							stockLimitHuan++;
						}
					}else{	            						
						if(decimal.compareTo(new BigDecimal(-0.09)) < 0){
							negativeOf10++;
						}else if(decimal.compareTo(new BigDecimal(-0.09)) >= 0 && decimal.compareTo(new BigDecimal(-0.08)) <0){
							negativeOf9++;
						}else if(decimal.compareTo(new BigDecimal(-0.08)) >= 0 && decimal.compareTo(new BigDecimal(-0.07)) <0){
							negativeOf8++;
						}else if(decimal.compareTo(new BigDecimal(-0.07)) >= 0 && decimal.compareTo(new BigDecimal(-0.06)) <0){
							negativeOf7++;
						}else if(decimal.compareTo(new BigDecimal(-0.06)) >= 0 && decimal.compareTo(new BigDecimal(-0.05)) <0){
							negativeOf6++;
						}else if(decimal.compareTo(new BigDecimal(-0.05)) >= 0 && decimal.compareTo(new BigDecimal(-0.04)) <0){
							negativeOf5++;
						}else if(decimal.compareTo(new BigDecimal(-0.04)) >= 0 && decimal.compareTo(new BigDecimal(-0.03)) <0){
							negativeOf4++;
						}else if(decimal.compareTo(new BigDecimal(-0.03)) >= 0 && decimal.compareTo(new BigDecimal(-0.02)) <0){
							negativeOf3++;
						}else if(decimal.compareTo(new BigDecimal(-0.02)) >= 0 && decimal.compareTo(new BigDecimal(-0.01)) <0){
							negativeOf2++;
						}else if(decimal.compareTo(new BigDecimal(-0.01)) >= 0 && decimal.compareTo(new BigDecimal(-0.00)) <0){
							negativeOf1++;
						}
					}
				}else{
					stockNoChange++;
				}
			}else{
				if("--".equals(contents.trim())){  
					System.out.println("停牌");
					suspensioNum++;
				}
			}
			
		}
    }

	 List<DataStatisticsVO> stockList = new ArrayList<DataStatisticsVO>();
	 DataStatisticsVO dataStatisticVo1 = new DataStatisticsVO();
	 dataStatisticVo1.setStockName("停牌");
	 dataStatisticVo1.setStockNum(suspensioNum);
	 dataStatisticVo1.setStockType("0");
	 stockList.add(dataStatisticVo1);
	 
	 DataStatisticsVO dataStatisticVo2 = new DataStatisticsVO();
	 dataStatisticVo2.setStockName("跌停");
	 dataStatisticVo2.setStockNum(stockLimit);
	 dataStatisticVo2.setStockType("1");
	 stockList.add(dataStatisticVo2);
	 
	 DataStatisticsVO dataStatisticVo3 = new DataStatisticsVO();

	 dataStatisticVo3.setStockName("-9");
	 dataStatisticVo3.setStockNum(negativeOf9);
	 dataStatisticVo3.setStockType("2");
	 stockList.add(dataStatisticVo3);
	 DataStatisticsVO dataStatisticVo4 = new DataStatisticsVO();

	 dataStatisticVo4.setStockName("-8");
	 dataStatisticVo4.setStockNum(negativeOf8);
	 dataStatisticVo4.setStockType("2");
	 stockList.add(dataStatisticVo4);
	 DataStatisticsVO dataStatisticVo5 = new DataStatisticsVO();

	 dataStatisticVo5.setStockName("-7");
	 dataStatisticVo5.setStockNum(negativeOf7);
	 dataStatisticVo5.setStockType("2");
	 stockList.add(dataStatisticVo5);
	 DataStatisticsVO dataStatisticVo6 = new DataStatisticsVO();

	 dataStatisticVo6.setStockName("-6");
	 dataStatisticVo6.setStockNum(negativeOf6);
	 dataStatisticVo6.setStockType("2");
	 stockList.add(dataStatisticVo6);
	 DataStatisticsVO dataStatisticVo7 = new DataStatisticsVO();

	 dataStatisticVo7.setStockName("-5");
	 dataStatisticVo7.setStockNum(negativeOf5);
	 dataStatisticVo7.setStockType("2");
	 stockList.add(dataStatisticVo7);
	 DataStatisticsVO dataStatisticVo8 = new DataStatisticsVO();

	 dataStatisticVo8.setStockName("-4");
	 dataStatisticVo8.setStockNum(negativeOf4);
	 dataStatisticVo8.setStockType("2");
	 stockList.add(dataStatisticVo8);
	 DataStatisticsVO dataStatisticVo9 = new DataStatisticsVO();

	 dataStatisticVo9.setStockName("-3");
	 dataStatisticVo9.setStockNum(negativeOf3);
	 dataStatisticVo9.setStockType("2");
	 stockList.add(dataStatisticVo9);
	 
	 DataStatisticsVO dataStatisticVo10 = new DataStatisticsVO();

	 dataStatisticVo10.setStockName("-2");
	 dataStatisticVo10.setStockNum(negativeOf2);
	 dataStatisticVo10.setStockType("2");
	 stockList.add(dataStatisticVo10);
	 
	 DataStatisticsVO dataStatisticVo11 = new DataStatisticsVO();

	 dataStatisticVo11.setStockName("-1");
	 dataStatisticVo11.setStockNum(negativeOf1);
	 dataStatisticVo11.setStockType("2");
	 stockList.add(dataStatisticVo11);			
	 
	 DataStatisticsVO dataStatisticVo13 = new DataStatisticsVO();
	 dataStatisticVo13.setStockName("0");
	 dataStatisticVo13.setStockNum(stockNoChange);
	 dataStatisticVo13.setStockType("3");
	 stockList.add(dataStatisticVo13);

	 DataStatisticsVO dataStatisticVo15 = new DataStatisticsVO();
	 dataStatisticVo15.setStockName("1");
	 dataStatisticVo15.setStockNum(riseOf1);
	 dataStatisticVo15.setStockType("4");
	 stockList.add(dataStatisticVo15);
	 
	 DataStatisticsVO dataStatisticVo16 = new DataStatisticsVO();
	 dataStatisticVo16.setStockName("2");
	 dataStatisticVo16.setStockNum(riseOf2);
	 dataStatisticVo16.setStockType("4");
	 stockList.add(dataStatisticVo16);
	 DataStatisticsVO dataStatisticVo17 = new DataStatisticsVO();

	 dataStatisticVo17.setStockName("3");
	 dataStatisticVo17.setStockNum(riseOf4);
	 dataStatisticVo17.setStockType("4");
	 stockList.add(dataStatisticVo17);
	 DataStatisticsVO dataStatisticVo18 = new DataStatisticsVO();

	 dataStatisticVo18.setStockName("4");
	 dataStatisticVo18.setStockNum(riseOf4);
	 dataStatisticVo18.setStockType("4");
	 stockList.add(dataStatisticVo18);
	 DataStatisticsVO dataStatisticVo19 = new DataStatisticsVO();

	 dataStatisticVo19.setStockName("5");
	 dataStatisticVo19.setStockNum(riseOf5);
	 dataStatisticVo19.setStockType("4");
	 stockList.add(dataStatisticVo19);
	 DataStatisticsVO dataStatisticVo20 = new DataStatisticsVO();

	 dataStatisticVo20.setStockName("6");
	 dataStatisticVo20.setStockNum(riseOf6);
	 dataStatisticVo20.setStockType("4");
	 stockList.add(dataStatisticVo20);
	 DataStatisticsVO dataStatisticVo21 = new DataStatisticsVO();

	 dataStatisticVo21.setStockName("7");
	 dataStatisticVo21.setStockNum(riseOf7);
	 dataStatisticVo21.setStockType("4");
	 stockList.add(dataStatisticVo21);
	 DataStatisticsVO dataStatisticVo22 = new DataStatisticsVO();

	 dataStatisticVo22.setStockName("8");
	 dataStatisticVo22.setStockNum(riseOf8);
	 dataStatisticVo22.setStockType("4");
	 stockList.add(dataStatisticVo22);
	 DataStatisticsVO dataStatisticVo23 = new DataStatisticsVO();

	 dataStatisticVo23.setStockName("9");
	 dataStatisticVo23.setStockNum(riseOf9);
	 dataStatisticVo23.setStockType("4");
	 stockList.add(dataStatisticVo23);
	 DataStatisticsVO dataStatisticVo24 = new DataStatisticsVO();

	 dataStatisticVo24.setStockName("涨停");
	 dataStatisticVo24.setStockNum(stockTrading);
	 dataStatisticVo24.setStockType("5");
	 stockList.add(dataStatisticVo24);
	 
	 new CreateChart().creatBarChart(stockList, "个股涨跌分析统计表",sumStock);
	 
	 List<DataStatisticsVO>  marketStockList= new ArrayList<DataStatisticsVO>();
	 for(int i =0 ;i < 8 ; i++ ){
		 DataStatisticsVO data = new DataStatisticsVO();
		 if(i==0){  //涨停
			 data.setStockName("涨停");
			 data.setStockNum(stockTrading);
			 
		 }else if(i==1){  //涨停一字板
			 data.setStockName("涨停一字板");
			 data.setStockNum(stockTradingYiBan);
			 
		 }else if(i==2){ //涨停换手板
			 data.setStockName("涨停换手板");
			 data.setStockNum(stockTradingHuan);
			 
		 }else if(i==3){ //跌停换手板
			 data.setStockName("跌停");
			 data.setStockNum(stockLimit);
			 
		 }else if(i==4){  //跌停一字板
			 data.setStockName("跌停一字板");
			 data.setStockNum(stockLimitYiBan);
		 }else if(i==5){ //跌停换手板
			 data.setStockName("涨停换手板");
			 data.setStockNum(stockLimitHuan);
		 }else if(i==6){ //涨停大于等于5%
			 data.setStockName("涨幅大于5%");
			 data.setStockNum(riseOf6+riseOf7+riseOf8+riseOf9+riseOf10);
		 }else if(i==7){//跌停大于等于5%
			 data.setStockName("涨幅大于5%");
			 data.setStockNum(negativeOf6+negativeOf7+negativeOf8+negativeOf9+negativeOf10);
		 }
		 data.setStockType("0");
		 marketStockList.add(data);				 
	 }
	 new MarketEnvirentAss().creatBarChart(marketStockList, "市场环境评估",sumStock);

	 returnDate.setIfSuccess("1");
	 returnDate.setMessage("操作成功");
	*/
	
	public static void main(String[] args) {
		
		String content = "";
        System.out.println(Double.valueOf(content));
	}
	
	
}
