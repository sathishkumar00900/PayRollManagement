package controller;

import dao.DaoClass;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/empLogin")
public class EmployeeLoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String username= req.getParameter("username");
        String password = req.getParameter("password");

        DaoClass dao = new DaoClass();

        if (dao.checkEmpLogin(username, password)) {

            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            res.sendRedirect("employeeDashboard.html");
        } else {
            res.sendRedirect("employeeLogin.html?error=true");
        }
    }
}


