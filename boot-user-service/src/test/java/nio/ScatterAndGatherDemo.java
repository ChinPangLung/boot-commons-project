package nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author longzhanpeng longzhanpeng@3vjia.com
 * @since 2020-07-27 14:28
 */
public class ScatterAndGatherDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile inFile = new RandomAccessFile("D:\\longzhanpeng\\IdeaProjects\\boot-commons-project\\boot-user-service\\src\\test\\java\\nio\\channel\\0727.png", "rw");
        RandomAccessFile outFile = new RandomAccessFile("D:\\longzhanpeng\\IdeaProjects\\boot-commons-project\\boot-user-service\\src\\test\\java\\nio\\channel\\copy003.png", "rw");

        FileChannel inFileChannel = inFile.getChannel();
        FileChannel outFileChannel = outFile.getChannel();

        // 分散的buffer大小之和要大于输入图片
        long spit = inFile.length() / 3;
        ByteBuffer buf1 = ByteBuffer.allocate((int) spit);
        ByteBuffer buf2 = ByteBuffer.allocate((int) spit);
        ByteBuffer buf3 = ByteBuffer.allocate((int) spit);

        ByteBuffer[] bufs = {buf1, buf2, buf3};
        inFileChannel.read(bufs);
        // 读完之后需要flip操作切换成写模式
        for (ByteBuffer buf : bufs) {
            buf.flip();
        }
        outFileChannel.write(bufs);
    }
}