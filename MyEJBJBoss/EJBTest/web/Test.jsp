<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.foshanshop.ejb3.HelloWorld, javax.naming.*, java.util.Properties"%>
<%
		Properties props = new Properties();
		props.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		props.setProperty("java.naming.provider.url", "localhost:1099");
		try {
			InitialContext ctx = new InitialContext(props);
			HelloWorld helloworld = (HelloWorld) ctx.lookup("HelloWorldBean/remote");
			out.println(helloworld.SayHello("·ðÉ½ÈË"));
		} catch (NamingException e) {
			out.println(e.getMessage());
		}
%>
