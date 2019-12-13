package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("")
public class UserServlet extends HttpServlet {
    UserService service = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            List<User> users = service.getAllUsers();
            req.setAttribute("users", users);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else if (action.equals("update")) {
            User user = service.getUserById(Long.parseLong(req.getParameter("id")));
            req.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/jsp/userEdit.jsp").forward(req, resp);
        } else if (action.equals("delete")) {
            service.delete(Long.parseLong(req.getParameter("id")));
            resp.sendRedirect("/");
        }
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        Integer age = Integer.parseInt(req.getParameter("age"));
        if (!name.isEmpty() && !surname.isEmpty() && age >= 0) {
            if (id == null) {
                service.create(new User(name, surname, age));
            } else {
                service.update(new User(Long.parseLong(id), name, surname, age));
            }
        }
        resp.sendRedirect("/");
    }
}
