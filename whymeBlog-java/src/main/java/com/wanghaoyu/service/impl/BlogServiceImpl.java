package com.wanghaoyu.service.impl;

import com.wanghaoyu.entity.Blog;
import com.wanghaoyu.mapper.BlogMapper;
import com.wanghaoyu.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
