/*
*功能:一些表单元素的验证功能
*作者：
*/

//支持嵌入其他校验方法，可以增加自定义校验
//function validater(frmObj)
var context="/aicclaim"
function validater(frmObj, strAlert){
	
	var msgTmp="";
	var msg="";
	
	
	if (strAlert != "" && strAlert != null){ 
		msg = "--" + strAlert + "\n";	
	}
	

	var arrType;
	var arrMsg="";
	var el;
	for(var i=0;i<frmObj.elements.length;i++){
		el=frmObj.elements[i];
		if(el.ValidateType!=null){
			arrType=el.ValidateType.split(",");
			if(el.Msg==null) el.Msg="";
			arrMsg=el.Msg.split(";");
			if(arrType.length==arrMsg.length){
				for(var j=0;j<arrType.length;j++){
					if(!ValidateEl(el,arrType[j])){
						msg+="--"+arrMsg[j]+"\n";
					}
				}
			}
		}
	}
   if(frmObj.elements["vo.password"]!=null){
	 if(frmObj.elements["vo.password"][0]!=null&&frmObj.elements["vo.password"][1]!=null){
		if(frmObj.elements["vo.password"][0].value!=frmObj.elements["vo.password"][1].value){
	      msg+="--二次密码输入不一致,请重新输入"
          frmObj.elements["vo.password"][0].value="";
	      frmObj.elements["vo.password"][1].value="";
	    }
      }
	
	if(frmObj.elements["vo.password"][0]!=null&&frmObj.elements["vo.password"][1]==null){
    	   msg+="--二次密码输入不一致,请重新输入"
          frmObj.elements["vo.password"][0].value="";
	      frmObj.elements["vo.password"][1].value="";

	}
	if(frmObj.elements["vo.password"][0]==null&&frmObj.elements["vo.password"][1]!=null){
    	   msg+="--二次密码输入不一致,请重新输入"
          frmObj.elements["vo.password"][0].value="";
	      frmObj.elements["vo.password"][1].value="";

	}


   }
  

	if(msg!=""){
		alert(msg);
		return false;
	}
	return true;
}




// 为 String 类增加一个 trim 方法
String.prototype.trim = function()
{
    // 用正则表达式将前后空格用空字符串替代。
    return this.replace( /(^\s*)|(\s*$)/g, "" );
}

// 使用正则表达式，检测 s 是否满足模式 re
function checkExp( re, s )
{
	return re.test( s );
}

// 验证是否 字母数字
function isAlphaNumeric( strValue )
{
	// 只能是 A-Z a-z 0-9 之间的字母数字 或者为空
	return checkExp( /^\w*$/gi, strValue );
}

// 验证是否 字母
function isAlpha( strValue )
{
	// 只能是 A-Z a-z 之间的字母数字 或者为空
	return checkExp(/[A-Za-z_]/i, strValue );
}


// 验证是否 日期
function isDate( strValue )
{
	// 日期格式必须是 2001-10-1/2001-1-10 或者为空
	if( isEmpty( strValue ) ) return true;

	if( !checkExp( /^\d{4}-[01]?\d-[0-3]?\d$/g, strValue ) ) return false;
	// 或者 /^\d{4}-[1-12]-[1-31]\d$/

	var arr = strValue.split( "-" );
	var year = arr[0];
	var month = arr[1];
	var day = arr[2];

	// 1 <= 月份 <= 12，1 <= 日期 <= 31
	if( !( ( 1<= month ) && ( 12 >= month ) && ( 31 >= day ) && ( 1 <= day ) ) )
		return false;

	// 润年检查
	if( !( ( year % 4 ) == 0 ) && ( month == 2) && ( day == 29 ) )
		return false;

	// 7月以前的双月每月不超过30天
	if( ( month <= 7 ) && ( ( month % 2 ) == 0 ) && ( day >= 31 ) )
		return false;

	// 8月以后的单月每月不超过30天
	if( ( month >= 8) && ( ( month % 2 ) == 1) && ( day >= 31 ) )
		return false;

	// 2月最多29天
	if( ( month == 2) && ( day >=30 ) )
		return false;

	return true;
}

// 验证是否 时间 (2003-7-29)
function isTimeOnly( strValue )
{
	// 日期格式必须是 2001-10-1/2001-1-10 或者为空
	if( isEmpty( strValue ) ) return true;

	if( !checkExp( /^[0-2]?\d:[0-5]?\d$/g, strValue ) ) return false;
	//或者if( !checkExp( /^(0[0-9]|1[0-9]|2[0-3]):((0[0-9])|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9])$/g, strValue ) ) return false;
	// 或者 /^\d{4}-[1-12]-[1-31]\d$/

	var arr=strValue.split(":");
	var hour=arr[0];
	var minute=arr[1];

	if(!((0<=hour)&&( 23 >= hour )&&( 59 >= minute ) && (0 <= minute )))
		return false;
		
	return true;
}

// 验证是否日期加 时间且不能为空 add by 张碧娟 (2003-7-29)
function isTime( strValue )
{
	// 日期格式必须是 2001-10-1/2001-1-10 或者为空
	
	if( isEmpty( strValue ) ) return true;

	if( !checkExp( /^\d{4}-[01]?\d-[0-3]?\d+\s+[0-2]?\d:[0-6]?\d:[0-6]?\d$/g, strValue ) ) return false;
	// 或者 /^\d{4}-[1-12]-[1-31]\d$/

	var arr=strValue.split(" ");
	var arr1 = arr[0].split( "-" );
	var year = arr1[0];
	var month = arr1[1];
	var day = arr1[2];
	var arr2=arr[1].split(":");
	var hour=arr2[0];
	var minute=arr2[1];
	var second=arr2[2];

	// 1 <= 月份 <= 12，1 <= 日期 <= 31
	if( !( ( 1<= month ) && ( 12 >= month ) && ( 31 >= day ) && ( 1 <= day ) ) )
		return false;

	// 润年检查
	if( !( ( year % 4 ) == 0 ) && ( month == 2) && ( day == 29 ) )
		return false;

	// 7月以前的双月每月不超过30天
	if( ( month <= 7 ) && ( ( month % 2 ) == 0 ) && ( day >= 31 ) )
		return false;

	// 8月以后的单月每月不超过30天
	if( ( month >= 8) && ( ( month % 2 ) == 1) && ( day >= 31 ) )
		return false;

	// 2月最多29天
	if( ( month == 2) && ( day >=30 ) )
		return false;

	if(!((0<=hour)&&( 23 >= hour )&& ( 59 >= minute ) && ( 0 <= minute )&& ( 59 >= second ) && ( 0 <= second )))
		return false;

	return true;
}

// 验证是否日期加 时间且可以为空 add by 张碧娟 (2003-08-12)
function isTime1( strValue )
{
	// 日期格式必须是 2001-10-1/2001-1-10 或者为空
	if( isEmpty( strValue ) ) return true;

	if( !checkExp( /^\d{4}-[01]?\d-[0-3]?\d+\s+[0-2]?\d:[0-6]?\d:[0-6]?\d$/g, strValue ) ) return false;
	// 或者 /^\d{4}-[1-12]-[1-31]\d$/

	var arr=strValue.split(" ");
	var arr1 = arr[0].split( "-" );
	var year = arr1[0];
	var month = arr1[1];
	var day = arr1[2];
	var arr2=arr[1].split(":");
	var hour=arr2[0];
	var minute=arr2[1];
	var second=arr2[2];

	// 1 <= 月份 <= 12，1 <= 日期 <= 31
	if( !( ( 1<= month ) && ( 12 >= month ) && ( 31 >= day ) && ( 1 <= day ) ) )
		return false;

	// 润年检查
	if( !( ( year % 4 ) == 0 ) && ( month == 2) && ( day == 29 ) )
		return false;

	// 7月以前的双月每月不超过30天
	if( ( month <= 7 ) && ( ( month % 2 ) == 0 ) && ( day >= 31 ) )
		return false;

	// 8月以后的单月每月不超过30天
	if( ( month >= 8) && ( ( month % 2 ) == 1) && ( day >= 31 ) )
		return false;

	// 2月最多29天
	if( ( month == 2) && ( day >=30 ) )
		return false;

	if(!((0<=hour)&&( 23 >= hour )&& ( 59 >= minute ) && ( 0 <= minute )&& ( 59 >= second ) && ( 0 <= second )))
		return false;


	return true;
}

function compareDate(startDate, endDate) {
    var arr = startDate.split("-");
    var starttime = new Date(arr[0], arr[1], arr[2]);
    var starttimes = starttime.getTime();

    var arrs = endDate.split("-");
    var lktime = new Date(arrs[0], arrs[1], arrs[2]);
    var lktimes = lktime.getTime();

    if (starttimes > lktimes) {
        alert('开始时间大于结束时间，请检查!');
        return false;
    }
    else
        return true;

}

// 验证是否 Email
function isEmail( strValue )
{
	// Email 必须是 x@a.b.c.d 等格式 或者为空
	if( isEmpty( strValue ) ) return true;

	//return checkExp( /^\w+@(\w+\.)+\w+$/gi, strValue );	//2001.12.24测试出错 检查 jxj-xxx@yysoft.com时不能通过
	//Modify By Tianjincat 2001.12.24
	var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
	return checkExp( pattern, strValue );

}

// 验证是否 为空
function isEmpty( strValue )
{
	if( strValue == "" )
		return true;
	else
		return false;
}

// 验证是否 数字
function isNumeric( strValue )
{
	// 数字必须是 0123456789 或者为空

	//return checkExp( /^\d*$/g, strValue );
	return !isNaN(strValue);

	//return  checkExp(/\ \^(-|\+)?\d+(\.\d+)?$/g, strValue );
}

// 验证是否 整数
function isInt( strValue )
{
	// 数字必须是 0123456789 或者为空

	return checkExp( /^\d*$/g, strValue );

	//return  checkExp(/\ \^(-|\+)?\d+(\.\d+)?$/g, strValue );
}


// 验证是否 货币
function isMoney( strValue )
{
	// 货币必须是 -12,345,678.9 等格式 或者为空
	if( isEmpty( strValue ) ) return true;

	return checkExp( /^[+-]?\d+(,\d{3})*(\.\d+)?$/g, strValue );
}

function isMoney1( strValue )
{
	// 货币必须是 1111.43等格式 或者为空
	if( isEmpty( strValue ) ) return true;

	return checkExp( /^[+-]?\d{1,6}\.\d{2}$/g, strValue );
}

//翁乃彬新增(decimal)
function isDecimal( strValue ){

	if( isEmpty( strValue ) ) return true;
	if (isNumeric(strValue)==false)
		return false;
	var iPos =0,strInt, strScale;
	iPos = strValue.indexOf(".");
	if (iPos < 0){
		strInt = strValue;
		strScale ="";
	} else {
		strInt = strValue.substring(0,iPos);
		strScale = strValue.substring(iPos+1, strValue.length)
	}
	if (strInt.length>14){
		return false;
	}
	if (strScale.length>4){
		return false;
	}
	return true;


}
// 验证是否 电话
function isPhone( strValue )
{
	// 普通电话	(0755)4477377-3301/(86755)6645798-665
	// Call 机	95952-351
	// 手机		130/131/135/136/137/138/13912345678
	// 或者为空
	if( isEmpty( strValue ) ) return true;

	return checkExp( /(^1\d{10}$)/g, strValue );
}

// 验证是否 邮政编码
function isPostalCode( strValue )
{
	// 邮政编码必须是6位数字
	return checkExp( /(^$)|(^\d{6}$)/gi, strValue )
}


// 验证是否 URL
function isURL( strValue )
{
	// http://www.yysoft.com/ssj/default.asp?Type=1&ArticleID=789
	if( isEmpty( strValue ) ) return true;

	var pattern = /^(http|https|ftp):\/\/(\w+\.)+[a-z]{2,3}(\/\w+)*(\/\w+\.\w+)*(\?\w+=\w*(&\w+=\w*)*)*/gi;
	// var pattern = /^(http|https|ftp):(\/\/|\\\\)(\w+\.)+(net|com|cn|org|cc|tv|[0-9]{1,3})((\/|\\)[~]?(\w+(\.|\,)?\w\/)*([?]\w+[=])*\w+(\&\w+[=]\w+)*)*$/gi;
	// var pattern = ((http|https|ftp):(\/\/|\\\\)((\w)+[.]){1,}(net|com|cn|org|cc|tv|[0-9]{1,3})(((\/[\~]*|\\[\~]*)(\w)+)|[.](\w)+)*(((([?](\w)+){1}[=]*))*((\w)+){1}([\&](\w)+[\=](\w)+)*)*)/gi;

	return checkExp( pattern, strValue );

}


//-- Added by linhs 2004-3-12 [begin] --
/*
*不含'<' 、'>' 、'&'、 '"'、 '\''。
*/
function exceptLawlessChar(strValue) {
	if (strValue.indexOf('<') >= 0 || strValue.indexOf('>') >= 0
		|| strValue.indexOf('&') >= 0 || strValue.indexOf('"') >= 0
		|| strValue.indexOf('\'') >= 0){
		return false;
	} else {
		return true;
	}
}
//-- Added by linhs 2004-3-12 [ end ] --


// 检查字段长度
//
//	strValue	字符串
//	strParam	检查参数，形如：L<10, L=5, L>117
//
function checkLength( strValue, strParam )
{
	if( isEmpty( strValue ) )	return true;

	// 参数形如：L<10, L=5, L>117
	if( strParam.charAt( 0 ) != 'L' )	return false;

	var l = strValue.length;
	var ml = parseInt( strParam.substr( 2 ) );

	switch( strParam.charAt( 1 ) )
	{
		case '<' :
			if( l >= ml )
				return false;
			break;

		case '=' :
			if( l != ml )
				return false;
			break;

		case '>' :
			if( l <= ml )
				return false;
			break;

		default :
			return false
	}

	return true;
}


// 检查输入数据长度的合法性（字符长度不能大于**个字符）
//
//	输入参数
//		strName		字段对象
//		strDescription	字段描述
//		strLength	字段长度
//
function ValidateMaxLength( strName, strDescription, strLength) {
	var strMsg = "";
	var strValue = document.all( strName ).value.trim();
	var strMaxLength = "L<" + strLength;
	if( !checkLength( strValue, strMaxLength ))
	strMsg = '"' + strDescription + '" 必须小于'+ strLength + '个字符\n';
	return strMsg;
}

// 检查输入数据长度的合法性（字符长度不能小于**个字符）
//
//	输入参数
//		strName		字段对象
//		strDescription	字段描述
//		strLength	字段长度
//
function ValidateMinLength( strName, strDescription, strLength) {
	var strMsg = "";
	var strValue = document.all( strName ).value.trim();
	var strMaxLength = "L>" + strLength;
	if( !checkLength( strValue, strMaxLength ))
	strMsg = '"' + strDescription + '" 必须大于'+ strLength + '个字符\n';
	return strMsg;
}

// 检查输入数据长度的合法性（字符长度等于**个字符）
//
//	输入参数
//		strName		字段对象
//		strDescription	字段描述
//		strLength	字段长度
//
function ValidateEquLength( strName, strDescription, strLength) {
	var strMsg = "";
	var strValue = document.all( strName ).value.trim();
	var strMaxLength = "L=" + strLength;
	if( !checkLength( strValue, strMaxLength ))
	strMsg = '"' + strDescription + '" 必须等于'+ strLength + '个字符\n';
	return strMsg;
}

//
//	输入参数
//		obj		字段对象
//		strType	字段类型
//
function ValidateEl( obj, strType)
{
	var strMsg ="";
	var result=true;
	var strValue = obj.value.trim();

	switch( strType.toLowerCase() )
	{
		case "alphanumeric" :	// 字母数字
			result=isAlphaNumeric( strValue ) ;
			break;

		case "date" :	// 日期
			result=isDate( strValue );
			if (result) {obj.value = formatDate(strValue)}
			break;

		case "time":    //日期加时间
			result=isTime(strValue);
			if (result) {obj.value = formatTime1(strValue)}
			break;

		case "time1":    //日期加时间
			result = isTime1(strValue);
			if (result) {obj.value = formatTime1(strValue)}
			break;

		case "timeonly":  //时间
			result=	isTimeOnly(strValue);
			break;

		case "email" :	// 电子邮件
			result=isEmail(strValue);
			break;

		case "notempty" :	// 不许空值
		
			result=!isEmpty( strValue );
			break;
		case "numeric" :	//数字
			result=isNumeric( strValue );

			break;
		case "int" :	//整型
			result=isInt( strValue );

			break;
		case "money" :	//货币
			result=isMoney( strValue );
			break;
			
		case "money1" :	//货币
			result=isMoney1( strValue );
			break;
		case "decimal" :	//货币
			result=isDecimal( strValue );
			break;	
		case "phone" :	// 电话
			result=isPhone( strValue );
			break;

		case "postalcode" :	// 邮政编码
			result=isPostalCode( strValue );
			break;

		case "url" :	// URL
			result=isURL( strValue );
			break;

		case "exceptlawlesschar" :
			result=exceptLawlessChar(strValue);
			break;
	}

	return result;
}

// ----- Added by linhs 2003-10-30 [begin] -----
// 如果按键是功能键则返回 true
function isTheFunctionKeys(code) {
  if (code==46 || code==37 || code==39 || code==8 || code==9) {
    return true;
  } else {
    return false;
  }
}
// ----- Added by linhs 2003-10-30 [ end ] -----

/**********************
*控制文本框只能输入某类型值
***********************/
function setBoxInputType(){
	
	var flag=this.onlytype;

	var txt="";
	var code=event.keyCode;
	txt=this.value;

	if(flag.toLowerCase()=="url"){
	  if (!isURL(strChart))
		  return false;
    }
	if (flag.toLowerCase()=="strj")
	{   if(code==191)
			return false;
		else
			return true;
	}
	if(event.shiftKey&&flag.toLowerCase()!="email") {
	  return false;
	}

	if(flag.toLowerCase()=="int"){
	  if(code==190) {
	    return false;
	  }
	  // modified by linhs 2003-10-31
	  // if((code<96 || code>105) && (code<48 || code>57) && code!=190 &&  code!=46 && code!=37 &&  code!=39 &&  code!=8 && code!=9){
	  if ((code<96 || code>105) && (code<48 || code>57) && !isTheFunctionKeys(code)) {
		return false;
	  }
	}
	
	if(flag.toLowerCase()=="float"){
		//alert(" code=" + code);
		if(txt=="" && code==190) 
		  return false;
		if ((code<96 || code>105) && (code<48 || code>57) && code!=190 && code!=110 && !isTheFunctionKeys(code)) {
			//alert(txt + " code1=" + code);
			return false;
		}
		if(txt.indexOf(".")!=-1 && (code==190 || code==110)) {
		//alert(txt + " code2=" + code);
		  return false;
		}
	}
	if(flag.toLowerCase()=="mobiles"){
		//alert(code);
		var len =txt.length;
		
		if (code ==188)
		{
			if (txt=="")
			{
				return false;
			} else if (txt.substring(len-1,len)==","){
				return false;
			} else {
				return true;
			}
		}
		
		if ((code<96 || code>105) && (code<48 || code>57) && !isTheFunctionKeys(code)) {
			return false;
		}
		
	}
	// ----- Added by linhs 2003-10-30 [begin] -----
	if(flag.toLowerCase()=="alpha"){
		var strChart=String.fromCharCode(code);
		if ((!isAlpha(strChart) || code==190 || (code>=96 && code<=111)) && !isTheFunctionKeys(code))
		  return false;
	}
	if(flag.toLowerCase()=="money1" || flag.toLowerCase()=="decimal"){
		if(txt=="" && code==190) 
		  return false;		
		if(txt.indexOf(".")!=-1 && (code==190 || code==110)) 
		  return false;
		if((code<96 || code>105) && (code<48 || code>57) && code!=190 && code!=110 &&  code!=46 && code!=37 &&  code!=39 &&  code!=8 && code!=9){
			return false;	
		}
	}
	// ----- Added by linhs 2003-10-30 [ end ] -----
	
	return true;

}

function checkPasteData(){
	var pasteData=window.clipboardData.getData('Text');
	switch(this.onlytype.toLowerCase()){

		case "int":
			if(!isInt(pasteData)){
				alert("粘贴文本不能转化整数，该文本框要求输入整数.");
				event.returnValue=false;
			}
			break;
		case "float":
			if(!isNumeric(pasteData)){
				alert("粘贴文本不能转化数字，该文本框要求输入数字.");
				event.returnValue=false;
			}
			break;
        // ----- Added by linhs 2003-11-5 [begin] -----        
		case "alpha":
			if (!isAlpha(pasteData) && (this.value.length!=0)) {
                alert("粘贴文本不能转化英文字符串，该文本框要求输入英文字符串.");
				event.returnValue=false;
			}
		// ----- Added by linhs 2003-11-5 [begin] -----
	}

}

function checkInputValue(){
	var value=this.value;
	switch(this.onlytype.toLowerCase()){

		case "int":
			if(!isInt(this.value)){
				alert("该文本框要求输入整数.");

				this.value=(this.oldValue==null?"":this.oldValue);
				return false;
			}
			break;
		case "float":
			if(!isNumeric(this.value)){
				alert("该文本框要求输入数字.");
				this.value=(this.oldValue==null?"":this.oldValue);
				return false;
			}
			break;
        // ----- Added by linhs 2003-11-5 [begin] -----        
		case "alpha":
			if(!isAlpha(this.value) && (this.value.length!=0)){
				alert("该文本框要求输入英文字符串.");
				this.value=(this.oldValue==null?"":this.oldValue);
				return false;
			}
			break;
        // ----- Added by linhs 2003-11-5 [begin] -----        
	}

}

//文本框获得焦点时运行该方法（onfocus="checkTextBoxInput()"）
function checkTextBoxInput(){
	var txtBox=event.srcElement;
	if(txtBox.onlytype){
		if(txtBox.onlytype.toLowerCase()=="date" || txtBox.onlytype.toLowerCase()=="time"
		|| txtBox.onlytype.toLowerCase()=="time1")
		{
			//txtBox.readOnly=true;
			txtBox.ondblclick=setTextBoxDate;
			return true;
		}
		if(txtBox.oldValue==null){
			txtBox.oldValue=txtBox.value;
		}

		txtBox.onkeydown=setBoxInputType;
		try{txtBox.onpaste=checkPasteData;}catch(e){}
		txtBox.onblur=checkInputValue;


	}else{
		return false;
	}

}

//////////////////////////////////////////////


/**********************
*设置文本框为日期输入
***********************/
function selectDateNoTime() {

	var str=window.showModalDialog(context+"/javascript/getdateNoTime.html",null,"font-size:10px;dialogWidth:180px;dialogHeight:180px;scrollbars=no;status=no");
	if (str == null || str == "" || str == "undefined")
		return "";
	else
		return str;
}
function selectDate() {
	var str=window.showModalDialog(context+"/javascript/getdate.html",null,	"font-size:10px;dialogWidth:180px;dialogHeight:180px;scrollbars=no;status=no");
	if (str == null || str == "" || str == "undefined")
		return "";
	else
		return str;
 }


function setDate(blFlag)
{
	var bl=true;
	var ret;
	if(blFlag==null){
		bl=true;
	}else{
		bl=blFlag;
	}
	if(event.srcElement.tagName=="INPUT"){
		if(bl){
			ret=selectDateNoTime();
			if(ret!=null && ret!=""){
				event.srcElement.value=ret;
			}
		}else{
			ret=selectDate();
			if(ret!=null && ret!=""){
				event.srcElement.value=ret;
			}
		}
	}
}


// 设置日期的某部分(年,月,日),blFlag的值为0:年,1:月,2:日 ,  add by lbl
function setDatePart(blFlag){
  var strDate=selectDateNoTime();
  if(strDate!=''){
    var arr = strDate.split( "-" );
    var strYear = arr[0];
    var strMonth = arr[1];
    var strDay = arr[2];
    if(blFlag==0){
      event.srcElement.value=strYear;
    }
    if(blFlag==1){
      event.srcElement.value=strMonth;
    }
    if(blFlag==2){
      event.srcElement.value=strDay;
    }
  }
}


function setTextBoxDate()
{
	var bl=true;
	var ret;
	if(this.onlytype.toLowerCase()=="date"){
		bl=true;
	}else if(this.onlytype.toLowerCase()=="time" || this.onlytype.toLowerCase()=="time1"){
		bl=false;
	}
	if(bl){
		ret=selectDateNoTime();
		if(ret!=null && ret!=""){
			this.value=ret;
		}
	}else{
		ret=selectDate();
		if(ret!=null && ret!=""){
			this.value=ret;
		}
	}
}

//////////////////////////////
/*
设置文本域的最大长度
文本域获得焦点时运行该方法（onfocus="setMaxLen()"）
*/


function setMaxLen(len){
	var txtObj=event.srcElement;
	txtObj.lenValue=null;

	if(txtObj.tagName.toLowerCase()!="textarea") return;

	if(txtObj.maxlength==null && len==null) return;
	if(len!=null){
		txtObj.lenValue=len;
	}
	if(txtObj.maxlength!=null){			//优先处理
		txtObj.lenValue=txtObj.maxlength;

	}
	if(txtObj.lenValue!=null){
		if(!isInt(txtObj.lenValue)){
			alert("maxlength必须设置为整数");
			return;
		}else{
			
			txtObj.onkeydown=checkInputTextMaxLen;
			try{
			txtObj.onpaste=checkPasteTextMaxLen;
			
			}catch(e){}
			txtObj.onblur=checkTextAreaValueLen;
			
		
			return;
			
		}

	}else{
		return;
	}
}
/** 方法和上面的setMaxLen(len)同名，造成标签（TalentTextAreaTag.java）长度检测错误  create by  2006/03/09
function setMaxLen(msg,len){
	var Ele = event.srcElement;
	if(Ele.value.length*1.0>len*1.0) {
		alert(msg+"超过长度!");
		Ele.value=Ele.value.substring(0,len);
	}
}
**/
function checkInputTextMaxLen(){
	var code=event.keyCode;
	
	if(code==190 ||  code==46 || code==37 ||  code==39 ||  code==8 ||  code==40 || code==17 ) 
		return true;
	if(this.value.length>=this.lenValue) return false;
	
}
function checkPasteTextMaxLen(){
	var pasteData=window.clipboardData.getData('Text');

	if(pasteData.length>this.lenValue){
		alert("粘贴的文字数量超过文本域所设定的最大长度");
		return false;
	}
	
}
function checkTextAreaValueLen(){
	if(this.value.length>this.lenValue){
		alert("文本域内容长度超出所设定的最大长度");
		this.value=this.value.substring(0,this.lenValue);
		return false;
	}
	
}
//设定文本域最大长度结束



// 格式化 可以为空的时间 add by linhs (2004-03-12)
function formatTime1(strValue)
{
	// 日期格式必须是 2001-10-1/2001-1-10 或者为空
	if( isEmpty( strValue ) ) return "";

	var arr=strValue.split(" ");
	var arr1 = arr[0].split( "-" );
	var year = arr1[0];
	var month = arr1[1];
	var day = arr1[2];
	var arr2=arr[1].split(":");
	var hour=arr2[0];
	var minute=arr2[1];
	var second=arr2[2];

	// 1 <= 月份 <= 12，1 <= 日期 <= 31
	if( month<10 && month.length == 1)
		month = '0' + month;

	if( day<10 && day.length == 1)
		day = '0' + day;

	// 1 <= 月份 <= 12，1 <= 日期 <= 31
	if( hour<10 && hour.length == 1)
		hour = '0' + hour;

	// 1 <= 月份 <= 12，1 <= 日期 <= 31
	if( minute<10 && minute.length == 1)
		minute = '0' + minute;

	// 1 <= 月份 <= 12，1 <= 日期 <= 31
	if( second<10 && second.length == 1)
		second = '0' + second;

    //alert(year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second);
	return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
}

// 格式化 可以为空的日期 add by linhs (2004-03-31)
function formatDate(strValue)
{
	// 日期格式必须是 2001-10-1/2001-1-10 或者为空
	if( isEmpty( strValue ) ) return "";

	var arr=strValue.split(" ");
	var arr1 = arr[0].split( "-" );
	var year = arr1[0];
	var month = arr1[1];
	var day = arr1[2];

	// 1 <= 月份 <= 12，1 <= 日期 <= 31
	if( month<10 && month.length == 1)
		month = '0' + month;

	if( day<10 && day.length == 1)
		day = '0' + day;

	return year + '-' + month + '-' + day;
}
//新添加选日期和时间
function showCalendar(p) {
	var binding;
	if(p!=null) {
		var arr=document.getElementsByName(p.bindingFld);
		if (arr.length>1) {
			var index=p.parentNode.parentNode.parentNode.sectionRowIndex;
			binding=arr[index];
		} else {
			binding=arr[0];
		}
	}else{
		binding=event.srcElement;
	}
	var v=binding.value && !binding.title ? binding.value.replace(/\-/g,"/"):"";
	var mask=binding.dtype;
	var height=(/[hms]+/).exec(mask) ? 248:228;
	var r=window.showModalDialog(context+"/javascript/Calendar.htm",[v,mask,binding.toMax=="true"],"help:0;resizable:0;status:0;center:1;dialogHeight:"+height+"px;dialogWidth:186px");
	if (r) {
		binding.value = formatDate(r,mask);
	}else{
		binding.value = "";
	}
	binding.focus();
}

function formatDate(date,type) {

	if(type.indexOf("yyyy-MM-dd") >= 0) {
		var patter = new RegExp("/","gi");
		return date.replace(patter,"-");
	}else if(type.indexOf("yyyy/MM/dd") >= 0) {
		var patter = new RegExp("-","gi");
		return date.replace(patter,"/");
	}
	return date;
}
//校验form中的不能为空的值function lTrim(str)
{
	if(str.charAt(0) == " "){
		str = str.slice(1);
		str =lTrim(str);
	}
	return str;
}
function rTrim(str)
{
	var iLength;
	iLength = str.length;
	if (str.charAt(iLength - 1) == " ")
	{
	str = str.slice(0, iLength - 1);
	str = rTrim(str);
	}
	return str;
}
function trim(str)
{
	return lTrim(rTrim(str));
}
function trimForm(){
	var formobj = document.forms[0];
	for(var k = 0;k<formobj.elements.length;k++){ 
		var elem = formobj.elements[k];
			elem.value=trim(elem.value);
	}
}


function validatorForm(){
	trimForm();
	var alertString = "" ;
	var returnBoolean = true;
	var formobj = document.forms[0];
	for(var k=0; k<formobj.elements.length; k++){ 
		var elem = formobj.elements[k];
		if(elem.notempty!=null&&elem.notempty!=""){
			if(trim(elem.value) == null || trim(elem.value) == ""){
				alertString += elem.msg + ":不能为空！\r\n";
			}
		} 
	}
	
	if(alertString != ""){
		alert(alertString);
		returnBoolean = false;
	}else{
		returnBoolean = validatorFormContent(formobj) ;
	}
	
	return returnBoolean ;
}


//验证所有表单数据不允许全部为空
function validatorIsNull(){
	trimForm();
	var s = 0;
	var f = true;
	var formobj = document.forms[0];
	for(var k = 0;k<formobj.elements.length;k++){ 
		var elem = formobj.elements[k];
			if(elem.type =="text" && elem.value !=""){
				s ++;
			}else if(elem.type =="select-one" && elem.value !=""){
				s ++;
			}
		}
	if(s < 1){
		alert("查询条件不允许全部为空!");
		f = false;
	}
	return f;
}


//失去焦点校验
function validatorFocus(obj, strType)
{
	var result=true;
	var strValue = obj.value.trim();

	switch( strType.toLowerCase() )
	{
		case "alphanumeric" :	// 字母数字
			result=isAlphaNumeric( strValue ) ;
			break;

		case "date" :	// 日期
			result=isDate( strValue );
			if (result) {obj.value = formatDate(strValue)}
			break;

		case "time":    //日期加时间
			result=isTime(strValue);
			if (result) {obj.value = formatTime1(strValue)}
			break;

		case "time1":    //日期加时间
			result = isTime1(strValue);
			if (result) {obj.value = formatTime1(strValue)}
			break;

		case "timeonly":  //时间
			result=	isTimeOnly(strValue);
			break;

		case "email" :	// 电子邮件
			result=isEmail(strValue);
			break;

		case "notempty" :	// 不许空值
			result=!isEmpty( strValue );
			break;
		case "numeric" :	//数字
			result=isNumeric( strValue );
			break;
		case "float" :	//数字
			result=isNumeric( strValue );
			break;
		case "int" :	//整型
			result=isInt( strValue );

			break;
		case "money" :	//货币
			result=isMoney( strValue );
			break;
			
		case "money1" :	//货币
			result=isMoney1( strValue );
			break;
		case "decimal" :	//货币
			result=isDecimal( strValue );
			break;	
		case "phone" :	// 电话
			result=isPhone( strValue );
			break;

		case "postalcode" :	// 邮政编码
			result=isPostalCode( strValue );
			break;

		case "url" :	// URL
			result=isURL( strValue );
			break;

		case "exceptlawlesschar" :
			result=exceptLawlessChar(strValue);
			break;
	}
	
	if(result==false){
		alert(obj.msg+":录入数据类型不对，所需数据类型为("+strType+")!");
		obj.value="";
		obj.focus();
	}

	return result;
}

function checkLen(obj){
	
	if(obj.maxlength==null && len==null) return;
	
	if(obj.value.length>obl.maxlength){
		alert(obj.msg+":录入数据类型大于设定的最大长度("+obj.maxlength+")!");
		obj.focus();
	}
	
}

//验证金额
function formatAmts(src, pos){
	var number=new Number(src);
	if (isNaN(number)){
		alert("无效的数字");
		return number=new Number(0.0).toFixed('1');
	}
	var k=((number/1)+'').indexOf('.');
	if(k==-1){
		pos=1;
	}
	return new Number(src).toFixed(pos);
}


/*
×	描述：对前台提交表单的内容进行过滤
*	qinfx add by security requirement on 2012-07-17
*/

function validatorFormContent(formobj){
	var returnBoolean = true ;
	var alertString = "" ;
	var specialString = " $ | ’ | % | ^ | & | ? | < | > | [ | ] | { | } | \\ | ; | -- | select | update | drop | delete | insert | count | declare | mid | master | truncate | script | or " ;
	var specialStrArray = specialString.split("|") ;
	
	for(var m=0; m<formobj.elements.length; m++){ 
		var elem = formobj.elements[m];
		if((elem.type == "text" || elem.type == "textarea") && trim(elem.value) != null && trim(elem.value) != ""){
			for(var n=0; n<specialStrArray.length; n++){
				if(trim(elem.value).toLowerCase().indexOf(specialStrArray[n]) >= 0){
					if(alertString == ""){
						alertString = "以下表单数据含有可能不安全的特殊符号或保留字：\r\n\n" ;
					}
					alertString += elem.value + "\r\n" ;
					break ;
				}
			}
		}
	}
	
	if(alertString != ""){
		alertString += "\r\n\r\n表单数据不能包含以下可能不安全的特殊符号或保留字：\r\n\r\n  $, ’, %, ^, &, ?, <, >, [, ], {, }, \\, ;, --, select, update, drop, delete, insert, count, declare, mid, master, truncate, script, or" ;
		alert("数据包含非法字符!") ;
		returnBoolean = false ;
	}
	return returnBoolean ;
}

//去左空格;
function ltrim(s){
   return s.replace( /^\s*/, "");
}
//去右空格;
function rtrim(s){
   return s.replace( /\s*$/, "");
}
function trim(s){
   return rtrim(ltrim(s));
}
//去两边空格

function toTrimStr(s){	  
  return s.replace(/(^\s*)|(\s*$)/g,"");
}
