<%@ page import="org.mcmasterkboys.codenamehenryford.objects.posts.Board" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="org.mcmasterkboys.codenamehenryford.modules.SQLConnectionFactory" %>
<%@ page import="me.hy.libhyextended.sql.SQLConnection" %>
<%@ page import="org.mcmasterkboys.codenamehenryford.objects.posts.Post" %>
<%@ page import="org.mcmasterkboys.codenamehenryford.objects.posts.Category" %><%--
  Created by IntelliJ IDEA.
  User: hoyounsong
  Date: 2023-01-15
  Time: 3:50 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Category - <%=request.getParameter("title")%></title>
</head>
<body>
<%
    String categoryUUID = request.getParameter("category");
    Category c = null;
    try {
        c = new Category(categoryUUID);
    }catch (Exception e){
        e.printStackTrace();
    }
%>
<h1><%=c.getName()%></h1>
<h2><%=c.getDescription()%></h2>

<h3>Boards</h3>
<hr>
<%
    SQLConnection sql = new SQLConnectionFactory().getConnection();
    sql.setUseAutoClose(true);
    ResultSet rs = null;
    try {
        rs = sql.executeQuery("SELECT * FROM " + new Board().getTableName() + " WHERE categoryUUID = ?", categoryUUID);
    }catch (Exception e){
        e.printStackTrace();
    }

    while (rs.next()){
        Board b = new Board();
        b.mapFromResultSet(rs);
%>
<a href="boardview.jsp?title=<%=b.getName()%>&board=<%=b.getUuid()%>"><h4><%=b.getName()%></h4></a>
<hr>
<%
    }
    try {
        rs.close();
        sql.close();
    }catch (Exception e){
        e.printStackTrace();
    }
%>
</body>
</html>
