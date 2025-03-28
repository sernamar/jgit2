package com.sernamar.jgit2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.sernamar.jgit2.TestUtils.createRepoWithInitialCommit;
import static com.sernamar.jgit2.TestUtils.deleteRepoDirectory;
import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

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
    void gitTreeLookup() {
        try (GitRepository repo = Repository.gitRepositoryOpen(PATH);
             GitIndex index = Repository.gitRepositoryIndex(repo)) {
            GitOid treeId = Index.gitIndexWriteTree(index);
            try (GitTree tree = Tree.gitTreeLookup(repo, treeId)) {
                assertNotNull(tree);
            }
        }
    }
}