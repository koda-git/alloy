<%@ page import="org.mcmasterkboys.codenamehenryford.objects.User" %>
<%@ page import="me.hy.libhyextended.objects.DatabaseObject" %><%--
  Created by IntelliJ IDEA.
  User: hoyounsong
  Date: 2023-01-15
  Time: 12:29 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Verification</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        .center {
        display: flex;
        justify-content: center;
        }
        html,
        body {
            justify-content: center;
            display: flex;
            flex-direction: column;
            flex: 20;
            width: 100%;
            height: 100%;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }
    </style>
</head>

<%
    if (session.getAttribute("task") == null) {
        // Not allowed to be here
        response.sendRedirect("index.jsp");
    }

    if (request.getParameter("error") != null) {
        %>
        <script>
            alert("An error occurred. Please try again.");
        </script>
        <%
    }

    if (request.getParameter("code") != null) {
        try {
            String code = request.getParameter("code");
            User user = (User) session.getAttribute("queue");

            if (user.getVerificationCode().equals(code)) {
                // Verified
                user.setVerified(true);
                user.setPkValue(user.getUuid());
                DatabaseObject.doPrintQuery = true;
                user.update();
                session.setAttribute("user", user);
                response.sendRedirect("index.jsp");
            } else {
                // Incorrect code
                response.sendRedirect("verification.jsp");
            }
        }catch (Exception e) {
            e.printStackTrace();
            %>
                <script>
                    alert("An error occurred. Please try again.");
                </script>
            <%
        }
    }
%>

<body>
<div class="w-[1920px] h-[1080px] object-fill relative overflow-hidden bg-[#4c89f8]/60">
    <div class="w-[782px] h-[826px] absolute left-[550px] top-[40px] overflow-hidden rounded-[20px] bg-white">
        <div class="w-[601px] h-[443px]">
            <form action="verification.jsp" method="post">
                <div class="w-[600px] h-[99px]">
                    <p class="absolute left-[94px] top-[477px] text-xl font-medium text-left text-[#4d5959]"> Verification Code </p>
                    <div type="text" class="w-[600px] h-[65px]">
                        <input type="text" name="code" class="w-[600px] h-[65px] absolute left-[91.5px] top-[510.5px] rounded-[15px] bg-[#eff0f2]"></input>
<%--                        <p class="absolute left-[121px] top-[528px] text-xl text-left text-[#838383]"> Enter Code </p>--%>
                    </div>
                </div>
                <div class="w-[300px] h-[111px]">
                    <p class="absolute left-[243px] top-[747px] text-lg text-left">
                        <span class="text-lg text-left text-black">Having trouble logging in? </span>
                        <span class="text-lg font-medium text-left text-[#4c89f8]/60">Help</span>
                    </p>
                    <button type="submit" class="flex justify-center items-center w-[300px] h-[60px] absolute left-[241px] top-[663px] gap-2.5 rounded-[15px] bg-[#4c89f8]/60">
                        <p class="flex-grow-0 flex-shrink-0 text-[26px] font-medium text-left text-white"> Verify </p>
                    </button>
                </div>
            </form>
            <div class="w-[600px] h-[60px]">
                <p class="w-[600px] absolute left-[91px] top-[331px] text-xl font-medium text-center text-[#4d5959]">
                    <span class="w-[600px] text-xl font-medium text-center text-[#4d5959]">We have sent a 6 digits code to your email. </span>
                    <br />
                    <span class="w-[600px] text-xl font-medium text-cente__r text-[#4d5959]">Please enter the code below to login</span>
                </p>
            </div>
        </div>
        <p class="w-[488px] h-[95px] absolute left-[148px] top-[134px] text-5xl font-semibold text-center text-[#043133]"> Enter Verification Code </p>
    </div>
</div>
</body>
</html>
