package mcserver.spring.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mcserver.spring.common.annotation.RequireInternalHeader;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class InternalRequestInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod method) {
            RequireInternalHeader annotation = (RequireInternalHeader)method.getMethodAnnotation(RequireInternalHeader.class);
            if (annotation == null) {
                annotation = (RequireInternalHeader)method.getBeanType().getAnnotation(RequireInternalHeader.class);
            }

            if (annotation != null) {
                String header = request.getHeader("X-Internal-Request");
                if (header == null || header.isBlank() || !header.equals("true")) {
                    response.setStatus(402);
                    response.getWriter().write("Missing X-Internal-Request header");
                    return false;
                }
            }
        }

        return true;
    }
}
