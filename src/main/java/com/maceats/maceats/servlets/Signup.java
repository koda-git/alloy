package com.maceats.maceats.servlets;

import com.maceats.maceats.struct.UserObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/signup")
public class Signup extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phonenumber");
            String macId = request.getParameter("macid");
            String password = request.getParameter("password");
            String residenceName = request.getParameter("resname");
            String residenceRoom = request.getParameter("resroom");

            if (username == null || email == null || phoneNumber == null || macId == null || password == null || residenceName == null || residenceRoom == null) {
                response.sendError(400, "Missing parameters");
                return;
            }

            // Create object
            UserObject o = new UserObject(username, email, phoneNumber, macId, password, residenceName, residenceRoom);
            o.save();

            // Success message
            response.getWriter().println("<script>alert('Successfully signed up!'); window.location.href = '/';</script>");
            
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}
