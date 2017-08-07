package commonUtils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class RespUtils {
	public void responseStr(HttpServletResponse response, String resultStr) {
		// TODO Auto-generated method stub
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().println(resultStr.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
