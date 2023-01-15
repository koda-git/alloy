package org.mcmasterkboys.codenamehenryford.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.hy.libhyextended.sql.SQLConnection;
import org.mcmasterkboys.codenamehenryford.modules.SQLConnectionFactory;
import org.mcmasterkboys.codenamehenryford.objects.Address;
import org.mcmasterkboys.codenamehenryford.objects.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.UUID;

@WebServlet(name = "Signup", value = "/signup")
public class Signup extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String phoneNumber = request.getParameter("phoneNumber");
            String gender = request.getParameter("gender");


            SQLConnection c = new SQLConnectionFactory().getConnection();
            ResultSet rs = c.executeQuery("SELECT * FROM " + new User().getTableName() + " WHERE email = ?", email);
            if (rs.next()) {
                response.sendRedirect("/signup?error=1"); // Email already in use
                rs.close();
                c.close();
                return;
            }
            rs.close();
            c.close();

            String random6digit = (int)(Math.random() * 1000000) + "";
            User u = new User(UUID.randomUUID().toString(), lastName, firstName, email, password, phoneNumber, gender, random6digit, false, new Address());
            u.insert();
            u.sendVerificationCode();

            request.getSession().setAttribute("task", "signup");

            response.sendRedirect("/verify.jsp");

        }catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/signup.jsp?error=1");
        }
    }
}
