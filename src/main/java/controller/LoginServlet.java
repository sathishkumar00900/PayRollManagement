package controller;

import dao.DaoClass;
import model.LoginResult;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String username= req.getParameter("username");
        String password = req.getParameter("password");

        DaoClass dao = new DaoClass();
        LoginResult result = dao.checkLogin(username, password);



        if(result.isSuccess()){

            HttpSession session = req.getSession(true);
            session.setAttribute("username", username);
            session.setAttribute("role", result.getRole());

            if ("Admin".equals(result.getRole())) {
                res.sendRedirect("adminDashboard.html");
            }
            else if ("Manager".equals(result.getRole())) {
                res.sendRedirect("");
            }
            else if("Employee".equals(result.getRole())) {
                res.sendRedirect("employeeDashboard.html");
            }
            else {
                res.sendRedirect("index.html?error=true");
            }
        }
    }
}


