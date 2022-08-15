package com.jack.service;

import com.github.pagehelper.PageInfo;
import com.jack.base.BaseService;
import com.jack.entity.UserFollow;
import com.jack.vo.UserFollowVo;

public interface UserFollowService extends BaseService<UserFollow> {
    /**
     * 关注房源
     */
    void follow(Long userId, Long houseId);

    /**
     * 判断房源关注
     */
    Boolean isFollowed(Long userId, Long houseId);

    /**
     * 关注列表分页展示
     */
    PageInfo<UserFollowVo> findListPage(int pageNum, int pageSize, Long userId);

    /**
     * 取消关注
     */
    Boolean cancelFollow(Long id);
}
