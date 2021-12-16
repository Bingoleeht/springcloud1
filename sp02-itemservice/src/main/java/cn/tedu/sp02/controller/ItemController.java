package cn.tedu.sp02.controller;

import cn.tedu.entity.Item;
import cn.tedu.service.ItemService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ItemController {
    @Autowired
    private ItemService itemService;
    // 获取订单的商品列表
    @GetMapping("/{orderId}")
    public JsonResult<List<Item>> getItems(@PathVariable String orderId){
        List<Item> items = itemService.getItems(orderId);
        return JsonResult.build().code(200).data(items);
    }

    // 减少商品库存
    //@Requestbody 接收Post 请求的协议体数据
    @PostMapping("/decreaseNumber")
    public JsonResult<?> decreaseNumber(@RequestBody List<Item> items){
        itemService.decreaseNumber(items);
        return JsonResult.build().code(200).msg("库存减少成功");
    }

    @GetMapping("/favicon.ico")
    public void ico(){

    }

}
