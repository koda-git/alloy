<%@ page import="org.mcmasterkboys.codenamehenryford.objects.posts.Board" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="org.mcmasterkboys.codenamehenryford.modules.SQLConnectionFactory" %>
<%@ page import="me.hy.libhyextended.sql.SQLConnection" %>
<%@ page import="org.mcmasterkboys.codenamehenryford.objects.posts.Post" %><%--
  Created by IntelliJ IDEA.
  User: hoyounsong
  Date: 2023-01-15
  Time: 3:50 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
    <title>Board - <%=request.getParameter("title")%></title>
</head>
<body>
    <%
        String boardUUID = request.getParameter("board");
        Board b = null;
        try {
            b = new Board(boardUUID);
        }catch (Exception e){
            e.printStackTrace();
        }
    %>
    <h1><%=b.getName()%></h1>
    <h2><%=b.getDescription()%></h2>

    <h3>Posts</h3>
    <hr>
    <%
        SQLConnection sql = new SQLConnectionFactory().getConnection();
        sql.setUseAutoClose(true);
        ResultSet rs = null;
        try {
            rs = sql.executeQuery("SELECT * FROM " + new Post().getTableName() + " WHERE boardUUID = ?", boardUUID);
        }catch (Exception e){
            e.printStackTrace();
        }

        while (rs.next()){
            Post p = new Post();
            p.mapFromResultSet(rs);
        %>
        <a href="postview.jsp?id=<%=p.getId()%>"><h4>Title</h4></a>
        <h5>[<%=p.getRating()%>] <%=p.getYear()%> - <%=p.getMonth()%> - <%=p.getDay()%> <%=p.getHour()%>:<%=p.getMinute()%>:<%=p.getSecond()%> by <%=p.getAuthorName()%></h5>
        <h6><%=Math.round(((float) p.getLikes() / (p.getLikes() + p.getDislikes()))*100)%>% people felt helpful.</h6>
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
