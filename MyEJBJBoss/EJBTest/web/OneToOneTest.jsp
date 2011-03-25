<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.foshanshop.ejb3.OneToOneDAO,com.foshanshop.ejb3.bean.*,
				javax.naming.*,
				java.text.SimpleDateFormat,
				java.util.*"%>
<%
	try {
		InitialContext ctx = new InitialContext();
		String outformate = "<font color=blue>CMD>>Out>></font> ";
		OneToOneDAO oneToonedao = (OneToOneDAO) ctx.lookup("OneToOneDAOBean/remote");
		SimpleDateFormat formatter = new SimpleDateFormat("MMddhhmmss");
		String endno = formatter.format(new Date()).toString();
        Person newperson = new Person();
        newperson.setName("叶子由");
        newperson.setSex(true);
        newperson.setAge((short)26);
        newperson.setBirthday(new Date());
        IDCard idcard = new IDCard("44011"+endno);
        idcard.setPerson(newperson);
        newperson.setIdcard(idcard);
		oneToonedao.insertPerson(newperson);
		//添加时请注意，身份证号不要重复，因为数据库字段身份证号是唯一的
		Person person = oneToonedao.getPersonByID(new Integer(1)); 
		if (person!=null){
			out.println(outformate +"寻找编号为1的人员<br>");
			out.println("姓名:"+ person.getName() +" 身份证："+ person.getIdcard().getCardno() +"<br>");
		}else{
			out.println("没有找到编号为1的人员<br>");
		}
		out.println(outformate +"更新编号为1的人员的姓名为李明,身份证号为33012" +endno +"<br>");
		oneToonedao.updatePersonInfo(new Integer(1), "李明", "33012" +endno);			
		
		out.println("================删除编号为3的人员==============<br>");	
		oneToonedao.deletePerson(new Integer(3));
		
	} catch (Exception e) {
		out.println(e.getMessage());
	}
%>