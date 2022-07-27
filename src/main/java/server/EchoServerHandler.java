package server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String message = (String)msg;
        Channel channel = ctx.channel();

        channel.writeAndFlush("Response: '" + message + "' received\n");

        if ("quit".equals(message)) {
            ctx.close();
        }
    }
}