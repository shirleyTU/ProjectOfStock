package allData.facade;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import allData.dao.DataConnUtils;
import allData.dataVO.StockMainVO;
import allData.dataVO.StockMessVO;
import allData.dataVO.StockUpDropVO;
import allData.excel.UpDropAndMessToDB;

/**
 * 
 *大盘数据操作流程
 * @author tumx
 *
 */
public class AllDataOprFacade {
	public void statisticalMessData(List<StockMainVO> mainList) throws ClassNotFoundException, SQLException{
		//SELECT t.s_stock_code,t.s_stock_name,round(t.s_stock_price*1.1,2) f, round(t.s_current_price,2) g from t_stock_main t where round(t.s_stock_price*1.1,2) = round(t.s_current_price,2) and t.s_opening_price <= t.s_highest_price and t.s_current_price > t.s_minimum_price and s_current_price <> '-100000000000000';

		int stockRiseNumber = 0;    //涨停数目
		int stockDropNumber = 0;   //跌停数目
		int stockAllNumber = 0;    //所有股票数目
		int stockStopNumber = 0;   //停牌数目
		int stockThanZeroNumber = 0; //股票涨幅大于0的数目
		double stockEarningEffect = -10000;  //大盘赚钱效应
		double stockSealingPlate = 0;  //大盘封板率
		StockMessVO stockMessVO = new StockMessVO();
		List<StockUpDropVO> stockUpDropList = new ArrayList<StockUpDropVO>();
		
		if(mainList != null && mainList.size() > 0){
			stockMessVO.setStockTime(mainList.get(0).getStockTime());
			stockAllNumber = mainList.size();
			stockMessVO.setStockAllNumber(stockAllNumber);
			//真实涨停股
			String sql1 = "SELECT t.s_stock_code,t.s_stock_name,t.s_current_price from t_stock_main t where round(t.s_stock_price*1.1,2) = round(t.s_current_price,2) and t.s_opening_price <= t.s_highest_price and t.s_current_price > t.s_minimum_price and t.s_stock_time = '"+mainList.get(0).getStockTime()+"'";   //获取真实涨停股
			//真实跌停股
			String sql2 = "SELECT t.s_stock_code,t.s_stock_name,t.s_current_price from t_stock_main t where round(t.s_stock_price*0.9,2) = round(t.s_current_price,2) and t.s_opening_price >= t.s_minimum_price and t.s_current_price < t.s_highest_price and t.s_stock_time = '"+mainList.get(0).getStockTime()+"'";   //获取真实涨停股
			//停牌股票
			String sql3 = "select t.s_stock_code,t.s_stock_name,t.s_current_price from t_stock_main t where t.s_stock_increase like  '-10000%' and t.s_stock_time = '"+mainList.get(0).getStockTime()+"'";
			//涨幅大于0的股票数
			String sql4 = "select count(*) from t_stock_main t where t.s_stock_increase > 0 and t.s_stock_time = '"+mainList.get(0).getStockTime()+"'";
			//大盘封板率
			String sql5 = "select count(*) from t_stock_main t where round(t.s_highest_price,2) = round(t.s_stock_price*1.1,2) and t.s_highest_price <> t.s_minimum_price and t.s_stock_time = '"+mainList.get(0).getStockTime()+"'";
			
			Connection conn = DataConnUtils.connDatabase();
			Statement statement1 = conn.createStatement();
			ResultSet resultSet1 = statement1.executeQuery(sql1);
			if(resultSet1 != null){
				while(resultSet1.next()){
					StockUpDropVO upDropVO = new StockUpDropVO();
					upDropVO.setStockTimeId(mainList.get(0).getStockTime());
					upDropVO.setStockCode(resultSet1.getString("s_stock_code"));
					upDropVO.setStockName(resultSet1.getString("s_stock_name"));
					upDropVO.setStockUpType("1");
					upDropVO.setCurrentPrice(resultSet1.getDouble("s_current_price"));
					stockUpDropList.add(upDropVO);
					stockRiseNumber++;
				}
			}
			
			Statement statement2 = conn.createStatement();
			ResultSet resultSet2 = statement2.executeQuery(sql2);

			if(resultSet2 != null){
				while(resultSet2.next()){
					StockUpDropVO upDropVO = new StockUpDropVO();
					upDropVO.setStockTimeId(mainList.get(0).getStockTime());
					upDropVO.setStockCode(resultSet2.getString("s_stock_code"));
					upDropVO.setStockName(resultSet2.getString("s_stock_name"));
					upDropVO.setStockUpType("3");
					upDropVO.setCurrentPrice(resultSet2.getDouble("s_current_price"));
					stockUpDropList.add(upDropVO);
					stockDropNumber++;
				}
			}
			
			Statement statement3 = conn.createStatement();
			ResultSet resultSet3 = statement3.executeQuery(sql3);

			if(resultSet3 != null){
				while(resultSet3.next()){
					StockUpDropVO upDropVO = new StockUpDropVO();
					upDropVO.setStockTimeId(mainList.get(0).getStockTime());
					upDropVO.setStockCode(resultSet3.getString("s_stock_code"));
					upDropVO.setStockName(resultSet3.getString("s_stock_name"));
					upDropVO.setStockUpType("0");
					upDropVO.setCurrentPrice(resultSet3.getDouble("s_current_price"));
					stockUpDropList.add(upDropVO);
					stockStopNumber++;
				}
			}
			Statement statement4 = conn.createStatement();
			ResultSet resultSet4 = statement4.executeQuery(sql4);
			
			if(resultSet4 != null){
				while(resultSet4.next()){
					
					stockThanZeroNumber = resultSet4.getInt(1);
					System.out.println("大于0的股票："+stockThanZeroNumber);
				}
			}
			
			
			Statement statement5 = conn.createStatement();
			ResultSet resultSet5 = statement4.executeQuery(sql5);
			//有封板的股票
			if(resultSet5 != null){
				while(resultSet5.next()){
					if(resultSet5.getInt(1) != 0){
						stockSealingPlate = ((double)stockRiseNumber) / resultSet5.getInt(1);
					}
				}
			}
			
			
			String effectStr = String.format("%.4f", ((double)stockThanZeroNumber) / (stockAllNumber - stockStopNumber));
			stockMessVO.setStockRiseNumber(stockRiseNumber);
			stockMessVO.setStockStopNumber(stockStopNumber);
			stockMessVO.setStockDropNumber(stockDropNumber);
			stockMessVO.setStockEarningEffect(Double.parseDouble(effectStr));
			stockMessVO.setStockSealingPlate(Double.parseDouble( String.format("%.4f", stockSealingPlate)));
			UpDropAndMessToDB upDropAndMessToDB = new UpDropAndMessToDB(stockUpDropList, stockMessVO);
			upDropAndMessToDB.start();
			
			
			
			
			conn.close();
		}
	}
	
}
