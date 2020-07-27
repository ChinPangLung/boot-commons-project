package nio.channel;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author longzhanpeng longzhanpeng@3vjia.com
 * @since 2020-07-27 14:26
 */
public class ChannelTransferDemo {
    public static void main(String[] args) throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("D:\\longzhanpeng\\IdeaProjects\\boot-commons-project\\boot-user-service\\src\\test\\java\\nio\\channel\\0727.png"),
                StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("D:\\longzhanpeng\\IdeaProjects\\boot-commons-project\\boot-user-service\\src\\test\\java\\nio\\channel\\dst-2.jpg"),
                StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        // transferTo和transferFrom等价，不同形式而已
        // inChannel.transferTo(0, inChannel.size(), outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());
        inChannel.close();
        outChannel.close();
    }
}