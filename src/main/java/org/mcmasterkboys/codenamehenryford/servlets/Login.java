package org.mcmasterkboys.codenamehenryford.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.mcmasterkboys.codenamehenryford.objects.User;

import java.io.IOException;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User u = new User();
        try {
            u.loadUsingEmail(email);

            if (u.getPassword().equals(u.hashPassword(password))) {
                u.setVerificationCode((int)(Math.random() * 1000000) + "");
                u.sendVerificationCode();

                Cookie c = new Cookie("identity", u.getUuid() + "/" + u.getPassword());
                c.setMaxAge(60 * 60 * 24 * 7 * 2); // Keep login for 2 weeks
                response.addCookie(c);

                session.setAttribute("user", u);
                request.getSession().setAttribute("task", "login");

                // TODO: If task-login is set, then verify.jsp will read u and remove from session. If verified, then assign again.
                response.sendRedirect("/verify.jsp");
            } else {
                response.sendRedirect("/login.jsp?error=2");
            }

        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/login.jsp?error=1");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}
