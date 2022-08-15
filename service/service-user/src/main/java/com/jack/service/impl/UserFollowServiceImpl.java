package com.jack.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jack.base.BaseDao;
import com.jack.base.BaseServiceImpl;
import com.jack.dao.UserFollowDao;
import com.jack.entity.UserFollow;
import com.jack.service.DictService;
import com.jack.service.UserFollowService;
import com.jack.vo.UserFollowVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author :Jack
 * @CreatTime : 2022/8/13
 * @Description :
 **/
@Service(interfaceClass = UserFollowService.class)
@Transactional
public class UserFollowServiceImpl extends BaseServiceImpl<UserFollow> implements UserFollowService {
    @Autowired
    private UserFollowDao userFollowDao;

    @Reference
    private DictService dictService;

    @Override
    protected BaseDao<UserFollow> getEntityDao() {
        return userFollowDao;
    }

    @Override
    public void follow(Long userId, Long houseId) {
        UserFollow userFollow = new UserFollow();
        userFollow.setUserId(userId);
        userFollow.setHouseId(houseId);
        userFollowDao.insert(userFollow);
    }

    @Override
    public Boolean isFollowed(Long userId, Long houseId) {
        //调用UserFollowDao中判断是否已关注的方法
        Integer count = userFollowDao.countByUserIdAndHouserId(userId, houseId);
        if(count.intValue() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public PageInfo<UserFollowVo> findListPage(int pageNum, int pageSize, Long userId) {
        PageHelper.startPage(pageNum, pageSize);
        Page<UserFollowVo> page = userFollowDao.findListPage(userId);
        List<UserFollowVo> lists = page.getResult();
        for(UserFollowVo list : lists){
            //户型
            String houseTypeName  = dictService.getNameById(list.getHouseTypeId());
            //楼层
            String floorName = dictService.getNameById(list.getFloorId());
            //朝向
            String directionName = dictService.getNameById(list.getDirectionId());
            list.setHouseTypeName(houseTypeName);
            list.setFloorName(floorName);
            list.setDirectionName(directionName);
        }
        return new PageInfo<UserFollowVo>(page,10);
    }

    @Override
    public Boolean cancelFollow(Long id) {
        userFollowDao.delete(id);
        return true;
    }
}
