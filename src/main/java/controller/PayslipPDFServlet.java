//package controller;
//
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;
//import dao.DaoClass;
//import model.Employee;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//
//@WebServlet("/downloadPayslip")
//public class PayslipPDFServlet extends HttpServlet {
//
//    protected void doGet(HttpServletRequest req, HttpServletResponse res)
//            throws IOException {
//
//        HttpSession session = req.getSession(false);
//        if (session == null || session.getAttribute("username") == null) {
//            res.sendRedirect("employeeLogin.html");
//            return;
//        }
//
//        String username = session.getAttribute("username").toString();
//
//        DaoClass dao = new DaoClass();
//        Employee emp = dao.getEmployeeByUsername(username);
//
//        double gross = emp.getBaseSalary() + emp.getHra() + emp.getDa();
//
//
//        res.setContentType("application/pdf");
//        res.setHeader("Content-Disposition", "attachment; filename=payslip.pdf");
//
//        try {
//            Document document = new Document();
//            PdfWriter.getInstance(document, res.getOutputStream());
//
//            document.open();
//
//            document.add(new Paragraph("model.Employee Payslip"));
//            document.add(new Paragraph(" "));
//            document.add(new Paragraph("model.Employee Name : " + emp.getUsername()));
//            document.add(new Paragraph("Base Salary  : " + emp.getBaseSalary()));
//            document.add(new Paragraph("HRA          : " + emp.getHra()));
//            document.add(new Paragraph("DA           : " + emp.getDa()));
//            document.add(new Paragraph("Gross Salary : " + gross));
//
//            document.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}