package com.pccw.register.infrastructure.basic;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMapper<T,E> {

    long countByExample(E var1);

    int deleteByExample(E var1);

    int deleteByPrimaryKey(Long var1);

    int insert(T var1);

    int insertSelective(T var1);

    List<T> selectByExample(E var1);

    T selectByPrimaryKey(Long var1);

    int updateByExampleSelective(@Param("record") T var1, @Param("example") E var2);

    int updateByExample(@Param("record") T var1, @Param("example") E var2);

    int updateByPrimaryKeySelective(T var1);

    int updateByPrimaryKey(T var1);
}
