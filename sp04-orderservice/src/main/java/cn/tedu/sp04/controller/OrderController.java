package cn.tedu.sp04.controller;

import cn.tedu.entity.Item;
import cn.tedu.entity.Order;
import cn.tedu.entity.User;
import cn.tedu.service.OrderService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public JsonResult<Order> getOrder(@PathVariable String orderId){
        Order order = orderService.getOrder(orderId);
        return JsonResult.build().code(200).data(order);
    }
    @GetMapping("/add")
    public JsonResult<?> addOrder(){
        Order order = new Order();
        order.setId("39dlskdfjlsd");
        order.setItems(Arrays.asList(new Item[]
                {
                        new Item(1,"商品1",5),
                        new Item(2,"商品2",2),
                        new Item(3,"商品3",5),
                        new Item(4,"商品4",6),
                        new Item(5,"商品5",7),
                        new Item(6,"商品6",5)
                }));
        order.setUser(new User(8,null,null));
        orderService.addOrder(order);
        return JsonResult.build().code(200).msg("订单添加成功");
    }

    @GetMapping("/favicon.ico")
    public void ico(){

    }
}
