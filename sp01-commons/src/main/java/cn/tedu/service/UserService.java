package cn.tedu.service;

import cn.tedu.entity.User;

public interface UserService {
    //获取用户
    User getUser(Integer userId);
    // 增加积分
    void addScore(Integer id,Integer score);
}
