package security.jwt.filter;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter3 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("filter3s");
        chain.doFilter(request,response);
    }
}
