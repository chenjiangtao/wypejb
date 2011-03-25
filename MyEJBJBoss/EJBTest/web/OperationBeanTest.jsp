<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.foshanshop.ejb3.*, javax.naming.*, java.util.Properties"%>
<%
		InitialContext ctx = new InitialContext();
		try {
			//通过远程接口调用EJB
			Operation remote = (Operation) ctx.lookup("OperationBean/remote");
			out.println("<br>(通过远程接口调用EJB)累加的结果是："+ remote.Addup());
		} catch (Exception e) {
			out.println("<br>远程接口调用失败");
		}
		out.println("<br>==============================================");
		try {
			//通过本地接口调用EJB
			LocalOperation local = (LocalOperation) ctx.lookup("OperationBean/local");
			out.println("<br>(通过本地接口调用EJB)累加的结果是："+ local.Addup());
		} catch (Exception e) {
			out.println("<br>本地接口调用失败");
		}
%>
