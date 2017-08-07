package commonUtils;

import java.math.BigDecimal;

public class Utils {
	public static Boolean strIsNum(String str) {
		if(str != null && !"".equals(str.trim())){
			String pattenStr = "^(-?\\d+)(\\.\\d+)?$";
			return str.matches(pattenStr);		
		}else{
			return false;
		}		 
	}
	
	public static BigDecimal getNumOf2(String str) {
		if(str != null && !"".equals(str.trim())){
			return new BigDecimal(str).setScale(2, BigDecimal.ROUND_HALF_UP);	
		}else{
			return null;
		}		 
	}
	
	public static String checkStr(String str){
		if(str != null && str.trim().contains("--")){
			str =str.replace("--", "");
		}
		if(str != null && (str.contains("↓") || str.trim().contains("↑"))){
			str =str.replace("↓", "").replace("↑", "");
		}
		return str;
	}
	
	
	//将字符串首字母大写
	public static String strCapitalize(String str){
		if(str != null && !"".equals(str.trim().length())){
			if(str.length() > 1){
				str = str.substring(0, 1).toUpperCase() + str.substring(1);
			}else{
				str = str.substring(0, 1).toUpperCase();
			}
		}else{
			str = "";
		}
		
		return str;
	}
	
	public static String strAddSet(String str){
		if(str != null && !"".equals(str.trim().length())){
			str = "set"+str;
		}else{
			str = "";
		}
		return str;
	}
	public static void main(String[] args) {
		Utils.strCapitalize("assss");
	}
}
