import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;

public abstract class WriteFile {
    private final static Set<StandardOpenOption> options = new HashSet<>();
    public static void writeFileChannel(ByteBuffer byteBuffer, String filePath) {
        options.add(StandardOpenOption.WRITE);
        options.add(StandardOpenOption.CREATE);
        options.add(StandardOpenOption.TRUNCATE_EXISTING);
        Path path = Paths.get(filePath);

        try {
            FileChannel fileChannel = FileChannel.open(path, options);
            fileChannel.write(byteBuffer);
            fileChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
