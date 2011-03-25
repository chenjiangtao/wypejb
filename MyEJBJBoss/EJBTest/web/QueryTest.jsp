<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.foshanshop.ejb3.QueryDAO,
				javax.naming.*,
				java.util.*"%>
<%
	try {
		String index = request.getParameter("index");
		if (index!=null && !"".equals(index.trim())){
			InitialContext ctx = new InitialContext();
			QueryDAO querydao = (QueryDAO) ctx.lookup("QueryDAOBean/remote");
			querydao.initdate();
			out.println(querydao.ExecuteQuery(Integer.parseInt(index)));
		}
	} catch (Exception e) {
		out.println(e.getMessage());			
	}
%>