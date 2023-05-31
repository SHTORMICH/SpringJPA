package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.TemplateEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileTemplateExecutor {
    private static final Logger logger = LogManager.getLogger(FileTemplateExecutor.class);
    private Path readFilePath;
    private Path writeFilePath;
    private TemplateEngine templateEngine;

    public FileTemplateExecutor(Path readFilePath, Path writeFilePath, TemplateEngine templateEngine) {
        if (Files.notExists(readFilePath)) {
            throw new IllegalStateException("Read file doesn't exist. Paths: " + readFilePath);
        }
        this.readFilePath = readFilePath;
        if (Files.notExists(writeFilePath)) {
            throw new IllegalStateException("Write file doesn't exist. Paths: " + writeFilePath);
        }
        this.writeFilePath = writeFilePath;
        this.templateEngine = templateEngine;
    }

    public void execute() {
        StringBuilder builder = new StringBuilder();
        validateReadFile(builder);
        String content = templateEngine.generateTemplate(builder.toString());
        validateWriteFile(content);
    }

    private void validateWriteFile(String content) {
        try (FileOutputStream outputStream = new FileOutputStream(writeFilePath.toFile())) {
            outputStream.write(content.getBytes());
        } catch (IOException e) {
            logger.error("Cannot write to file: " + writeFilePath + ". Cause: " + e);
        }
    }

    private void validateReadFile(StringBuilder builder) {
        try (FileInputStream fileInputStream = new FileInputStream(readFilePath.toFile())) {
            int c;
            while((c = fileInputStream.read()) != -1) {
                builder.append((char) c);
            }
        } catch (IOException e) {
            logger.error("Cannot read from file: " + readFilePath + ". Cause: " + e);
        }
    }
}
