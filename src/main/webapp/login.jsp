<%@ page import="org.mcmasterkboys.codenamehenryford.objects.User" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        html,
        body {
            display: flex;
            flex-direction: column;
            flex: 1;
            width: 100%;
            height: 100%;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }
    </style>
</head>
<body>
<%
    // Check cookie. If cookie is present, set session
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("identity")) {

                String cookieValueDecoded = URLDecoder.decode(cookie.getValue(), "UTF-8");
                String uuid = cookieValueDecoded.split("/")[0];
                String password = cookieValueDecoded.split("/")[1];

                User u = new User();
                u.setPkValue(uuid);
                try {
                    u.select();
                }catch (Exception e) {
                    e.printStackTrace();
                }

                if (u.getPassword().equals(password)) {
                    session.setAttribute("user", u);
                    response.sendRedirect("index.jsp");
                    return;
                }
                break;
            }
        }
    }
    // If cookie is not present, then check if error message occurred
    // Checking error message
    String error = request.getParameter("error");

    String message = "";

    if (error != null) {
        if (error.equals("1")) {
            message = "- Unknown error occurred";
        } else if (error.equals("2")) {
            message = "- Invalid username or password";
        }
    }
%>
<div class="w-[1440px] h-[1024px] relative overflow-hidden bg-[#4c89f8]/60">
    <div class="w-[782px] h-[826px] absolute left-[329px] top-[99px] overflow-hidden rounded-[20px] bg-white">
        <div class="w-[602px] h-[472px]">
            <form action="Login" method="post">
                <div class="w-[600px] h-[98px]">
                    <p class="absolute left-[92px] top-[302px] text-xl font-medium text-left text-[#4d5959]"> Email <%=message%> </p>
                    <div class="w-[600px] h-[65px]">
                        <input type="text" name="email" class="w-[600px] h-[65px] absolute left-[89.5px] top-[334.5px] rounded-[15px] bg-[#eff0f2]">
                    </div>
                </div>
                <div class="w-[600px] h-[99px]">
                    <p class="absolute left-[94px] top-[424px] text-xl font-medium text-left text-[#4d5959]"> Password <%=message%> </p>
                    <div class="w-[600px] h-[65px]">
                        <input type="password" name="password" id="" class="w-[600px] h-[65px] absolute left-[91.5px] top-[457.5px] rounded-[15px] bg-[#eff0f2]">
                    </div>
                </div>
                <div class="w-[300px] h-[111px]">
                    <p class="absolute left-[243px] top-[747px] text-lg text-left">
                        <span class="text-lg text-left text-black">Having trouble logging in? </span>
                        <span class="text-lg font-medium text-left text-[#4c89f8]/60">Help</span>
                    </p>
                    <button type="submit" class="flex justify-center items-center w-[300px] h-[60px] absolute left-[241px] top-[663px] gap-2.5 rounded-[15px] bg-[#4c89f8]/60"/>
                        <p class="flex-grow-0 flex-shrink-0 text-[26px] font-medium text-left text-white"> Login </p>
                    </button>
                </div>
            </form>
        </div>
        <p class="w-[488px] h-[95px] absolute left-[148px] top-[134px] text-5xl font-semibold text-center text-[#043133]"> Let’s log you in! </p>
    </div>
</div>
</body>
</html>