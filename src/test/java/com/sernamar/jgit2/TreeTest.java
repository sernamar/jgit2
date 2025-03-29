package com.sernamar.jgit2;

import com.sernamar.jgit2.utils.GitException;
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
    void gitTreeLookup() throws GitException {
        try (GitRepository repo = Repository.gitRepositoryOpen(PATH);
             GitIndex index = Repository.gitRepositoryIndex(repo)) {
            GitOid treeId = Index.gitIndexWriteTree(index);
            try (GitTree tree = Tree.gitTreeLookup(repo, treeId)) {
                assertNotNull(tree);
            }
        }
    }
}