package allData.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import allData.dataVO.StockMainVO;
import allData.dataVO.StockMessVO;
import constant.ConstantData;

public class DataConnUtils {
	//连接数据库
	public static Connection connDatabase() throws SQLException, ClassNotFoundException {
		Class.forName(ConstantData.driverName);
		Connection driverManager =DriverManager.getConnection(ConstantData.url, ConstantData.user, ConstantData.password);
		return driverManager;
	}
	
	
	
	
	//插入数据
	public void insertStockMain(List<StockMainVO> mainList) throws ClassNotFoundException, SQLException{
		String sql = "insert into t_stock_main(ID,S_STOCK_TIME,S_STOCK_CODE,S_STOCK_NAME,S_STOCK_INCREASE,S_CURRENT_PRICE,S_GO_UP_DROP,S_TURNOVER_RATE,S_TOTAL_AMOUNT,S_FREE_CAPITALISATION,S_OPENING_PRICE,S_STOCK_PRICE,S_HIGHEST_PRICE,S_MINIMUM_PRICE,S_INDUTRY_OWNED) values(S_T_STOCK_MAIN_ID.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection conn = DataConnUtils.connDatabase();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		if(mainList != null && mainList.size() > 0){
			for(StockMainVO mainVO : mainList){
				preparedStatement.setString(1, mainVO.getStockTime());
				preparedStatement.setString(2, mainVO.getStockCode());
				preparedStatement.setString(3, mainVO.getStockName());
				preparedStatement.setDouble(4, mainVO.getStockIncrease());
				preparedStatement.setDouble(5, mainVO.getCurrentPrice());
				preparedStatement.setDouble(6, mainVO.getGoUpDrop());
				preparedStatement.setDouble(7, mainVO.getTurnoverRate());
				preparedStatement.setDouble(8, mainVO.getTotalAmount());
				preparedStatement.setDouble(9, mainVO.getFreeCapitalisation());
				preparedStatement.setDouble(10, mainVO.getOpeningPrice());
				preparedStatement.setDouble(11, mainVO.getStockPrice());
				preparedStatement.setDouble(12, mainVO.getHightestPrice());
				preparedStatement.setDouble(13, mainVO.getMinimumPrice());
				preparedStatement.setString(14, mainVO.getIndutryOwned());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
		}
		conn.close();
		
	}
	
	public void insertStockMess(StockMessVO stockMessVO){
		String sql = "insert into t_stock_mess(ID,"
				+ "S_STOCK_TIME,"
				+ "S_STOCK_RISE_NUMBER,"
				+ "S_STOCK_DROP_NUMBER,"
				+ "S_STOCK_ALL_NUMBER,"
				+ "S_STOCK_STOP_NUMBER,"
				+ "S_STOCK_EARNING_EFFECT,"
				+ ")"
				+ "values(S_T_STOCK_MESS.NEXTVAL,?,?,?,?,?,?)";
		
		
	}
	

}
