package cn.tedu.sp04.feign;

import cn.tedu.entity.User;
import cn.tedu.web.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * userClient.getUser(8);
 * 1.调用后台模块服务器：http;//User-Service 的服务器地址
 * 2、调用指定路径：http;//User-Service 的服务器地址/{userId}
 * 3、向路径提交参数：http;//User-Service 的服务器地址/8
 *
 * userClient.addScore(8,1000)
 * 1、调用后台模块服务器：http;//User-Service 的服务器地址
 * 2、调用指定路径：http;//User-Service 的服务器地址/{userId}/score
 * 3、向路径提交参数：http;//User-Service 的服务器地址/8/score？score=1000
 */
@FeignClient(name = "USER-SERVICE")
public interface UserClient {
    @GetMapping("/{userId}")
    JsonResult<User> getUser(@PathVariable Integer userId);

    @GetMapping("/{userId}/score") // ?score=1000
    JsonResult<?> addScore(@PathVariable Integer userId, @RequestParam("score") Integer score);
}
