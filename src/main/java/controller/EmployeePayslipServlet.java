package controller;

import dao.DaoClass;
import model.Employee;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getPayslip")
public class EmployeePayslipServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("username") == null) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String username = session.getAttribute("username").toString();

        DaoClass dao = new DaoClass();
        Employee emp = dao.getEmployeeByUsername(username);

        double gross = emp.getBaseSalary() + emp.getHra() + emp.getDa();

        res.setContentType("application/json");
        PrintWriter out = res.getWriter();

        out.print("{");
        out.print("\"username\":\"" + emp.getUsername() + "\",");
        out.print("\"baseSalary\":" + emp.getBaseSalary() + ",");
        out.print("\"hra\":" + emp.getHra() + ",");
        out.print("\"da\":" + emp.getDa() + ",");
        out.print("\"gross\":" + gross);
        out.print("}");
    }
}
