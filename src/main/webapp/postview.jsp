<%@ page import="org.mcmasterkboys.codenamehenryford.objects.posts.Post" %>
<%@ page import="me.hy.libhyextended.objects.DatabaseObject" %><%--
  Created by IntelliJ IDEA.
  User: hoyounsong
  Date: 2023-01-15
  Time: 3:55 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View</title>
</head>
<body>


<%
    String id = request.getParameter("id");
    Post p = null;
    try {
        p = new Post(id);
    }catch (Exception e) {
        e.printStackTrace();
        %>
        <script>
            alert("Error: Post not found");
            window.location.href = "index.jsp";
        </script>
<%
    }
%>

<%
    if (request.getParameter("rate") != null) {
        int rate = Integer.parseInt(request.getParameter("rate"));
        if (rate > 0) {
            p.setLikes(p.getLikes() + 1);
        }else{
            p.setDislikes(p.getDislikes() + 1);
        }
        p.update();
    }
%>

<h1><%=p.getTitle()%></h1><br>
<h2><%=p.getYear()%>-<%=p.getMonth()%>-<%=p.getDay()%> <%=p.getHour()%>:<%=p.getMinute()%></h2><br>
<p><%=p.getContent()%></p><br>
<hr>
<p><%=Math.round(((float) p.getLikes() / (p.getLikes() + p.getDislikes()))*100)%>% of people rated this helpful.</p><br>
<a href="postview.jsp?id=<%=p.getId()%>&rate=1">Helpful</a> / <a href="postview.jsp?id=<%=p.getId()%>&rate=-1">Not helpful</a>
</body>
</html>
