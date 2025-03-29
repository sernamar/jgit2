package com.sernamar.jgit2;

import com.sernamar.jgit2.utils.GitException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.sernamar.jgit2.Repository.gitRepositoryIndex;
import static com.sernamar.jgit2.Repository.gitRepositoryOpen;
import static com.sernamar.jgit2.TestUtils.createRepoWithInitialCommit;
import static com.sernamar.jgit2.TestUtils.deleteRepoDirectory;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class IndexTest {

    private static final String PATH = "/tmp/repo";

    @BeforeAll
    static void beforeAll() {
        try {
            Global.gitLibgit2Init();
            createRepoWithInitialCommit(PATH);
        } catch (GitException e) {
            throw new RuntimeException("Failed to set up test repository", e);
        }
    }

    @AfterAll
    static void afterAll() {
        deleteRepoDirectory(PATH);
        Global.gitLibgit2Shutdown();
    }

    @Test
    void gitIndexAddByPath() throws GitException {
        try {
            Files.write(Paths.get(PATH, "hello.txt"), "Hello, World!".getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (GitRepository repo = gitRepositoryOpen(PATH);
             GitIndex index = gitRepositoryIndex(repo)) {
            assertDoesNotThrow(() -> Index.gitIndexAddByPath(index, "hello.txt"));
        }
    }

    @Test
    void gitIndexWrite() throws GitException {
        try {
            Files.write(Paths.get(PATH, "hello.txt"), "Hello, World!".getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (GitRepository repo = gitRepositoryOpen(PATH);
             GitIndex index = gitRepositoryIndex(repo)) {
            Index.gitIndexAddByPath(index, "hello.txt");
            assertDoesNotThrow(() -> Index.gitIndexWrite(index));
        }
    }

    @Test
    void gitIndexWriteTree() throws GitException {
        try {
            Files.write(Paths.get(PATH, "hello.txt"), "Hello, World!".getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (GitRepository repo = gitRepositoryOpen(PATH);
             GitIndex index = gitRepositoryIndex(repo)) {
            Index.gitIndexAddByPath(index, "hello.txt");
            Index.gitIndexWrite(index);
            assertDoesNotThrow(() -> Index.gitIndexWriteTree(index));
        }
    }
}