<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.foshanshop.ejb3.*,java.util.*,javax.naming.*"%>
<%
	try {
		InitialContext ctx = new InitialContext();
		Cart cat = (Cart)session.getAttribute("cat");
		if(cat==null){//创建一个购物车
			cat = (Cart) ctx.lookup("CartBean/remote");
			session.setAttribute("cat", cat);
		}
		cat.AddBuyItem("《EJB3.0实例教程》");
		List<String> buyitem = cat.getBuyItem();
		out.println("购物车的商品列表：<br>");
		for(String name : buyitem){
			out.println("  "+ name+ "<br>");
		}		
	} catch (Exception e) {
		out.println(e.getMessage());
	}
%>
