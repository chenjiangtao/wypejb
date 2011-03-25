<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.foshanshop.ejb3.PersonDAO,com.foshanshop.ejb3.bean.Person,
				javax.naming.*, 
				java.util.Properties, 
				java.util.Date,
				java.util.List"%>

<TABLE width="80%" border="1">
<TR bgcolor="#DFDFDF">
	<TD>personid</TD>
	<TD>name</TD>
	<TD>sex</TD>
	<TD>age</TD>
	<TD>birthday</TD>
</TR>
<%
try {
		InitialContext ctx = new InitialContext();
	    PersonDAO persondao = (PersonDAO) ctx.lookup("PersonDAOBean/remote");
		Person newperson = new Person();
		newperson.setName("ÕÅÀÊ");
		newperson.setAge((short)27);
		newperson.setBirthday(new Date());
		newperson.setSex(true);
		persondao.insertPerson(newperson);
		
		List<Person> persons = persondao.getPersonList();
		for(Person person : persons){
			out.println("<TR><TD>"+ person.getPersonid()+"</TD><TD>"+ person.getName()+"</TD><TD>"+ person.getSex()+"</TD><TD>"+ person.getAge()+"</TD><TD>"+ person.getBirthday()+"</TD></TR>");
        }

} catch (Exception e) {
	out.println(e.getMessage());
}
%>
</TABLE>