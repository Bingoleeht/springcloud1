package cn.tedu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Order {
    private String id;
    private User user; // 所属的用户
    private List<Item> items; // 商品列表
}
