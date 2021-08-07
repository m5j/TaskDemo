package com.example.taskdemo.mapper;

import com.example.taskdemo.entity.EaEhomeDk;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface EaEhomeDkMapper extends Mapper<EaEhomeDk> {

    List<EaEhomeDk> selectList(@Param("time") String time);
}
