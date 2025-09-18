// 代码生成时间: 2025-09-18 12:43:02
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipperService {

    public void unzip(String zipFilePath, String destDirectory) {
        try {
            Path destination = Paths.get(destDirectory);
            if (!Files.exists(destination)) {
                Files.createDirectories(destination);
            }

            try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
                ZipEntry entry = zipIn.getNextEntry();
                while (entry != null) {
                    String filePath = destination.toString() + File.separator + entry.getName();

                    if (!entry.isDirectory()) {
                        // If the entry is a file, extract it
                        extractFile(zipIn, filePath);
                    } else {
                        // If the entry is a directory, make the directory
                        Files.createDirectories(Paths.get(filePath));
                    }

                    zipIn.closeEntry();
                    entry = zipIn.getNextEntry();
                }
            }
        } catch (IOException e) {
            System.err.println("Error occurred while unzipping: " + e.getMessage());
        }
    }

    private void extractFile(InputStream is, String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());
        try (OutputStream os = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) != -1) {
                os.write(buffer, 0, length);
            }
        }
    }
}
