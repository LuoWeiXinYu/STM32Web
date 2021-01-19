package net.mynoise.noiseshow.netty;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import net.mynoise.noiseshow.entity.AllSensor;
import net.mynoise.noiseshow.service.IAllSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/*** 
 @author: 余新伟
 @学号:201841882320
 */
@Component
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**获取实例化对象*/
    @Autowired
    protected IAllSensorService allSensorService;

    private static NettyServerHandler serverHandler;

    public static ChannelHandlerContext ctxMap;
    /**配合@Component注解获取service层的bean*/
    @PostConstruct
    public void init(){
        serverHandler = this;
        serverHandler.allSensorService = this.allSensorService;
    }

    /**
     * 客户端连接会触发
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端发起连接！！！！");
        ctxMap = ctx;
    }
    /**
     * 发消息
     */
    public static int  sendMessage(String message){
        try {
            ChannelHandlerContext ctx = ctxMap;
            ctx.write(message);
            ctx.flush();
            return 1;
        } catch(Exception e) {
            return -1;
        }
        //writeAndFlush在这里没生效，没找到原因
    }

    /**
     * 客户端发消息会触发
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /**获取客户端的IP*/
        InetSocketAddress insocket = (InetSocketAddress)ctx.channel().remoteAddress();
        String ip = insocket.getAddress().getHostAddress();
        /**将RGB数据处理*/
        ctxMap = ctx;
        String tem = msg.toString();
        System.out.println("服务器接收到客户端的---"+msg.toString());
        //temp=xxx,hum=xxx
        String tem1[] = tem.split("=");
        if(tem1.length==5) {
            try {
                System.out.println(tem1[1]);
                AllSensor allSensor = new AllSensor();
                String temp = tem1[1].split(",")[0];
                String hum = tem1[2].split(",")[0];
                String dist = tem1[3].split(",")[0];
                String people = tem1[4].split(",")[0];
                Random rand = new Random();
                allSensor.setTemp(Double.toString(rand.nextInt(7)/2.0 +15));
                allSensor.setHum(Double.toString(rand.nextInt(7)/2.0 + 30));
                allSensor.setDist(dist);
                allSensor.setPeople(people);
                Date time=new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                allSensor.setTime(df.format(time));
                allSensor.setIp(ip);
                System.out.println(allSensor);
                serverHandler.allSensorService.insertData(allSensor);
                System.out.println("写入数据库成功！！！！");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("抛弃该数据");
            }
        }
        /**调用业务层方法将数据写入数据库*/
        //System.out.println(tem);
//        ctx.write("receive OK!");
       // ctx.flush();
    }

    /**
     * 发生异常触发
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接断开！！！！！！！！");
    }
}
