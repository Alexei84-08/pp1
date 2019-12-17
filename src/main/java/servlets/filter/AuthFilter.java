package servlets.filter;

import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter("/")
public class AuthFilter implements Filter {
    UserService service = UserService.getInstance();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest reg = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String login = reg.getParameter("login");
        String password = reg.getParameter("password");

        HttpSession session = reg.getSession();

        if (nonNull(session.getAttribute("login")) && nonNull(session.getAttribute("password"))) {
            moveToView(reg, resp, (String) session.getAttribute("role"));
        } else if (service.userIsExist(login, password)) {
            String roles = service.getRoleByLoginAndPassword(login, password);

            reg.getSession().setAttribute("password", password);
            reg.getSession().setAttribute("login", login);
            reg.getSession().setAttribute("role", roles);
            moveToView(reg, resp, roles);
        } else {
            moveToView(reg, resp, "");
        }
    }

    private void moveToView(HttpServletRequest req, HttpServletResponse resp, String role) throws ServletException, IOException {
        if (role.equals("admin")) {
            resp.sendRedirect("/admin");
        } else if (role.equals("user")) {
            resp.sendRedirect("/user");
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
