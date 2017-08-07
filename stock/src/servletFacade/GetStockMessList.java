package servletFacade;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import allData.dao.DataConnUtils;
import commonUtils.RespUtils;

/**
 * Servlet implementation class GetStockMessList
 */
@WebServlet("/getStockMessList")
public class GetStockMessList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStockMessList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//大盘信息
		String inlineCheckbox1Value = request.getParameter("inlineCheckbox1Value");
		//真实涨停
		String inlineCheckbox2Value = request.getParameter("inlineCheckbox2Value");
		//真实跌停
		String inlineCheckbox3Value = request.getParameter("inlineCheckbox3Value");
		int inlineCheckbox4Value = Integer.parseInt(request.getParameter("inlineCheckbox4Value"));
		//时间
		String calendarValue = request.getParameter("calendarValue").replaceAll("-", "");
		int betweenTime = Integer.parseInt(request.getParameter("betweenTime"));
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		
		
		
		JSONObject json = new JSONObject();
		JSONArray resultSetArray1 = new JSONArray();
		JSONArray resultSetArray2 = new JSONArray();
		JSONArray resultSetArray3 = new JSONArray();
		String ifSuccess = "0";
		String message = "";
		try {
			Date date = format.parse(calendarValue);
			Calendar c =Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DAY_OF_YEAR, 0-betweenTime);
			String beforeDateStr = format.format(c.getTime());
			Connection conn = DataConnUtils.connDatabase();
			Statement statement1 = conn.createStatement();
			Statement statement2Of1 = conn.createStatement();
			if("1".equals(inlineCheckbox1Value)){
				String sql1 = "select t.*,rowid from t_stock_mess t where t.s_stock_time = '"+calendarValue+"'";
				ResultSet resultSet1 = statement1.executeQuery(sql1);
				String sql2 = "select t.*,rowid from t_stock_mess t where t.s_stock_time = '"+beforeDateStr+"'";
				ResultSet resultSet2Of1 = statement2Of1.executeQuery(sql2);
				if(resultSet1 != null && resultSet2Of1 != null){
					if(resultSet1.next() && resultSet2Of1.next()){
							JSONObject jsonResult1 = new JSONObject();
							jsonResult1.put("stockRiseNumber",resultSet1.getInt("s_stock_rise_number"));
							if(resultSet1.getInt("s_stock_rise_number")>resultSet2Of1.getInt("s_stock_rise_number")){
								jsonResult1.put("stockRiseNumberFlag","↑");
							}else if(resultSet1.getInt("s_stock_rise_number")<resultSet2Of1.getInt("s_stock_rise_number")){
								jsonResult1.put("stockRiseNumberFlag","↓");
							}else{
								jsonResult1.put("stockRiseNumberFlag","");
							}
							jsonResult1.put("stockDropNumber",resultSet1.getInt("s_stock_drop_number"));
							if(resultSet1.getInt("s_stock_drop_number")>resultSet2Of1.getInt("s_stock_drop_number")){
								jsonResult1.put("stockDropNumberFlag","↑");
							}else if(resultSet1.getInt("s_stock_drop_number")<resultSet2Of1.getInt("s_stock_drop_number")){
								jsonResult1.put("stockDropNumberFlag","↓");
							}else{
								jsonResult1.put("stockDropNumberFlag","");
							}
							jsonResult1.put("stockAllNumber",resultSet1.getInt("s_stock_all_number"));
							jsonResult1.put("stockStopNumber",resultSet1.getInt("s_stock_stop_number"));
							jsonResult1.put("stockEarningEffect",resultSet1.getDouble("s_stock_earning_effect"));
							if(resultSet1.getDouble("s_stock_earning_effect") > resultSet2Of1.getDouble("s_stock_earning_effect")){
								jsonResult1.put("stockEarningEffectFlag","↑");
							}else if(resultSet1.getDouble("s_stock_earning_effect") < resultSet2Of1.getDouble("s_stock_earning_effect")){
								jsonResult1.put("stockEarningEffectFlag","↓");
							}else{
								jsonResult1.put("stockEarningEffectFlag","");
							}
							jsonResult1.put("stockSealingPlate",resultSet1.getDouble("s_stock_sealing_plate"));
							if(resultSet1.getDouble("s_stock_sealing_plate")>resultSet2Of1.getDouble("s_stock_sealing_plate")){
								jsonResult1.put("stockSealingPlateFlag","↑");

							}else if(resultSet1.getDouble("s_stock_sealing_plate")<resultSet2Of1.getDouble("s_stock_sealing_plate")){
								jsonResult1.put("stockSealingPlateFlag","↓");
							}else{
								jsonResult1.put("stockSealingPlateFlag","");
							}
							String sql3 = "select * from t_stock_main a where 1=2";
							if(inlineCheckbox4Value < 0){
								sql3 = "select * from (select * from t_stock_main a where s_stock_increase <> -10000 and s_stock_time = '"+calendarValue+"' order by a.s_stock_increase asc)  where rownum <= "+(0-inlineCheckbox4Value);
							}else{
								sql3 = "select * from (select * from t_stock_main a where s_stock_increase <> -10000 and s_stock_time = '"+calendarValue+"' order by a.s_stock_increase desc)  where rownum <= "+inlineCheckbox4Value;
							}
							Statement statement3Of1  = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
							ResultSet resultSet3Of1 = statement3Of1.executeQuery(sql3);
							if(resultSet3Of1 != null){
								if(resultSet3Of1.last()){
									jsonResult1.put("stockDropLastOf40", resultSet3Of1.getDouble("s_stock_increase"));
								}
							}
							resultSetArray1.add(jsonResult1);
					}
				}
			}
			
			
			if("1".equals(inlineCheckbox2Value)){
				String sql2 = "select t.*,rowid from t_stock_up_drop t where t.s_stock_time_id = '"+calendarValue+"' and t.s_stock_up_type = '1'";
				Statement statement2 = conn.createStatement();
				ResultSet resultSet2 = statement1.executeQuery(sql2);
				if(resultSet2 != null){
					while(resultSet2.next()){
						JSONObject jsonResult2 = new JSONObject();
						jsonResult2.put("stockCode",resultSet2.getString("S_STOCK_CODE"));
						jsonResult2.put("stockName",resultSet2.getString("S_STOCK_NAME"));
						jsonResult2.put("stockUpType",resultSet2.getString("S_STOCK_UP_TYPE"));
						jsonResult2.put("currentPrice",resultSet2.getDouble("S_CURRENT_PRICE"));
						resultSetArray2.add(jsonResult2);
					}
				}
			}
			
			if("1".equals(inlineCheckbox3Value)){
				String sql3 = "select t.*,rowid from t_stock_up_drop t where t.s_stock_time_id = '"+calendarValue+"' and t.s_stock_up_type = '3'";
				Statement statement3 = conn.createStatement();
				ResultSet resultSet3 = statement1.executeQuery(sql3);
				if(resultSet3 != null){
					while(resultSet3.next()){
						JSONObject jsonResult3 = new JSONObject();
						jsonResult3.put("stockCode",resultSet3.getString("S_STOCK_CODE"));
						jsonResult3.put("stockName",resultSet3.getString("S_STOCK_NAME"));
						jsonResult3.put("stockUpType",resultSet3.getString("S_STOCK_UP_TYPE"));
						jsonResult3.put("currentPrice",resultSet3.getDouble("S_CURRENT_PRICE"));
						resultSetArray3.add(jsonResult3);
					}
				}
			}
			
			ifSuccess = "1";
			message = "操作成功";
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			ifSuccess = "0";
			message = "操作失败:"+e.getMessage();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ifSuccess = "0";
			message = "操作失败:"+e.getMessage();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			ifSuccess = "0";
			message = "操作失败:"+e.getMessage();
		}
		json.put("ifSuccess", ifSuccess);
		json.put("message", message);
		json.put("allStockMess", resultSetArray1);
		json.put("realUpStockList", resultSetArray2);
		json.put("realDropStockList", resultSetArray3);
		new RespUtils().responseStr(response,json.toString());

	}

}
