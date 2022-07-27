package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.net.InetSocketAddress;

public class EchoServer {
    private static final int SERVER_PORT = 11011;

    private final ChannelGroup allChannels = new DefaultChannelGroup("server", GlobalEventExecutor.INSTANCE);
    private EventLoopGroup bossEventLoopGroup;
    private EventLoopGroup workerEventLoopGroup;

    public void startServer() {
        // NIO 기반의 EventLoop 생성
        bossEventLoopGroup = new NioEventLoopGroup(1); // ServerSocket을 Listen
        workerEventLoopGroup = new NioEventLoopGroup(1); // bossEventLoopGroup에서 만들어진 Channel에서 넘어온 데이터를 처리

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(bossEventLoopGroup, workerEventLoopGroup); // EventLoopGroup 할당

        bootstrap.channel(NioServerSocketChannel.class); // Channel 생성시 사용할 클래스 (NIO 소켓을 이용한 채널)

        // accept되어 생성되는 TCP Channel 설정
        bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

        // Client Request를 처리할 Handler 등록
        bootstrap.childHandler(new EchoServerInitializer());

        try {
            // Channel 생성 후 기다림
            ChannelFuture bindFuture = bootstrap.bind(new InetSocketAddress(SERVER_PORT)).sync();
            Channel channel = bindFuture.channel();
            allChannels.add(channel);

            // Channel이 닫힐 때 까지 대기
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }
    }

    private void close() {
        allChannels.close().awaitUninterruptibly();
        workerEventLoopGroup.shutdownGracefully().awaitUninterruptibly();
        bossEventLoopGroup.shutdownGracefully().awaitUninterruptibly();
    }

    public static void main(String[] args) throws Exception {
        new EchoServer().startServer();
    }
}