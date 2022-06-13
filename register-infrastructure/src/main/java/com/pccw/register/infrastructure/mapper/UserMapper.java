package com.pccw.register.infrastructure.mapper;


import com.pccw.register.infrastructure.basic.BaseMapper;
import com.pccw.register.infrastructure.po.UserExample;
import com.pccw.register.infrastructure.po.UserPO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserPO, UserExample> {
    /**
     * 批量插入 注意: 需要设置默认值
     *
     * @param records
     */
    int batchInsert(List<UserPO> records);

    int insert(UserPO var1);
}