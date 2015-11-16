<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="<%=path%>/js/jquery-1.11.1.js"></script>
	<style type="text/css">
	.menu li{
		display: inline-block;
	}
	</style>
  </head>
  <body>
  	<div>
	<ul>
	<li><label>服务器</label>
	<select name="host" id="host">
	<option value="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath() %>" selected>当前服务器地址</option>
	</select><input type='txt' id='realhost' >
	<li><label>接口名</label>
	<textarea id='api' cols="60"></textarea>

	</li>
	<li><label>参数</label><textarea id="param" cols="100" rows="15"></textarea></li>
	<li><label>cookie</label><textarea id="cookie" cols="100" rows="2"></textarea></li>
	<li><input type="button" value="提交" id="bt"></li>
	<li><label>响应</label><div id="resp"></div></li>
	</ul>
	</div>
	<table>
	<tr><td colspan="6">用户中心</td></tr>
	<tr>
	    <input type="button" onclick='setbinding("/alipay/alipay_payment_notify?discount=0.00&payment_type=1&subject= 测 试&trade_no=2013082244524842&buyer_email=dlwdgl@gmail.com&gmt_create=2013-08-22 14:45:23&notify_type=trade_status_sync&quantity=1&out_trade_no=082215222612710&seller_id=2088501624816263&notify_time=2013-08-22 14:45:24&body=测试测 试&trade_status=TRADE_SUCCESS&is_total_fee_adjust=N&total_fee=1.00&gmt_payment=2013-08-22 14:45:24&seller_email=xxx@alipay.com&price=1.00&buyer_id=2088602315385429&notify_id=64ce1b6ab92d00ede0ee56ade98fdf2f4c&use_coupon=N&sign_type=RSA&sign=1glihU9DPWee+UJ82u3+mw3Bdnr9u01at0M/xJnPsGuHh+JA5bk3zbWaoWhU6GmLab3dIM4JNdktTcEUI9/FBGhgfLO39BKX/eBCFQ3bXAmIZn4l26fiwoO613BptT44GTEtnPiQ6+tnLsGlVSrFZaLB9FVhrGfipH2SWJcnwYs=","",null,"formdata")' value="支付宝支付通知" ></li>
		<td><input type="button"  value="微信用户注册"  onclick='setbinding("/userCenter/info/reg","{\"app\":{\"appId\":\"ADP\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\":{\"openId\":\"oITR0uAkXSsTgY2YaU2ItDN2kh7g\",\"nickname\":\"大师你懂吗\",\"sex\":\"1\",\"language\":\"zh_CN\",\"city\":\"深圳\", \"province\":\"广东\",\"country\":\"中国\",\"headimgurl\":\"http://wx.qlogo.cn/mmopen/PiajxSqBRaEInIaAHHYN9ia0CF5rUljZlhDMHniaoft7MXwXDtLTHuACWyWTePqpvVc2qL6aGZchEUBg5X0RZsxyQ/0\",\"subscribe_time\":\"1440076007\",\"unionId\":\"olbkKt-8vkqpPod-N7i1SzSxddIo\",\"remark\":\"\",\"subscribe\":\"1\",\"groupid\":\"0\",\"privilege\":\"\"}}")'></td>
		<td><input type="button"  value="微信用户绑定"  onclick='setbinding("/userCenter/user/bind","{\"app\":{\"appId\":\"ADP\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\":{\"unionId\":\"olbkKt-8vkqpPod-N7i1SzSxddIo\",\"mobileNum\":\"18600003333\"}}")'></td>
		<td><input type="button"  value="查询用户信息"  onclick='setbinding("/userCenter/user/query","{\"app\":{\"appId\":\"ADP\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\":{\"unionId\":\"olbkKt-8vkqpPod-N7i1SzSxddIo\",\"openId\":\"oITR0uAkXSsTgY2YaU2ItDN2kh7g\"}}")'></td>
		
	</tr>
	<tr><td colspan="6">宝宝课程和扫码上课 </td></tr>
	<tr>
		<td><input type="button"  value="宝宝课程查询"  onclick='setbinding("/babyCourse/course/queryBabyCourseList","{\"app\":{\"appId\":\"ADP\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\":{\"unionId\":\"olbkKt-8vkqpPod-N7i1SzSxddIo\"}}")'></td>
		<td><input type="button"  value="宝宝耗课历史"  onclick='setbinding("/babyCourse/queryBabyCourseHistoryList","{\"app\":{\"appId\":\"ADP\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\":{\"schoolName\":\"白云校区\",\"babyName\":\"蚯蚓\",\"orderId\":\"ZJ97898653\",\"pageIndex\":1,\"pageSize\":10}}")'></td>
		<td><input type="button"  value="扫码上课确认"  onclick='setbinding("/courseManager/course/query","{\"app\":{\"appId\":\"ADP\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\":{\"unionId\":\"olbkKt-8vkqpPod-N7i1SzSxddIo\",\"courseNum\":2,\"courseName\":\"3215\"}}")'></td>
		<td><input type="button"  value="扫码上课"  onclick='setbinding("/courseManager/course/spend","{\"app\":{\"appId\":\"ADP\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\":{\"childId\":543,\"courseName\":\"音律\",\"courseNum\":2,\"orderId\":\"ZJ97898653\",\"type\":\"SPD\",\"remark\":\"正常上课\"}}")'></td>
		
	</tr>
	<tr><td colspan="6">试听预约 </td></tr>
	<tr>
		<td><input type="button"  value="线上预约"  onclick='setbinding("/customerDev/candidate/add","{\"app\":{\"appId\":\"ADP\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\":{\"unionId\":\"olbkKt-8vkqpPod-N723232xddIo\",\"mobileNum\":\"13657256331\",\"userName\":\"蝌蚪妈\",\"babyName\":\"蝌蚪\",\"babyBirthday\":\"2012-9-12\",\"babySex\":\"MALE\",\"city\":\"深圳市\",\"district\":\"南山区\"}}")'></td>
		<td><input type="button"  value="查看是否预约接口"  onclick='setbinding("/customerDev/judgeAppointment","{\"app\":{\"appId\":\"ADP\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\":{\"unionId\":\"olbkKt-8vkqpPod-N7i1SzSxddIo\"}}")'></td>
		<td><input type="button"  value="记录预约上课信息"  onclick='setbinding("/courseManager/course/record","{\"app\":{\"appId\":\"ADP\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\":{\"unionId\":\"olbkKt-8vkqpPod-N7i1SzSxddIo\",\"courseName\":\"3215\"}}")'></td>
		<td><input type="button"  value="预约分享接口"  onclick='setbinding("/customerDev/share","{\"app\":{\"appId\":\"ADP\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\":{\"unionId\":\"olbkKt-8vkqpPod-N7i1SzSxddIo\",\"recordId\":43647}}")'></td>
		
	</tr>
	<tr><td colspan="6">广告</td>
	</tr>
	<tr>
		<td><input type="button"   onclick='setbinding("/advertisement/getAdvertisementList","{\"app\":{\"appId\":\"babyspace\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\":  {\"unionId\":\"13912345678\",\"pageIndex\":0,\"pageSize\":10}}")' value="查询广告列表" ></td>
		<td><input type="button"   onclick='setbinding("/advertisement/getAdvertisementDetail","{\"app\":{\"appId\":\"babyspace\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\":  {\"advertisementId\":1}}")' value="获取广告详情" ></td>
		<td><input type="button"   onclick='setbinding("/babyWonderful/babyWonderfulList","{\"app\":{\"appId\":\"babyspace\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\":  {\"unionId\":\"13912345678\",\"pageIndex\":0,\"pageSize\":10}}")' value="查询宝宝精彩列表" ></td>
		<td><input type="button"   onclick='setbinding("/babyWonderful/queryBabyWonderfulDetail","{\"app\":{\"appId\":\"babyspace\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\": {\"unionId\":\"13912345678\",\"recordId\":1}}")' value="获取宝宝精彩详情" ></td>
		<td><input type="button"   onclick='setbinding("/babyWonderful/praise","{\"app\":{\"appId\":\"babyspace\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\":  {\"unionId\":\"13912345678\",\"recordId\":1}}")' value="宝宝精彩点赞" ></td>
	</tr>
	<tr>
		<td><input type="button"   onclick='setbinding("/babyWonderful/share","{\"app\":{\"appId\":\"babyspace\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\":  {\"unionId\":\"13912345678\",\"recordId\":1}}")' value="宝宝精彩分享" ></td>
		<td><input type="button"   onclick='setbinding("/knowledgeManager/babyInterlocution/questionList","{\"app\":{\"appId\":\"babyspace\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\":  {\"unionId\":\"13912345678\",\"pageIndex\":0,\"pageSize\":10}}")' value="查询宝宝问答列表" ></td>
		<td><input type="button"   onclick='setbinding("/knowledgeManager/babyInterlocution/submitQuestion","{\"app\":{\"appId\":\"babyspace\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\":  {\"unionId\":\"13912345678\",\"title\":\"小孩不说话\",\"desc\":\"我家小孩3岁了,只会叫爸爸妈妈,其它不会讲,请问正常吗\"}}")' value="宝宝问答提问题" ></td>
		<td><input type="button"   onclick='setbinding("/knowledgeManager/encyclopedia/queryEncyclopediaList","{\"app\":{\"appId\":\"babyspace\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\": {\"searchKeyword\":\"不爱说话\",\"pageIndex\":0,\"pageSize\":10}}")' value="查询十万个为什么列表" ></td>
		<td><input type="button"   onclick='setbinding("/knowledgeManager/encyclopedia/queryEncyclopediaDetail","{\"app\":{\"appId\":\"babyspace\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\":  {\"recordId\":1}}")' value="查看十万个为什么" ></td>
	</tr>
	<tr>
		<td><input type="button"   onclick='setbinding("/babyMature/queryBabyMatureList","{\"app\":{\"appId\":\"babyspace\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\":  {\"unionId\":\"13912345678\",\"pageIndex\":0,\"pageSize\":10}}")' value="查询成长时光列表" ></td>
		<td><input type="button"   onclick='setbinding("/babyMature/getBabyMature","{\"app\":{\"appId\":\"babyspace\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\":  {\"matrueId\":1}}")' value="查看成长时光详情" ></td>
		<td><input type="button"   onclick='setbinding("/babyMature/addComment","{\"app\":{\"appId\":\"babyspace\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\":  {\"unionId\":\"13912345678\",\"matrueId\":1,\"replyTo\":1,\"comment\":\"谢谢老师\"}}")' value="成长时光发表评论" ></td>
		<td><input type="button"   onclick='setbinding("/babyMature/addShareHistory","{\"app\":{\"appId\":\"babyspace\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  },\"body\": {\"unionId\":\"13912345678\",\"recordId\":1} }")' value="成长时光分享" ></td>
	</tr>
	<tr>
		<td><input type="button"   onclick='setbinding("/assistant/leave","{\"body\":{\"assistantId\":1},\"app\":{\"appId\":\"babyspace\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  } }")' value="查询职工离职" ></td>
	</tr>
	<tr>
		<td><input type="button"   onclick='setbinding("/assistant/assign","{\"body\":{\"assistantId\":1,\"cancidateIds\":[1,2]},\"app\":{\"appId\":\"babyspace\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  } }")' value="手动分配用户业务员跟进" ></td>
	</tr>
	<tr>
		<td><input type="button"   onclick='setbinding("/activity/join","{\"body\":{ \"unionId\":\"xfdsfdsf832432\" ,\"activityId\":1},\"app\":{\"appId\":\"babyspace\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\", \"signature\":\"SIGNATURE\"  } }")' value="加入活动" ></td>
	</tr>
	<tr>
		<td><input type="button"   onclick='setbinding("/notify/add","{\"body\":{\"users\":[{\"identity\":\"ozCgxuFPIWQ-FTnmf4GnN96sTd9E\",\"attrs\":{\"userName\":\"张三\",\"wechat_appId\":\"wx713dc5b6f622f84f\"},\"content\":\"宝宝（小明，手机号1234567）已经2天没有跟进啦，请跟进一下\"}] ,\"notifyType\":\"WECHAT\"},\"app\":{\"appId\":\"babyspace\",\"timeStamp\":\"TIMESTAMP\",  \"nonce\":\"NONCE\",\"signature\":\"SIGNATURE\"  } })' value="添加通知" ></td>
	</tr>
	<tr>
		<td><input type="button"   onclick='setbinding("/notify/query","{\"body\": {\"notifyId\":17},\"app\":{\"appId\":\"babyspace\",\"timeStamp\":\"TIMESTAMP\",\"nonce\":\"NONCE\",\"signature\":\"SIGNATURE\"} })' value="通知查询" ></td>
	</tr>
</table>
  </body>
    <script>
    var type='payload'
    
		$("#bt").click(function (){
			$("#resp").html("");
			var hostval = $("#realhost").val()
			if(hostval==''){
				hostval=$("#host").val()
			}
			var url =hostval+$("#api").val();
			var data = $("#param").val();
			if(type=='payload')
			{
				var vcookie = $('#cookie').val();
				if(vcookie!=''){
					try{
						vcookie = eval('('+vcookie+')');
						for(item in vcookie){
							$.cookie(item, vcookie[item]);
						}
					}
					catch(e){console.print(e)}
					
				}
				
				$.ajax({type:'POST',contentType:'application/json',url:url,data:data,
				success:function(resp){ $("#resp").text(resp);},dataType:"html"}
				);
			}
			else if(type=='cookie'){
				data = eval('('+data+')');
				for(item in data){
					$.cookie(item, data[item]);
				}
				$.ajax({type:'POST',contentType:'application/json',url:url,
					success:function(resp){ $("#resp").text(resp);},dataType:"html"}
					);
			}
			else if(type=='formdata'){
				data = eval('('+data+')');
				$.ajax({type:'POST',url:url,data:data,
					success:function(resp){ $("#resp").text(resp);},dataType:"html"}
				);
			}
			else if(type=='string'){
				//以string 的形式提交，参数名为param
				$.ajax({type:'POST',url:url,data:{param:data},
					success:function(resp){ $("#resp").text(resp);},dataType:"html"}
					);
			}
			else if(type=='xml'){
				$.ajax({type:'POST',contentType:'application/xml',url:url,data:data,
					success:function(resp){ 
						var str = serializeXml(resp)
						$("#resp").text(str);
					},dataType:"xml"});
			}
			
		});
		
    function setbinding4string(api,json){
    	setbinding(api,json,null,'string');
    }
    
		function setbinding(api,json,cookiejson,submittype){
			$("#api").val(api)
			$("#param").val(json.replace("\$\{systime\}",new Date().getTime()))
			$("#cookie").val(cookiejson)
			if(!submittype){
				submittype= 'payload';
			}
			type=submittype;
		}
		
		jQuery.cookie = function(name, value, options) {
			if (typeof value != 'undefined') {
			   options = options || {};
			   if (value === null) {
			    value = '';
			    options = $.extend({}, options);
			    options.expires = -1;
			   }
			   var expires = '';
			   if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
			    var date;
			    if (typeof options.expires == 'number') {
			     date = new Date();
			     date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
			    } else {
			     date = options.expires;
			    }
			    expires = '; expires=' + date.toUTCString();
			   }
			   var path = options.path ? '; path=' + (options.path) : '';
			   var domain = options.domain ? '; domain=' + (options.domain) : '';
			   var secure = options.secure ? '; secure' : '';
			   document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
			} else {
			   var cookieValue = null;
			   if (document.cookie && document.cookie != '') {
			    var cookies = document.cookie.split(';');
			    for (var i = 0; i < cookies.length; i++) {
			     var cookie = jQuery.trim(cookies[i]);
			     if (cookie.substring(0, name.length + 1) == (name + '=')) {
			      cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
			      break;
			     }
			    }
			   }
			   return cookieValue;
			}
			};
			
			function setbinding_cookie(api,json){
				$("#api").val(api)
				$("#param").val(json)
				type = 'cookie';
				
			}
			
		    function setbinding4xml(api,json){
		    	setbinding(api,json,null,'xml');
		    }
		
	</script>
  
</html>

