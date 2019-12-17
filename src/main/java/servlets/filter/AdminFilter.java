package servlets.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest reg = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = reg.getSession();

        if (nonNull(session.getAttribute("role")) && session.getAttribute("role").equals("admin")) {
            filterChain.doFilter(reg, resp);
        } else {
            session.removeAttribute("login");
            session.removeAttribute("password");
            session.removeAttribute("role");
            resp.sendRedirect("/");
        }
    }
}
