package com.nilsswensson.petplayground.load.utils;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class FileUtils {
    public static String readAndPrepare(final String path) throws IOException {
        final String pathWithPrefix = "classpath:" + path;

        final File file = ResourceUtils.getFile(pathWithPrefix);
        String raw = new String(Files.readAllBytes(file.toPath()));
        return replacePlaceholders(raw);
    }

    private static String replacePlaceholders(String raw) {
        OffsetDateTime dt = OffsetDateTime.now(Clock.systemUTC());

        raw = raw.replace("\n", "");

        while (raw.contains("{{$guid}}")) {
            raw = raw.replace("{{$guid}}", UUID.randomUUID().toString());
        }

        while (raw.contains("{{randomInt}}")) {
            raw = raw.replace("{{randomInt}}", randomDigits10());
        }

        while (raw.contains("{{$isoTimestamp}}")) {
            raw = raw.replace("{{$isoTimestamp}}", dt.toString());
        }

        return raw;
    }

    private static String randomDigits10() {
        final StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            builder.append(ThreadLocalRandom.current().nextInt(10));
        }

        return builder.toString();
    }

    public static String randomDigits(final int len) {
        final StringBuilder builder = new StringBuilder();

        for (int i = 0; i < len; i++) {
            builder.append(ThreadLocalRandom.current().nextInt(10));
        }

        return builder.toString();
    }
}
