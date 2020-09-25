package com.inmost.bulletin_board.FileSystem;

import com.inmost.bulletin_board.FileSystem.Exceptions.FileBrokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class FileSystemService {

    @Value("${path.imageFolder}")
    private String imageFolderPath;

    public Path saveFile(byte[] incomeFile) throws FileBrokenException{
        File dir = new File(imageFolderPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(imageFolderPath + "/" + UUID.randomUUID() + ".jpg");
        try (OutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(incomeFile);
            return file.toPath();
        } catch (IOException e) {
            throw new FileBrokenException();
        }
    }
}
