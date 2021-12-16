package cn.tedu.service;

import cn.tedu.entity.Item;

import java.util.List;

public interface ItemService {
    // 获取订单的商品列表
    List<Item> getItems(String orderId);
    // 减少商品的库存
    void decreaseNumber(List<Item> items);
}
