package com.sernamar.jgit2;

import com.sernamar.jgit2.utils.GitException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.sernamar.jgit2.TestUtils.deleteRepoDirectory;
import static org.junit.jupiter.api.Assertions.*;
class CloneTest {

    private static final String REMOTE_REPO = "https://github.com/sernamar/jgit2";
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
    void gitClone() throws GitException {
        try (GitRepository repo = Clone.gitClone(REMOTE_REPO, PATH)) {
            assertNotNull(repo);
        }
    }
}