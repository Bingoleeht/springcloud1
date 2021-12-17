package cn.tedu.sp06.filter;

import cn.tedu.web.util.JsonResult;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class AccessFilter extends ZuulFilter {
    /*设置过滤器类型： pre routing post error;
    zuul 自动配置的回调方法*/
    @Override
    public String filterType() {
        //return "pre";
        log.info("Zuul自动配置过滤器类型");
        return FilterConstants.PRE_TYPE;
    }
    /* 过滤器的顺序号*/
    @Override
    public int filterOrder() {
        /*
        * 在默认的第5个过滤器中，向上下文对象放入了 serviceID。后面过滤器中才能访问这个数据*/
        log.info("Zuul自动配置过滤器的顺序号");
        return 6;
    }
    /* 针对当前请求进行判断
     判断是否要执行过滤代码
     返回true 执行过滤代码，返回false直接跳过*/
    @Override
    public boolean shouldFilter() {
        // 如果客户端调用商品，要判断权限，否则调用用户或订单，不判断权限；
        // 获得当前请求的上下文对象
        RequestContext ctx = RequestContext.getCurrentContext();
        //从上下文对象取出正在访问的 serviceId
        String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);//"serviceId"
        // 判断serviceID是否是 "Item-service"
        return "item-service".equalsIgnoreCase(serviceId);

    }
    /*过滤代码，检查是否有登陆*/
    @Override
    public Object run() throws ZuulException {
        // http://loacalhost:3001/item-service/yesefse？token=321k
        // 获得上下文对象，从上下文对象取出request对象
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        // 用request接收token参数
        String token = request.getParameter("token");
        // 如果token不存在(null,"" ,"    ")，阻止继续访问，直接返回响应
        if(StringUtils.isBlank(token)){
            ctx.setSendZuulResponse(false);
            String json = JsonResult.build().code(500).msg("没有登陆").toString();
            ctx.addZuulResponseHeader("Content-type","application/json;charset=UTF-8");
            ctx.setResponseBody(json);
        }
        // :JsonResult{code:500,msg:"xxx",data:null}

        return null;    // zull 当前版本没有使用这个返回值，返回任何数据都可以，不起任何作用；
    }
}
