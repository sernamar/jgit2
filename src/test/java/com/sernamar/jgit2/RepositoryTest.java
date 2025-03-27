package com.sernamar.jgit2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    private static final String PATH = "/tmp/repo";

    @BeforeAll
    static void beforeAll() {
        Global.gitLibgit2Init();
    }

    @AfterAll
    static void afterAll() {
        Global.gitLibgit2Shutdown();
    }

    @AfterEach
    void afterEach() throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(PATH))) {
            paths.sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }

    @Test
    void gitRepositoryInit() {
        // Have permission to create a repository in the /tmp directory.
        assertDoesNotThrow(() -> {
            try (GitRepository repo = Repository.gitRepositoryInit(PATH)) {
                assertNotNull(repo);
            }
        });
        // Don't have permission to create a repository in the /root directory.
        assertThrows(RuntimeException.class, () -> {
            try (GitRepository repo = Repository.gitRepositoryInit("/root/repo")) {
                assertNotNull(repo);
            }
        });

    }

    @Test
    void gitRepositoryOpen() {
        try (GitRepository _ = Repository.gitRepositoryInit(PATH);
             GitRepository repo = Repository.gitRepositoryOpen(PATH)) {
            assertNotNull(repo);
        }
    }

    @Test
    void gitRepositoryFree() {
        GitRepository repo = Repository.gitRepositoryInit(PATH);
        Repository.gitRepositoryFree(repo);
    }

    @Test
    void gitRepositoryIndex() {
        try (GitRepository repo = Repository.gitRepositoryInit(PATH);
             GitIndex index = Repository.gitRepositoryIndex(repo)) {
            assertNotNull(index);
        }
    }
}