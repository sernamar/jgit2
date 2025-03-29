package com.sernamar.jgit2;

import com.sernamar.jgit2.utils.GitException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.sernamar.jgit2.TestUtils.deleteRepoDirectory;
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
    void afterEach() {
        deleteRepoDirectory(PATH);
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
        assertThrows(GitException.class, () -> {
            try (GitRepository repo = Repository.gitRepositoryInit("/root/repo")) {
                assertNotNull(repo);
            }
        });

    }

    @Test
    void gitRepositoryOpen() throws GitException {
        try (GitRepository _ = Repository.gitRepositoryInit(PATH);
             GitRepository repo = Repository.gitRepositoryOpen(PATH)) {
            assertNotNull(repo);
        }
    }

    @Test
    void gitRepositoryIndex() throws GitException {
        try (GitRepository repo = Repository.gitRepositoryInit(PATH);
             GitIndex index = Repository.gitRepositoryIndex(repo)) {
            assertNotNull(index);
        }
    }
}