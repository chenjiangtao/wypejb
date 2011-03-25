<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.foshanshop.ejb3.*, javax.naming.*"%>
<%
	try {
		InitialContext ctx = new InitialContext();
		TimerServiceDAO timer = (TimerServiceDAO) ctx.lookup("TimerServiceBean/remote");
		timer.scheduleTimer((long)3000);
		out.println("定时器已经启动，请观察Jboss控制台输出，如果定时器触发5次，便终止定时器");
	} catch (Exception e) {
		out.println(e.getMessage());
	}
%>