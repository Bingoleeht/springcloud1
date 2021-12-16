package cn.tedu.service;

import cn.tedu.entity.Order;

public interface OrderService {
    //获取订单
    Order getOrder(String orderId);

    //添加订单
    void addOrder(Order order);
}
