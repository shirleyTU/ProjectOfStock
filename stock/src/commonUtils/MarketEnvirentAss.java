package commonUtils;

import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

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

import excel.DataStatisticsVO;

public class MarketEnvirentAss {

	public String creatBarChart(List<DataStatisticsVO> stockList,String xName,int sumStock){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for(DataStatisticsVO temp : stockList){
			if(temp.getStockType().equals("0")){
				dataset.addValue(temp.getStockNum(), "停牌", temp.getStockName());	
			}else if(temp.getStockType().equals("1")){
				dataset.addValue(temp.getStockNum(), "跌停", temp.getStockName());	
			}else if(temp.getStockType().equals("2")){
				dataset.addValue(temp.getStockNum(), "下跌", temp.getStockName());	
			}else if(temp.getStockType().equals("3")){
				dataset.addValue(temp.getStockNum(), "不变", temp.getStockName());	
			}else if(temp.getStockType().equals("4")){
				dataset.addValue(temp.getStockNum(), "上涨", temp.getStockName());	
			}else if(temp.getStockType().equals("5")){
				dataset.addValue(temp.getStockNum(), "涨停", temp.getStockName());	
			}		
		}		
		JFreeChart chart = ChartFactory.createBarChart("所有股票总数:"+sumStock, " ", xName,dataset, PlotOrientation.VERTICAL, true, false, false);
		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();  
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();  
        CategoryAxis domainAxis = categoryplot.getDomainAxis();  
        TextTitle textTitle = chart.getTitle();
        
        
        BarRenderer renderer = new BarRenderer();
		renderer.setIncludeBaseInRange(true);
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());  		
		renderer.setDrawBarOutline(true); // 设置柱子边框可见  
		renderer.setMaximumBarWidth(0.5D);
		renderer.setItemMargin(0.01D);
		renderer.setBaseItemLabelsVisible(true, true);
		renderer.setBaseOutlinePaint(Color.BLACK); // 设置柱子边框颜色   

        //将X轴显示在下方  
        categoryplot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);  
  
        //设置X轴参数格式  
        DecimalFormat df = new DecimalFormat("0");  
        numberaxis.setNumberFormatOverride(df); 
		//设置标题字体样式          
		textTitle.setFont(new Font("黑体", Font.PLAIN, 20)); 
		
		
		categoryplot.setRenderer(renderer); // 给柱图添加呈现器  
        textTitle.setFont(new Font("黑体", Font.PLAIN, 20));  
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 18));  
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 20));  
        numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 20));  
        numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 20));  
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 20));
        java.io.File file = new java.io.File("D:/barchat");
        if(!file.exists()){
        	file.mkdirs();
        }
         try {
        	 FileOutputStream fos = new FileOutputStream("D:/barchat/barchat.jpg");
        	 ChartUtilities.writeChartAsPNG(fos, chart, 1200, 800);
        	 fos.close();
        }
         catch (FileNotFoundException e) {
        	 e.printStackTrace();         
         } catch (IOException e) { 
        	 e.printStackTrace();
        } 
		return "D:/barchat/barchat2.jpg";
	}




}
