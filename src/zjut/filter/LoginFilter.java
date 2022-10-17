package zjut.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class LoginFilter implements Filter {
    /*排除的地址*/
    private Map<String, Boolean> ignore;
    /*排除的后缀*/
    private Map<String, Boolean> ignoreExt;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        HttpSession session = request.getSession();
        String ss=request.getRequestURI().replaceFirst(request.getContextPath(),"");
//        System.out.println(ss);
         if (ss.indexOf("/login") == -1 && ss.indexOf("/Login") == -1 && session.getAttribute("user")==null){
             response.sendRedirect("Login.jsp");
            return;
        }
//        System.out.println("LoginFilter2");
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
