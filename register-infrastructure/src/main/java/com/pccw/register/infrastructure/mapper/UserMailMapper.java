package com.pccw.register.infrastructure.mapper;


import com.pccw.register.infrastructure.basic.BaseMapper;
import com.pccw.register.infrastructure.po.UserMailExample;
import com.pccw.register.infrastructure.po.UserMailPO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMailMapper extends BaseMapper<UserMailPO, UserMailExample> {
    /**
     * 批量插入 注意: 需要设置默认值
     *
     * @param records
     */
    int batchInsert(List<UserMailPO> records);
}