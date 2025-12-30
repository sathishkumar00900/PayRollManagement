package controller;
import com.google.gson.Gson;
import dao.DaoClass;
import model.Employee;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class AdminDashboardServlet extends HttpServlet {
    public void doGet (HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        List<Employee> users = null;
        try {
            users = new DaoClass().getAllUsers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Gson gson = new Gson();
        String json = gson.toJson(users);

        res.getWriter().write(json);
    }
}
