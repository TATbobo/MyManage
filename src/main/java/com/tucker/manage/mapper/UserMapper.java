package com.tucker.manage.mapper;



import com.tucker.manage.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("UserMapper")
public interface UserMapper {

    @Select("select * from user where name=#{name}")
    public User getUserByName(String name);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into user(id,name,password,role) values(#{id},#{name},#{password},#{role})")
    public int insertUser(User user);
}
