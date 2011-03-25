<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.foshanshop.ejb3.*, javax.naming.*"%>
<%

	try {
		LifeCycle lifecycle = (LifeCycle) session.getAttribute("lifecycle");
		if (lifecycle == null) {
		  InitialContext ctx = new InitialContext();
		  lifecycle = (LifeCycle) ctx.lookup("LifeCycleBean/remote");
		  session.setAttribute ("lifecycle", lifecycle);
		}
		out.println(lifecycle.Say());
		out.println("<BR>请注意观察Jboss控制台输出.等待10分钟,容器将会钝化此会话Bean,@PrePassivate注释的方法将会执行<BR>");			
		out.println("<font color=red>你可以调用stopSession方法把会话Bean实例删除。在删除会话Bean时，将触发@PreDestroy事件<BR></font>");	
		/*
		lifecycle.stopSession();
		*/
	} catch (Exception e) {
		out.println(e.getMessage());
	}
%>
