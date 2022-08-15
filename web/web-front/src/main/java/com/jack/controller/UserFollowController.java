package com.jack.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.jack.base.BaseController;
import com.jack.entity.UserInfo;
import com.jack.result.Result;
import com.jack.service.UserFollowService;
import com.jack.vo.UserFollowVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author :Jack
 * @CreatTime : 2022/8/13
 * @Description :
 **/
@RestController
@RequestMapping("/userFollow")
public class UserFollowController extends BaseController {

    @Reference
    private UserFollowService userFollowService;

    /**
     * 关注房源
     */
    @GetMapping("/auth/follow/{houseId}")
    public Result follow(@PathVariable("houseId") Long houseId, HttpServletRequest request){
        // 获取用户
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("USER");
        Long userId = userInfo.getId();
        // 保存关注
        userFollowService.follow(userId,houseId);
        return Result.ok();
    }

    /**
     * 查询我的关注
     */
    @GetMapping(value = "/auth/list/{pageNum}/{pageSize}")
    public Result findListPage(@PathVariable Integer pageNum,@PathVariable Integer pageSize,
                               HttpServletRequest request) {
        //获取Session域中中的UserInfo对象
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("USER");
        Long userId = userInfo.getId();
        //调用UserFollowService中查询我的关注的方法
        PageInfo<UserFollowVo> pageInfo = userFollowService.findListPage(pageNum, pageSize, userId);
        return Result.ok(pageInfo);
    }

    /**
     * 取消关注
     */
    @GetMapping("auth/cancelFollow/{id}")
    public Result cancelFollow(@PathVariable("id") Long id){
        userFollowService.cancelFollow(id);
        return Result.ok();
    }

}
