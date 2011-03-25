<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.foshanshop.ejb3.LocalHelloWorld, javax.naming.*"%>
<%
		try {
			InitialContext ctx = new InitialContext();
			LocalHelloWorld helloworld = (LocalHelloWorld) ctx.lookup("LocalHelloWorldBean/local");
			out.println(helloworld.SayHello("·ðÉ½ÈË"));
		} catch (NamingException e) {
			out.println(e.getMessage());
		}
%>
