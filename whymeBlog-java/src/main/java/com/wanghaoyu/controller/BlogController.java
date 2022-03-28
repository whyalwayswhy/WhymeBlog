package com.wanghaoyu.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanghaoyu.common.lang.Result;
import com.wanghaoyu.common.lang.dto.LoginDto;
import com.wanghaoyu.entity.Blog;
import com.wanghaoyu.service.BlogService;
import com.wanghaoyu.util.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王昊宇
 * @since 2022-01-25
 */
@RestController

public class BlogController {

    @Autowired
    BlogService blogService;
    //三个接口 详情 index 编辑
    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage,5);
        //QueryWrapper是mybatis里面的 查询操作
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));

        return Result.succ(pageData);
    }
    //详情
    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable Long id){
        Blog blog = blogService.getById(id);
        //有可能查出来是空的 要判断
        Assert.notNull(blog,"该博客已被删除");

        return Result.succ(blog);
    }
    //编辑
    //需要权限才能编辑
    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog){
        Blog temp = null;
        if (blog.getId() != null){
            //是编辑状态
            //查数据库 更新状况
            temp = blogService.getById(blog.getId());
            //只能编辑自己的文章
            Assert.isTrue(temp.getUserId().equals(ShiroUtil.getProfile().getId()),"没有权限操作");
        }else{
            //是添加状态
            //设置一个博客的基本信息
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }
        //复制 源 -> 目标
        BeanUtil.copyProperties(blog,temp,"id","userId","created","status");
        blogService.saveOrUpdate(temp);

        return Result.succ(null);
    }

    @RequiresAuthentication
    @PostMapping("/blog/delete/{id}")
    public Result delete(@PathVariable Long id){
        boolean flag =  blogService.removeById(id);
        if (flag){
            return Result.succ("博客删除成功");
        }else{
            return Result.fail("要删除的博客不存在");
        }
    }
}
