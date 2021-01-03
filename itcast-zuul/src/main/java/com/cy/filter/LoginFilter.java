package com.cy.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
@Component
public class LoginFilter extends ZuulFilter {

    /**
     * 过滤器类型：pre route post error
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 执行顺序，返回值越小，优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 10;
    }

    /**
     * 是否执行该过滤器，主要执行run方法
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 编写过滤器的业务逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //初始化context上下文对象
        RequestContext context = RequestContext.getCurrentContext();
        //获取request对象
        HttpServletRequest request = context.getRequest();
        //读取参数
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)){
            //拦截，不转发请求
            context.setSendZuulResponse(false);
            //设置状态码
            context.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            //设置提示
            context.setResponseBody("request error");
        }
        //返回值null,就表示过滤器什么都不做
        return null;
    }
}
