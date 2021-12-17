package cn.tedu.sp06.fb;

import cn.tedu.web.util.JsonResult;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 当调用后台商品服务失败，执行网关中的这个降级类
 * 向客户端返回降级结果；
 */
@Component
public class OrderFB implements FallbackProvider {
    /*
    * 设置当前降级类，针对哪个后台服务降级
    * -item-service : 只针对商品降级
    *   * 对所有服务应用当前降级类
    *   null：对所有服务都应用当前降级类；*/

    @Override
    public String getRoute() {
        return "order-service";
    }
    // 发回给客户端的降级响应数据
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
            }

            @Override
            public void close() {
                //用来关闭下边的输入流
                // ByteArrayInputStream 不占用底层系统资源，不需要关闭
            }

            @Override
            public InputStream getBody() throws IOException {
                // JsonResult {code:500,msg:调用后台服务失败，data:null}
                String json = JsonResult.build().code(500).msg("服务调用失败").toString();
                return new ByteArrayInputStream(json.getBytes("UTF-8"));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders h = new HttpHeaders();
                h.add("Content-Type","application/json;charset=UTF-8");
                return h;
            }
        };
    }
}
