import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();

        HttpResponse execute = httpClient.execute(new HttpGet("https://en.wikipedia.org/wiki/Unicode"));
        String text = EntityUtils.toString(execute.getEntity(), StandardCharsets.UTF_8);

        Path utf16_1 = Paths.get("Unicode.1.html");
        byte[] fileBytes = text.getBytes(StandardCharsets.UTF_16);
        Files.write(utf16_1, fileBytes);

        byte[] shorterFileBytes = new byte[fileBytes.length - 2];
        System.arraycopy(fileBytes, 2, shorterFileBytes, 0, shorterFileBytes.length);
        Path utf16_2 = Paths.get("Unicode.2.html");
        Files.write(utf16_2, shorterFileBytes);

        String finalText = new String(Files.readAllBytes(utf16_2), StandardCharsets.UTF_8);
        Files.write(Paths.get("Unicode.3.html"), finalText.getBytes(StandardCharsets.UTF_16));
    }
}
