package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String username= req.getParameter("username");
        String password = req.getParameter("password");

        DaoClass dao = new DaoClass();

        if (dao.checkLogin(username, password)) {
            res.sendRedirect("adminDashboard.html");
        } else {
            res.sendRedirect("login.html?error=true");
        }
    }
}


