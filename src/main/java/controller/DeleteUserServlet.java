package controller;

import dao.DaoClass;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        DaoClass dao = new DaoClass();
        boolean deleted = dao.deleteUser(id);

        res.setContentType("application/json");

        if (deleted) {
            res.setStatus(HttpServletResponse.SC_OK);
            res.getWriter().write("{\"message\":\"model.User deleted successfully\"}");
        } else {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res.getWriter().write("{\"message\":\"model.User not found\"}");
        }
    }
}
