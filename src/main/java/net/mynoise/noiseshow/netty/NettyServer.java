package net.mynoise.noiseshow.netty;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.net.InetSocketAddress;

/*** 
 @author: 余新伟
 @学号:201841882320
 */
public class NettyServer {
    public void start(InetSocketAddress socketAddress){
        /**new 一个主线程组*/
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        /**new 一个工作线程组*/
        EventLoopGroup workGroup = new NioEventLoopGroup(200);
        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerChannelInitializer())
                .localAddress(socketAddress)
                /**设置队列的大小*/
                .option(ChannelOption.SO_BACKLOG,1024)
                /**两小时内没有数据的通信时，TCP会自动发送一个活动探测数据报文*/
                .childOption(ChannelOption.SO_KEEPALIVE,true);
        /**绑定端口，开始接收进来的连接*/
        try{
            ChannelFuture future = bootstrap.bind(socketAddress).sync();
            System.out.println("服务器ip为："+socketAddress.getHostName());
            System.out.println("服务器端口号为："+socketAddress.getPort());
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            /**关闭主线程组*/
            bossGroup.shutdownGracefully();
            /**关闭工作线程组*/
            workGroup.shutdownGracefully();
        }
    }
}
