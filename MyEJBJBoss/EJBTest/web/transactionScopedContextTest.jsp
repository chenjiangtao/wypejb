<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.foshanshop.ejb3.ContextDAO,com.foshanshop.ejb3.bean.Context,
				javax.naming.*"%>
				
<%
try {
		InitialContext ctx = new InitialContext();
		ContextDAO transactionScopeddao = (ContextDAO) ctx.lookup("TransactionScopedBean/remote");
		transactionScopeddao.initdata("小张");
		out.println("实体bean目前是否处于托管状态: <font color=red>"+ transactionScopeddao.contains()+ "</font>");
		transactionScopeddao.updateName("田力");
		Context context = transactionScopeddao.getContext();
		out.println("<br>数据库中的名称为:"+ context.getName());
} catch (Exception e) {
	out.println(e.getMessage());
}
%>