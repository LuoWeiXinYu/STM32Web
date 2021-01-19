package net.mynoise.noiseshow.service.impl;/* *
 * @Description:
 * @param $params$
 * @Return: $returns$
 * @开发人员：余新伟
 * @开发单位：湖南农业大学物联网工程专业
 * @Date: 2021/1/7 20:09
 * @开发版本：综合练习V0.1
 */

import net.mynoise.noiseshow.entity.User;
import net.mynoise.noiseshow.service.IUserServer;
import net.mynoise.noiseshow.mapper.Usermapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements IUserServer{
    @Autowired
    private Usermapper usermapper;

    @Override
    public User userlogin(String name,String pwd){
        return usermapper.userlogin(name, pwd);
    }

    //注册新用户
    @Override
    public int adduser(String name, String pwd, String sexy, Date birthday){

        /**
         * 注意查看mapper中的注释
         */
        return usermapper.adduser(name,pwd,sexy,birthday);     //对应sql语句中的第二种注册方式
    }

}
