package mcserver.spring.common.config;

import mcserver.spring.common.interceptor.InternalRequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final InternalRequestInterceptor interceptor;

    public WebConfig(InternalRequestInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.interceptor).addPathPatterns(new String[]{"/**"});
    }
}
