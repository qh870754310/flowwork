package com.qh.modules.activiti.org.activiti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/19.
 */
@WebFilter("/service/*")
public class JsonpCallbackFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(JsonpCallbackFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        Map<String, String[]> params = httpServletRequest.getParameterMap();

        if (params.containsKey("callback")) {
            if (logger.isDebugEnabled()) {
                logger.debug("Wrapping response with JSONP callback '" + params.get("callback")[0] + "'");
            }

            OutputStream out = httpServletResponse.getOutputStream();
            GenericResponseWrapper wrapper = new GenericResponseWrapper(httpServletResponse);

            filterChain.doFilter(request, wrapper);
            //handles the content-size truncation
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(new String(params.get("callback")[0] + "(").getBytes());
            outputStream.write(wrapper.getData());
            outputStream.write(new String(");").getBytes());
            byte jsonpResponse[] = outputStream.toByteArray();

            wrapper.setContentType("text/javascript;charset=UTF-8");
            wrapper.setContentLength(jsonpResponse.length);
            out.write(jsonpResponse);
            out.close();
        } else {
            filterChain.doFilter(request, response);
        }
    }

    public void destroy() {

    }
}
