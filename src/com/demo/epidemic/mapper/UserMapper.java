package com.demo.epidemic.mapper;

import com.demo.epidemic.beans.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author eddie
 * @Date 2020-02-25 15:59
 * @Version 1.0
 */
@Mapper
public interface UserMapper {

    /**
     * 通过账号查找信息
     *
     * @param account 用户账号
     * @return 通过账号查找信息
     */
    @Select(value="SELECT u.`user_id`,u.`account`,u.`password`,u.`user_name` " +
            "FROM users u " +
            "WHERE u.`account`=#{account} " +
            "AND u.`del_flag` IS NULL OR u.`del_flag`=0")
    UserInfo findByAccount(String account);

}
