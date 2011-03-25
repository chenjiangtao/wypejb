<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.foshanshop.ejb3.Friend,com.foshanshop.ejb3.Person,
				javax.naming.*"%>
<%
try {
	InitialContext ctx = new InitialContext();
	Friend friend = (Friend) ctx.lookup("FriendBean/remote");
	out.println("被注入的对象为:"+ friend.getFriend());
} catch (Exception e) {
	out.println(e.getMessage());
}
%>