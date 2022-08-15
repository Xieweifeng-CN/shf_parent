package com.jack.dao;

import com.github.pagehelper.Page;
import com.jack.base.BaseDao;
import com.jack.entity.UserFollow;
import com.jack.vo.UserFollowVo;
import org.apache.ibatis.annotations.Param;

public interface UserFollowDao extends BaseDao<UserFollow> {
    Integer countByUserIdAndHouserId(@Param("userId") Long userId,@Param("houseId") Long houseId);

    Page<UserFollowVo> findListPage(@Param("userId")Long userId);
}
