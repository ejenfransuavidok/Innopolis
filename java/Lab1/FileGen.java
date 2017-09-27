package Lab1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileGen {

    public void generator(){
        Charset charset = Charset.forName("UTF-8");
        RandomString rs = new RandomString(1000);

        for(int i=0; i<10000; i++) {
            String s = rs.nextString();
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("test-" + i + ".txt"), charset)) {
                writer.write(s, 0, s.length());
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }

    }

}
