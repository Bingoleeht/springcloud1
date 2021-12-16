package cn.tedu.sp04.feign;

import cn.tedu.entity.Item;
import cn.tedu.web.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 通过注解配置：
 * 1、调用哪个服务
 * 2、调用服务的哪个路径
 * 3、向路径提交什么参数
 */
@FeignClient(name="ITEM-SERVICE")   // 通过名称发现服务器的地址
public interface ItemClient {
    @GetMapping("{orderId}")    //利用springmvc注解，去调用远程模块路径。
    JsonResult<List<Item>> getItems(@PathVariable String orderId);

    @PostMapping("/decreaseNumber")
    JsonResult<?> decreaseNumber(@RequestBody List<Item> items);
}
