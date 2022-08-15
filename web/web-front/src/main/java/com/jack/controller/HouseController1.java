package com.jack.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.jack.base.BaseController;
import com.jack.entity.*;
import com.jack.result.Result;
import com.jack.service.*;
import com.jack.vo.HouseQueryVo;
import com.jack.vo.HouseVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author :Jack
 * @CreatTime : 2022/8/12
 * @Description :
 **/
@RestController
@RequestMapping("/house")
public class HouseController1 extends BaseController {

    @Reference
    private HouseService houseService;

    @Reference
    private CommunityService communityService;

    @Reference
    private HouseBrokerService houseBrokerService;

    @Reference
    private HouseImageService houseImageService;

    @Reference
    private UserFollowService userFollowService;

    /**
     * 房源详情
     */
    @RequestMapping("/info/{houseId}")
    public Result info(@PathVariable Long houseId, HttpServletRequest request){
        //调用HouseService中根据id查询房源
        House house = houseService.getById(houseId);
        //根据房源中小区的id获取所在的小区
        Community community = communityService.getById(house.getCommunityId());
        //获取该房源的经纪人
        List<HouseBroker> listByHouseId = houseBrokerService.findListByHouseId(houseId);
        //获取房源图片
        List<HouseImage> houseImage = houseImageService.findList(houseId, 1);
        //判断关注
        //先判断是否登录
        Boolean isFollow  = false;
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("USER");
        if(null != userInfo) {
            Long userId = userInfo.getId();
            isFollow = userFollowService.isFollowed(userId, houseId);
        }
        //用map封装
        Map<String, Object> map = new HashMap<>();
        map.put("house",house);
        map.put("community",community);
        map.put("houseBrokerList",listByHouseId);
        map.put("houseImage1List",houseImage);

        //是否关注房源
        map.put("isFollowd ",isFollow );
        return Result.ok(map);
    }

    /**
     * 房源列表
     */
    @RequestMapping("/list/{pageNum}/{pageSize}")
    public Result findListPage(@RequestBody HouseQueryVo houseQueryVo,
                               @PathVariable Integer pageNum,
                               @PathVariable Integer pageSize){
        PageInfo<HouseVo> pageInfo = houseService.findListPage(pageNum, pageSize, houseQueryVo);
        return Result.ok(pageInfo);
    }
}
