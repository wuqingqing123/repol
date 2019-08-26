package com.wu.dao;

import com.wu.pojo.Member;
import org.apache.ibatis.annotations.Select;

/**
 * 会员的持久层接口类
 */
public interface MemberDao {

    //根据id查询会员信息
    @Select("select *  from member where id=#{id}")
    public Member findById(String id)throws Exception;
}
