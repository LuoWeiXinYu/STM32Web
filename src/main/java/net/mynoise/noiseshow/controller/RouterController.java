package net.mynoise.noiseshow.controller;
import net.mynoise.noiseshow.entity.AllSensor;
import net.mynoise.noiseshow.entity.User;
import net.mynoise.noiseshow.mapper.AllSensorMapper;
import net.mynoise.noiseshow.netty.NettyServerHandler;
import net.mynoise.noiseshow.service.impl.UserServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*** 
 @author: 余新伟
 @学号:201841882320
 */
@Controller
public class RouterController {
    @Autowired
    private AllSensorMapper allSensorMapper;
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("")
    private  String index() {
        return "login";
    }

    @GetMapping("/chart")
    private  String chart() {
        return "chart";
    }

    @GetMapping("/return")
    private  String ret() { return "index"; }

    @GetMapping("/sendData")
    @ResponseBody
    public String sendData(@RequestParam(value="message") String message)
    {
        System.out.println(message);
        String sendMessage = '#'+message+'$';
       if(NettyServerHandler.sendMessage(sendMessage)==1) {
           System.out.println("成功");
           return "success";
       } else {
           System.out.println("失败");
           return "fail";
       }
    }

    @GetMapping("/baseData")
    @ResponseBody
    public List<AllSensor> baseData(ModelMap modelMap)
    {
        return allSensorMapper.querrySensor();
    }

    /**
     * 跳转到用户注册页面
     * @return 注册页面
     */
    @RequestMapping(value = {"/registerpage"})
    public String registerpage(){
        return "register";
    }

    /**
     * 获取用户名与密码，用户登录
     * @return 登录成功页面
     */
    //@ResponseBody
    @RequestMapping(value = {"/userLogin"})
    private String userlogin(@Param("name") String name, @Param("pwd") String pwd){
        if(StringUtils.isEmpty(name)){
            return "用户名不能为空";
        }

        if(StringUtils.isEmpty(pwd)){
            return "密码不能为空";
        }

        User user = userService.userlogin(name, pwd);

        if(user != null){                                                  //登录成功

            return "index";
        }
        return "登录失败，用户名或密码错误";
    }
    /**
     * 注册新用户
     * @return 注册结果
     */
    //@ResponseBody
    @RequestMapping(value = {"/uregister"})
    public String addUser(@RequestParam("name") String name,@RequestParam("pwd") String pwd,@RequestParam("pwd2") String pwd2,@RequestParam("sexy") String sexy,@RequestParam("birthday")String birthday1){
        int res=0;
        if(org.springframework.util.StringUtils.isEmpty(name)){
            return "用户名不能为空";
        }

        if(org.springframework.util.StringUtils.isEmpty(pwd)){
            return "密码不能为空";
        }

        if(org.springframework.util.StringUtils.isEmpty(pwd2)){
            return "确认密码不能为空";
        }

        if(!pwd.equals(pwd2)){
            return "两次密码不相同，注册失败！！";
        }else {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date birthday = simpleDateFormat.parse(birthday1);
                res = userService.adduser(name,pwd,sexy,birthday);
                if(res == 0){
                    return "注册失败！";
                }else {
                    return "registersuccess";
                }
            }catch(ParseException px) {
                px.printStackTrace();
                return "注册失败！";
            }
        }
        //return birthday.toString();
    }

    @RequestMapping(value = "/deldatabyid")
    public String deluserid(@RequestParam("id") Long id){
        allSensorMapper.deleteData(id);
        return "index";
    }

    @RequestMapping(value = {"/update"})
    public String updateuser(@RequestParam("id") long id,@RequestParam("temp") String temp,
                             @RequestParam("hum") String hum,@RequestParam("dist") String dist,
                             @RequestParam("people")String people,@RequestParam("ip")String ip) {
        allSensorMapper.updataData(id,temp,hum,dist,people,ip);
        return "index";
    }
}
