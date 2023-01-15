<%@ page import="me.hy.libhyextended.sql.SQLConnection" %>
<%@ page import="org.mcmasterkboys.codenamehenryford.modules.SQLConnectionFactory" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="org.mcmasterkboys.codenamehenryford.objects.User" %>
<%@ page import="java.util.UUID" %>
<%@ page import="org.mcmasterkboys.codenamehenryford.objects.Gender" %>
<%@ page import="org.mcmasterkboys.codenamehenryford.objects.Address" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sign Up</title>
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
            justify-content: center;
            display: flex;
            flex-direction: column;
        }
        .center {
            display: flex;
            justify-content: center;
        }
    </style>
</head>
<body>

<%
    if (request.getParameter("firstName") != null) {
        try {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            //            String phoneNumber = request.getParameter("phoneNumber");
            //            String gender = request.getParameter("gender");


            SQLConnection c = new SQLConnectionFactory().getConnection();
            ResultSet rs = c.executeQuery("SELECT * FROM " + new User().getTableName() + " WHERE email = ?", email);
            if (rs.next()) {
                response.sendRedirect("signup.jsp?error=1"); // Email already in use
                rs.close();
                c.close();
                return;
            }
            rs.close();
            c.close();

            String random6digit = (int) (Math.random() * 1000000) + "";
            User u = new User(UUID.randomUUID().toString(), lastName, firstName, email, password, "(000)000-0000", Gender.OTHER.toString(), random6digit, false, new Address());
            u.setPassword(u.hashPassword(u.getPassword()));
            u.insert();
            u.sendVerificationCode();

            session.setAttribute("task", "signup");
            session.setAttribute("queue", u);

            response.sendRedirect("verification.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("signup.jsp?error=2");
        }
    }
%>

<%
    String error = request.getParameter("error");
    if (error != null) {
        if (error.equals("1")) {
            %>
            <script>
                alert("Email already in use");
            </script>

            <%
        }else if (error.equals("2")) {
            %>
            <script>
                alert("An error occurred.");
            </script>
            <%
        }
    }
%>



<div class="w-[1920px] h-[1080px] relative overflow-hidden bg-[#4c89f8]/60">
    <div class="w-[782px] h-[826px] absolute left-[550px] top-[40px] overflow-hidden rounded-[20px] bg-white">
        <div class="w-[604px] h-[623px]">
            <form action="./signup.jsp" method="post">
                <div class="w-[600px] h-[99px]">
                    <p class="absolute left-[98px] top-[151px] text-xl font-medium text-left text-[#4d5959]"> First Name </p>
                    <div class="w-[600px] h-[65px]">
                        <input type="text" name="firstName" class="w-[600px] h-[65px] absolute left-[95.5px] top-[184.5px] rounded-[15px] bg-[#eff0f2]"></input>
<%--                        <p class="absolute left-[125px] top-[202px] text-xl text-left text-[#838383]">John</p>--%>
                    </div>
                </div>
                <div class="w-[600px] h-[99px]">
                    <p class="absolute left-[98px] top-[274px] text-xl font-medium text-left text-[#4d5959]"> Last Name </p>
                    <div class="w-[600px] h-[65px]">
                        <input type="text" name="lastName" class="w-[600px] h-[65px] absolute left-[95.5px] top-[307.5px] rounded-[15px] bg-[#eff0f2]"></input>
<%--                        <p class="absolute left-[125px] top-[325px] text-xl text-left text-[#838383]">Doe</p>--%>
                    </div>
                </div>
                <div class="w-[600px] h-[98px]">
                    <p class="absolute left-[100px] top-[397px] text-xl font-medium text-left text-[#4d5959]"> Email </p>
                    <div class="w-[600px] h-[65px]">
                        <input type="text" name="email" class="w-[600px] h-[65px] absolute left-[97.5px] top-[429.5px] rounded-[15px] bg-[#eff0f2]"></input>
<%--                        <p class="absolute left-[127px] top-[448px] text-xl text-left text-[#838383]"> Enter your Email here </p>--%>
                    </div>
                </div>
                <div class="w-[600px] h-[99px]">
                    <p class="absolute left-[102px] top-[519px] text-xl font-medium text-left text-[#4d5959]"> Password </p>
                    <div class="w-[600px] h-[65px]">
                        <input type="password" name="password" class="w-[600px] h-[65px] absolute left-[99.5px] top-[552.5px] rounded-[15px] bg-[#eff0f2]"></input>
<%--                        <p class="absolute left-[129px] top-[570px] text-xl text-left text-[#838383]"> Enter your Password </p>--%>
                    </div>
                </div>
                <div class="w-[300px] h-[111px]">
                    <p class="absolute left-[243px] top-[747px] text-lg text-left">
                        <span class="text-lg text-left text-[#4d5959]">Already have an account?</span>
                        <span class="text-lg text-left text-black"></span>
                        <span class="text-lg font-medium text-left text-[#4c89f8]/60"><a href="login.jsp">Log in</a></span>
                    </p>
                    <button type="submit" class="flex justify-center items-center w-[300px] h-[60px] absolute left-[241px] top-[663px] gap-2.5 rounded-[15px] bg-[#4c89f8]/60">
                        <p class="flex-grow-0 flex-shrink-0 text-[26px] font-medium text-left text-white"> Create Account </p>
                    </button>
                </div>
            </form>
        </div>
        <p class="w-[488px] h-[95px] absolute left-36 top-[52px] text-5xl font-semibold text-center text-[#043133]"> Let’s Get Started! </p>
    </div>
</div>
</body>
</html>