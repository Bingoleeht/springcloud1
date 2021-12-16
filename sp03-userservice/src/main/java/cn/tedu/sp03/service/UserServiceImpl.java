package cn.tedu.sp03.service;

import cn.tedu.entity.User;
import cn.tedu.service.UserService;
import cn.tedu.web.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.oracle.tools.packager.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    //注入yml中配置的demo数据
    @Value("${sp.user-service.users}")
    private String userJson;

    @Override
    public User getUser(Integer userId) {
        log.info("获取用户，userId=" + userId);
        //userJson-->List<User>
        // jackson api 利用继承语法来写转换的类型
        List<User> list = JsonUtil.from(userJson, new TypeReference<List<User>>() {});

        //便利集合查找用户id是userId的用户
        for (User user : list) {
            if(user.getId().equals(userId)){
                return user;
            }
        }

        // 如果没有此用户，直接返回一个写死的用户数据
        return new User(userId,"用户名 "+userId,"密码 "+userId);
    }

    @Override
    public void addScore(Integer id, Integer score) {
    log.info("增加用户积分，userID= "+id +",score = "+score);
    }
}
