<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.foshanshop.ejb3.ContextDAO,com.foshanshop.ejb3.bean.Context,
				javax.naming.*"%>
				
<%
try {
		InitialContext ctx = new InitialContext();
		ContextDAO extendeddao = (ContextDAO) ctx.lookup("ExtendedBean/remote");
		extendeddao.initdata("小美");
		out.println("实体bean目前是否处于托管状态: <font color=red>"+ extendeddao.contains()+ "</font>");
		extendeddao.updateName("由子美");
		Context context = extendeddao.getContext();
		out.println("<br>数据库中的名称为:"+ context.getName());
} catch (Exception e) {
	out.println(e.getMessage());
}
%>