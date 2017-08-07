package allData.excel;

import java.io.File;
import java.util.*;

import com.sun.org.apache.bcel.internal.classfile.Field;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import allData.dao.DataConnUtils;
import allData.dataVO.StockMainVO;
import allData.dataVO.StockMessVO;
import allData.dataVO.StockUpDropVO;
import allData.facade.AllDataOprFacade;
import commonUtils.Utils;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class GetExcelData {
		public Boolean saveAllDataToDatabase(String filePath) {
			Boolean flag = false;
			try {
				List<StockMainVO> mainList = new ArrayList<StockMainVO>();
				File file = new File(filePath);
				String fileName = file.getName();
				InputStream is = new FileInputStream(file);
				Workbook workBook = Workbook.getWorkbook(is);
				Sheet sheet = workBook.getSheet(0);				
				for(int i = 1; i < sheet.getRows() ; i++){
					StockMainVO stockMainVO = new StockMainVO();
					Class stockMainVOClass = stockMainVO.getClass();
					java.lang.reflect.Field[] fields  = stockMainVOClass.getDeclaredFields();
					stockMainVO.setStockTime(fileName.split("\\.")[0]);
					for(int j = 0; j < sheet.getColumns() ; j++){
						fields[j+2].setAccessible(true);
						String content = Utils.checkStr(sheet.getCell(j, i).getContents().trim()).trim();
						if(content != null){
							if("".equals(content)){
								content = "-1000000";
							}
							String methodName = Utils.strAddSet(Utils.strCapitalize(fields[j+2].getName().toString()));
							Class parameterTypes = fields[j+2].getType();
							Method method = stockMainVOClass.getMethod(methodName, parameterTypes);
							if("java.lang.String".equalsIgnoreCase(parameterTypes.getName())){
								method.invoke(stockMainVO,content);
							}else if("java.lang.Double".equalsIgnoreCase(parameterTypes.getName())){
								method.invoke(stockMainVO,Double.valueOf(content));
							}
						}
					}	
					System.out.println(stockMainVO.toString());
					mainList.add(stockMainVO);
				}	
				new DataConnUtils().insertStockMain(mainList);
				new AllDataOprFacade().statisticalMessData(mainList);
				flag =  true;
			} catch (BiffException | IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			return flag;
		}

		public void saveUpDropDataToDB(List<StockUpDropVO> stockUpDropList) {
			// TODO Auto-generated method stub
			String sql = "insert into t_stock_up_drop values(S_T_STOCK_UP_drop.Nextval,?,?,?,?,?)";

			try {
				if(stockUpDropList != null && stockUpDropList.size() > 0){
					Connection conn = DataConnUtils.connDatabase();
					PreparedStatement preparedStatement = conn.prepareStatement(sql);
					for(StockUpDropVO stockUpDropVO : stockUpDropList){
						preparedStatement.setString(1, stockUpDropVO.getStockTimeId());
						preparedStatement.setString(2,stockUpDropVO.getStockCode());
						preparedStatement.setString(3, stockUpDropVO.getStockName());
						preparedStatement.setString(4, stockUpDropVO.getStockUpType());
						preparedStatement.setDouble(5, stockUpDropVO.getCurrentPrice());
						preparedStatement.addBatch();
					}
					preparedStatement.executeBatch();
					preparedStatement.close();
					conn.close();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

		public void saveMesDataToDB(StockMessVO stockMessVO) {
			// TODO Auto-generated method stub
			String sql = "insert into t_stock_mess values(S_T_STOCK_MESS.Nextval,?,?,?,?,?,?,?)";
			try {
				if(stockMessVO != null){
					Connection conn = DataConnUtils.connDatabase();
					PreparedStatement preparedStatement = conn.prepareStatement(sql);
					preparedStatement.setString(1, stockMessVO.getStockTime());
					preparedStatement.setInt(2, stockMessVO.getStockRiseNumber());
					preparedStatement.setInt(3, stockMessVO.getStockDropNumber());
					preparedStatement.setInt(4, stockMessVO.getStockAllNumber());
					preparedStatement.setInt(5, stockMessVO.getStockStopNumber());
					preparedStatement.setDouble(6, stockMessVO.getStockEarningEffect());
					preparedStatement.setDouble(7, stockMessVO.getStockSealingPlate());

					preparedStatement.execute();
					preparedStatement.close();
					conn.close();
				}
	
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
