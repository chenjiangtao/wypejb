<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.foshanshop.ejb3.TeacherDAO,com.foshanshop.ejb3.bean.*,
				javax.naming.*, 
				java.util.*"%>
<%
	try {
		InitialContext ctx = new InitialContext();
		TeacherDAO teacherdao = (TeacherDAO) ctx.lookup("TeacherDAOBean/remote");
		Teacher newteacher = new Teacher("何为");
		newteacher.addStudent(new Student("叶子由"));
		newteacher.addStudent(new Student("陈光照"));
		teacherdao.insertTeacher(newteacher);
		
		List<Teacher> teachers = teacherdao.getAllTeacher();
		for(Teacher teacher : teachers){
			out.println("======= 获取编号为1的老师姓名："+ teacher.getTeacherName() +" ======<br>");
			for(Student student : teacher.getStudents()){
				out.println("&nbsp;&nbsp;他的学生:"+ student.getStudentName() +"<br>");
			}
		}
	} catch (Exception e) {
		out.println(e.getMessage());
	}
%>