package servlets.servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    UserService service = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            List<User> users = service.getAllUsers();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/WEB-INF/jsp/users.jsp").forward(req, resp);
        } else if (action.equals("update")) {
            User user = service.getUserById(Long.parseLong(req.getParameter("id")));
            req.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/jsp/userEdit.jsp").forward(req, resp);
        }
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String ageStr = req.getParameter("age");
        int age = 0;
        if (ageStr != null) {
            age = Integer.parseInt(ageStr);
        }

        switch (action) {
            case "add":
                if (isValidate(name, surname, age)) {
                    service.create(new User(name, surname, age));
                }
                break;
            case "update":
                doPut(req, resp);
                break;
            case "delete":
                doDelete(req, resp);
                break;
        }

        resp.sendRedirect("/admin");
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String ageStr = req.getParameter("age");
        int age = 0;
        if (ageStr != null) {
            age = Integer.parseInt(ageStr);
                    }
        long id = 0L;
        if (idStr != null) {
            id = Integer.parseInt(idStr);
        }
        if (isValidate(name, surname, age)) {
            service.update(new User(id, name, surname, age));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        long id = 0L;
        if (idStr != null) {
            id = Integer.parseInt(idStr);
            service.delete(id);
        }
    }

    boolean isValidate(String s1, String s2, int i) {
        return !s1.isEmpty() && !s2.isEmpty() && i >= 0;
    }

}
