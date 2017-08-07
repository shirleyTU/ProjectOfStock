package allData.excel;

import java.util.List;

import allData.dataVO.StockMessVO;
import allData.dataVO.StockUpDropVO;

public class UpDropAndMessToDB extends Thread {
	List<StockUpDropVO> stockUpDropList;
	StockMessVO stockMessVO;
	public UpDropAndMessToDB(List<StockUpDropVO> stockUpDropList,StockMessVO stockMessVO){
		this.stockUpDropList = stockUpDropList;
		this.stockMessVO = stockMessVO;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		new GetExcelData().saveUpDropDataToDB(stockUpDropList);
		new GetExcelData().saveMesDataToDB(stockMessVO);
	}
}
