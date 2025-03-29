package com.sernamar.jgit2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.sernamar.jgit2.Revwalk.gitRevwalkPushHead;
import static com.sernamar.jgit2.TestUtils.createRepoWithInitialCommit;
import static com.sernamar.jgit2.TestUtils.deleteRepoDirectory;
import static org.junit.jupiter.api.Assertions.*;

class RevwalkTest {

    private static final String PATH = "/tmp/repo";

    @BeforeAll
    static void beforeAll() {
        Global.gitLibgit2Init();
        createRepoWithInitialCommit(PATH);
    }

    @AfterAll
    static void afterAll() {
        deleteRepoDirectory(PATH);
        Global.gitLibgit2Shutdown();
    }

    @Test
    void gitRevwalkNew() {
        try (GitRepository repo = Repository.gitRepositoryOpen(PATH);
             GitRevwalk walk = Revwalk.gitRevwalkNew(repo)) {
            assertNotNull(walk);
        }
    }

    @Test
    void gitRevwalkPushHead() {
        try (GitRepository repo = Repository.gitRepositoryOpen(PATH);
             GitRevwalk walk = Revwalk.gitRevwalkNew(repo)) {
            assertDoesNotThrow(() -> Revwalk.gitRevwalkPushHead(walk));
        }
    }

    @Test
    void gitRevwalkSorting() {
        try (GitRepository repo = Repository.gitRepositoryOpen(PATH);
             GitRevwalk walk = Revwalk.gitRevwalkNew(repo)) {
            assertDoesNotThrow(() -> Revwalk.gitRevwalkSorting(walk));
        }
    }

    @Test
    void gitRevwalkNext() {
        try (GitRepository repo = Repository.gitRepositoryOpen(PATH);
             GitRevwalk walk = Revwalk.gitRevwalkNew(repo)) {
            Revwalk.gitRevwalkPushHead(walk);
            int commitCount = 0;
            while (Revwalk.gitRevwalkNext(walk) != null) {
                commitCount++;
            }
            assertTrue(commitCount > 0);
        }
    }
}