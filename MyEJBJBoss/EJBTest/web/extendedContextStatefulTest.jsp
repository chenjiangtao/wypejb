<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.foshanshop.ejb3.ContextDAO,com.foshanshop.ejb3.bean.Context,
				javax.naming.*"%>
				
<%
try {
		InitialContext ctx = new InitialContext();
		ContextDAO extendedStatefuldao = (ContextDAO) ctx.lookup("ExtendedStatefulBean/remote");
		extendedStatefuldao.initdata("李明");
		out.println("实体bean目前是否处于托管状态: <font color=red>"+ extendedStatefuldao.contains()+ "</font>");
		extendedStatefuldao.updateName("何为");
		Context context = extendedStatefuldao.getContext();
		out.println("<br>数据库中的名称为:"+ context.getName());
} catch (Exception e) {
	out.println(e.getMessage());
}
%>