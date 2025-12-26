package controller;

import com.google.gson.Gson;
import model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        res.setContentType("application/json");
        Gson gson = new Gson();
        User user = gson.fromJson(req.getReader(), User.class);

        DaoClass dao = new DaoClass();
        boolean added = dao.addUser(user);

        if (added) {
            res.setStatus(HttpServletResponse.SC_CREATED);
            res.getWriter().write("{\"message\":\"model.User registered successfully\"}");
        } else {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.getWriter().write("{\"message\":\"model.User registration failed\"}");
        }
    }
}
