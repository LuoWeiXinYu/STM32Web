package net.mynoise.noiseshow;

import net.mynoise.noiseshow.netty.NettyServer;
import net.mynoise.noiseshow.netty.NettyServerHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

@SpringBootApplication
@MapperScan("net.mynoise.noiseshow.mapper")
public class NoiseshowApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoiseshowApplication.class, args);
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(new InetSocketAddress("192.168.137.1",5000));
    }

}
