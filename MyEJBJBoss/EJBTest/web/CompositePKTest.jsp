<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.foshanshop.ejb3.AirLineDAO,com.foshanshop.ejb3.bean.*,
				javax.naming.*, 
				java.util.*"%>
<%	
	try {
		InitialContext ctx = new InitialContext();
		AirLineDAO airlinedao = (AirLineDAO) ctx.lookup("AirLineDAOBean/remote");
		airlinedao.insertAirLine();

		AirLine airLine = airlinedao.getAirLineByID("PEK","CAN"); 
		out.println("航线："+ airLine.getId().getLeavecity() +"--"+ airLine.getId().getArrivecity() +"<br>");
		out.println("============== 存在以下航班 =================<br>");
		if (airLine!=null){
	        for(Flight flight : airLine.getFlights()){
	        	out.println("航班:"+ flight.getFlightno() +"<br>");
	        }
		}else{
			out.println("没有找到相关航线");
		}
	} catch (Exception e) {
		out.println(e.getMessage());
	}
%>