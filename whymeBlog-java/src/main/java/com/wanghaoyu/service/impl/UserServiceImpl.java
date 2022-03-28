package com.wanghaoyu.service.impl;

import com.wanghaoyu.entity.User;
import com.wanghaoyu.mapper.UserMapper;
import com.wanghaoyu.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王昊宇
 * @since 2022-01-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
