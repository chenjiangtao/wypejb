<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.foshanshop.ejb3.*, javax.naming.*"%>
<%
	try {
		InitialContext ctx = new InitialContext();
		Injection injection = (Injection) ctx.lookup("InjectionBean/remote");
		out.println(injection.SayHello());
	} catch (NamingException e) {
		out.println(e.getMessage());
	}
%>
