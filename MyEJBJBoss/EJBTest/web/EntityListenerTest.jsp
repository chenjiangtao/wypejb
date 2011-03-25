<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.foshanshop.ejb3.*, com.foshanshop.ejb3.bean.*,
				javax.naming.*"%>
<%
	try {
		out.println("输出信息打印在Jboss控制台");
		InitialContext ctx = new InitialContext();
	    EntityLifecycleDAO entitylifecycledao = (EntityLifecycleDAO) ctx.lookup("EntityLifecycleDAOBean/remote");	
		entitylifecycledao.Persist(); //添加测试数据
		entitylifecycledao.Load(); //载入数据 
		entitylifecycledao.Update(); //更新数据 
		
		entitylifecycledao.Remove(); //删除最后一条数据
		
	} catch (Exception e) {
		out.println(e.getMessage());
	}
%>