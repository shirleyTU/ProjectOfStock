package servletFacade;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import allData.dao.DataConnUtils;
import allData.dataVO.StockMainVO;
import allData.dataVO.StockUpDropVO;
import commonUtils.RespUtils;

/**
 * Servlet implementation class GrailEffectServlet
 */
@WebServlet("/grailEffectServlet")
public class GrailEffectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GrailEffectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String effectRaise = "0";
		String ifSuccess = "";
		String messsage = "";
		JSONObject jsonStr = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		try {
			String calendarValue = request.getParameter("calendarValue").replaceAll("-", "");
			String num = request.getParameter("num");
			String redioValue = request.getParameter("redioValue");
			int betweenTime = 0 - Integer.parseInt(request.getParameter("betweenTime"));
			List<StockUpDropVO> stockUpDropList = new ArrayList<StockUpDropVO>();
			String sql1 = "";
			
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Date date = format.parse(calendarValue);
			Calendar c =Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DAY_OF_YEAR, betweenTime);
			String beforeDateStr = format.format(c.getTime());
			
			
			
			if("1".equals(redioValue)){
				sql1 = "select * from (select * from t_stock_main order by s_stock_increase desc) where s_stock_increase < 0.4 and round(s_stock_price*1.1,2) >= round(s_current_price,2) and s_highest_price <> s_minimum_price and rownum <= "+num+" and s_stock_time = '"+beforeDateStr+"'";   //获取真实涨停股
			}
			if("0".equals(redioValue)){
				sql1 = "select * from (select * from t_stock_main order by s_stock_increase asc) where s_stock_increase <> -10000 and round(s_stock_price*0.9,2) <= round(s_current_price,2) and s_minimum_price <> s_highest_price and rownum <= "+num+" and s_stock_time = '"+beforeDateStr+"'";   //获取真实涨停股
			}
			Connection conn = DataConnUtils.connDatabase();
			Statement statement1 = conn.createStatement();
			ResultSet resultSet1 = statement1.executeQuery(sql1);
			Map<String, String> result1Map = new HashMap<>();
			
			if(resultSet1 != null){
				while(resultSet1.next()){
					result1Map.put(resultSet1.getString("s_stock_code"), String.format("%.2f",resultSet1.getDouble("s_stock_Increase")*100)+"%");
					StockUpDropVO upDropVO = new StockUpDropVO();
					upDropVO.setStockTimeId(resultSet1.getString("s_stock_time"));
					upDropVO.setStockCode(resultSet1.getString("s_stock_code"));
					upDropVO.setStockName(resultSet1.getString("s_stock_name"));
					upDropVO.setStockUpType("1");
					upDropVO.setCurrentPrice(resultSet1.getDouble("s_current_price"));
					stockUpDropList.add(upDropVO);
				}
			}
			
			int stockRiseNumber = 0;    //有效数目
			if(stockUpDropList != null && stockUpDropList.size() > 0 ){
				String upStr = "''";
				for(int i = 0 ; i < stockUpDropList.size() ; i++){
					upStr = upStr + ",'" + stockUpDropList.get(i).getStockCode() +"'";
				}
				if(!"''".equals(upStr)){
					String sql2 = "select * from t_stock_main t where 1 = 2";
					if("1".equals(redioValue)){
						sql2 = "select * from t_stock_main t where t.s_stock_time = '"+calendarValue+"' and s_stock_code in ("+upStr+") order by s_stock_increase desc";
					}
					if("0".equals(redioValue)){
						sql2 = "select * from t_stock_main t where t.s_stock_time = '"+calendarValue+"' and s_stock_code in ("+upStr+") order by s_stock_increase asc";
					}
					Statement statement2 = conn.createStatement();
					ResultSet resultSet2 = statement1.executeQuery(sql2);
					if(resultSet2 != null){
						while(resultSet2.next()){
							JSONObject jsonObj = new JSONObject();
							jsonObj.put("stockTime", resultSet2.getString("s_stock_time"));
							jsonObj.put("stockCode", resultSet2.getString("s_stock_code"));
							jsonObj.put("stockName", resultSet2.getString("s_stock_name"));
							jsonObj.put("stockPrice", resultSet2.getDouble("s_stock_price"));
							jsonObj.put("currentPrice", resultSet2.getDouble("s_current_price"));
							jsonObj.put("stockIncrease", String.format("%.2f",resultSet2.getDouble("s_stock_Increase")*100)+"%");
							jsonObj.put("yesterdayStockIncrease",result1Map.get(resultSet2.getString("s_stock_code")) );

							if(resultSet2.getDouble("s_stock_increase") > 0){
								jsonObj.put("colorType", "1");
								stockRiseNumber++;
								System.out.println("记录："+resultSet2.getString("s_stock_code")+"---"+resultSet2.getString("s_stock_name")+"---"+resultSet2.getDouble("s_stock_price")+"---"+resultSet2.getDouble("s_current_price")+"--- 大于0");
							}else{
								jsonObj.put("colorType", "0");
								System.out.println("记录："+resultSet2.getString("s_stock_code")+"---"+resultSet2.getString("s_stock_name")+"---"+resultSet2.getDouble("s_stock_price")+"---"+resultSet2.getDouble("s_current_price")+"--- 小于0");
							}
							jsonArray.add(jsonObj);
						}
					}
					
				}
			}
		    effectRaise = String.format("%.4f",((double)stockRiseNumber / Integer.parseInt(num)));
			System.out.println("涨幅前"+num+"的赚钱效应：  "+effectRaise);
			jsonStr.put("mainList", jsonArray);
			jsonStr.put("effectRaise", effectRaise);
			ifSuccess = "1";
			messsage = "操作成功";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			ifSuccess = "0";
			messsage = "操作异常:"+e.getMessage();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ifSuccess = "0";
			messsage = "操作异常:"+e.getMessage();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			ifSuccess = "0";
			messsage = "操作异常:"+e.getMessage();
		}
		jsonStr.put("ifSuccess", ifSuccess);
		jsonStr.put("messsage", messsage);
		new RespUtils().responseStr(response,jsonStr.toString());
	}

}
