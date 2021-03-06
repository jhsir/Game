package com.jhcoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;


public class WebSocketClientInitializer extends ChannelInitializer<SocketChannel>{

    private SocketServerHandler handler;

    public WebSocketClientInitializer(SocketServerHandler handler) {
        this.handler = handler;
    }
    protected void initChannel(SocketChannel ch) throws Exception {
       ChannelPipeline pipeline = ch.pipeline();
        // 第12位表示整包的长度,34位表示包描述信息长度,后续为描述信息字节,以及逻辑数据字节
       pipeline.addLast(new LengthFieldBasedFrameDecoder(Short.MAX_VALUE, 0, 2, -2, 2, false));
       pipeline.addLast(new WebSocketServerHandler());
    }
}
