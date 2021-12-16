package cn.tedu.sp04.service;

import cn.tedu.entity.Order;
import cn.tedu.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Override
    public Order getOrder(String orderId) {
        log.info("获取订单,orderId="+ orderId);

        // TODO: 远程调用上屏，获取商品列表
        // TODO: 远程调用用户，获取用户列表

        Order order = new Order();
        order.setId(orderId);
//        order.setItems(商品列表);
//        order.setUser(用户);
        return order;
    }

    @Override
    public void addOrder(Order order) {
        log.info("添加订单："+order);

        // TODO: 远程调用商品，减少库存
        // TODO: 远程调用用户，增加积分
    }
}
