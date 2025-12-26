package controller;

import com.google.gson.Gson;
import dao.DaoClass;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/users")
public class UserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        DaoClass dao = new DaoClass();
        String json = new Gson().toJson(dao.getAllUsers());
        res.getWriter().print(json);
    }
}
