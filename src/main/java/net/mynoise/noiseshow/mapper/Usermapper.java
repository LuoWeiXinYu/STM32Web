package net.mynoise.noiseshow.mapper;/* *
 * @Description:
 * @param $params$
 * @Return: $returns$
 * @开发人员：余新伟
 * @开发单位：湖南农业大学物联网工程专业
 * @Date: 2021/1/7 20:07
 * @开发版本：综合练习V0.1
 */

import net.mynoise.noiseshow.entity.User;

import java.util.Date;

public interface Usermapper {
    User userlogin(String name, String pwd);

    int adduser(String name, String pwd, String sexy, Date birthday);

}
