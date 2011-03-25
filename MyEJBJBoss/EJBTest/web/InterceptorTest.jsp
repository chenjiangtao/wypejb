<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.foshanshop.ejb3.*, javax.naming.*"%>
<%
	try {
		InitialContext ctx = new InitialContext();
		HelloChinaRemote hellochinaremote = (HelloChinaRemote) ctx.lookup("HelloChinaBean/remote");
		out.println(hellochinaremote.SayHello("·ðÉ½ÈË"));
		out.println("<br>"+ hellochinaremote.Myname());
	} catch (NamingException e) {
		out.println(e.getMessage());
	}
%>
