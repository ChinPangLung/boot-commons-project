package nio.network;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author longzhanpeng longzhanpeng@3vjia.com
 * @since 2020-07-27 14:44
 */
public class NIOTcpSever {

    @Test
    public void server() throws IOException {
        // 1. 创建Channel
        ServerSocketChannel open = ServerSocketChannel.open();
        // 2. 设置为非阻塞
        open.configureBlocking(false);
        // 3. 绑定端口
        open.bind(new InetSocketAddress(2571));
        // 4. 获得选择器
        Selector selector = Selector.open();
        // 5. 将通道注册在选择器上，并绑定监听的事件
        open.register(selector, SelectionKey.OP_ACCEPT);
        // 6. 轮训获取selector上已经就绪的事件
        while (selector.select() > 0) {
            System.out.println("=====");
            // 7. 获取当前selector中所有注册的键
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey sk = iterator.next();
                // 8. 判断具体是注册的哪种键
                if (sk.isAcceptable()) {
                    // 8.1. 接受准备就绪，获取客户端的连接
                    // 注意需要返回一个和原来ssChannle不一样的Channel
                    SocketChannel acceptChannel = open.accept();
                    // 将客户端连接设置为非阻塞模式
                    acceptChannel.configureBlocking(false);
                    // 将accept后的channel再注册到selector上，并且键为读就绪状态
                    acceptChannel.register(selector, SelectionKey.OP_READ);
                } else if (sk.isReadable()) {
                    // 8.2. 读就绪状态
                    SocketChannel channel = (SocketChannel) sk.channel();
                    // 读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = channel.read(buf)) > 0) {
                        System.out.println(len);
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                }
                // 9. 取消键
                iterator.remove();
            }
        }
    }
}