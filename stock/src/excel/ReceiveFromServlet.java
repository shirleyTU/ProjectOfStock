package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.*;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import allData.excel.GetExcelData;
import commonUtils.CreateChart;
import commonUtils.MarketEnvirentAss;
import commonUtils.RespUtils;
import commonUtils.Utils;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


/**
 * Servlet implementation class ReceiveFromServlet
 */
@WebServlet(description = "接收上传的excel表格", urlPatterns = { "/ReceiveFromServlet" })
public class ReceiveFromServlet extends HttpServlet {
	static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReceiveFromServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String respCode = "";
		String respMessage = "";
		String dateStr = new SimpleDateFormat("HHmmssSSS").format(new Date());
		String savePath = "D:/excel/";
		String realPath = "";   //真实的存放地址
		File file = new File(savePath);
		 //判断上传文件的保存目录是否存在 
		 if (!file.exists() && !file.isDirectory()) {
			 System.out.println(savePath+"目录不存在，需要创建");
		 	 file.mkdir();
		 }
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(5*1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(Integer.valueOf(1024)*1024*1024);
		try {
			List<FileItem> items = upload.parseRequest(request);
			 for (FileItem item : items) {  
		            if(!item.isFormField()){	
		            	InputStream inputis = item.getInputStream();
		            	realPath = savePath + item.getName();
		            	FileOutputStream out = new FileOutputStream(realPath);
		            	byte buffer[] = new byte[1024];
		            	int len = 0;
		            	while((len=inputis.read(buffer))>0){
		            		out.write(buffer, 0, len);		            		
		            	}
		            	//将数据信息存入数据库		            	
		            	out.close();
		            	inputis.close();
		            	new GetExcelData().saveAllDataToDatabase(realPath);
		        } 
			 }
			 respCode = "1";
			 respMessage = "操作成功";
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
			respCode = "0";
			respMessage = "操作失败："+e.getMessage();
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{")
		.append("\"ifSuccess\":\""+respCode+"\",")
		.append("\"messsage\":\""+respMessage+"\"")
		.append("}");
		new RespUtils().responseStr(response,sb.toString());
       
	}
	

	
	

}
