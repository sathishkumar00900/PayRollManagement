import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {

    protected void doPut(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        BufferedReader reader = req.getReader();
        User user = gson.fromJson(reader, User.class);

        DaoClass dao = new DaoClass();
        boolean updated = dao.updateUser(user);

        if (updated) {
            res.setStatus(HttpServletResponse.SC_OK);
            res.getWriter().write("{\"message\":\"User updated successfully\"}");
        } else {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res.getWriter().write("{\"message\":\"User not found or no changes made\"}");
        }
    }
}
