package com.sernamar.jgit2;

import com.sernamar.jgit2.utils.GitException;
import org.junit.jupiter.api.*;

import static com.sernamar.jgit2.TestUtils.createRepoWithInitialCommit;
import static com.sernamar.jgit2.TestUtils.deleteRepoDirectory;
import static org.junit.jupiter.api.Assertions.*;

class CommitTest {

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
    void gitCommitLookup() throws GitException {
        try (GitRepository repo = Repository.gitRepositoryOpen(PATH)) {
            GitOid referenceId = Refs.gitReferenceNameToId(repo, "HEAD");
            assert referenceId != null;
            try (GitCommit parentCommit = Commit.gitCommitLookup(repo, referenceId)) {
                assertNotNull(parentCommit);
            }
        }
    }

    @Test
    void gitCommitMessage() throws GitException {
        try (GitRepository repo = Repository.gitRepositoryOpen(PATH)) {
            GitOid referenceId = Refs.gitReferenceNameToId(repo, "HEAD");
            assert referenceId != null;
            try (GitCommit parentCommit = Commit.gitCommitLookup(repo, referenceId)) {
                String message = Commit.gitCommitMessage(parentCommit);
                assertEquals(TestUtils.MESSAGE, message);
            }
        }
    }

    @Test
    void gitCommitCreateV() throws GitException {
        // NOTE: In `BeforeAll`, we already created an initial commit using `gitCommitCreateV` with `null` parents, so
        // no need to test the
        // `gitCommitCreateV(repo, updateRef, author, committer, messageEncoding, message, tree)`
        // version of the method again.
        // Instead, we will test the
        // `gitCommitCreateV(repo, updateRef, author, committer, messageEncoding, message, tree,... parents)`
        // version of the method.
        try (GitRepository repo = Repository.gitRepositoryOpen(PATH);
             GitIndex index = Repository.gitRepositoryIndex(repo)) {
            GitOid treeId = Index.gitIndexWriteTree(index);
            try (GitTree tree = Tree.gitTreeLookup(repo, treeId);
                 GitSignature signature = Signature.gitSignatureNow(TestUtils.NAME, TestUtils.EMAIL)) {
                GitOid referenceId = Refs.gitReferenceNameToId(repo, "HEAD");
                assert referenceId != null;
                // Create a new commit with the initial commit as the parent
                try (GitCommit parentCommit1 = Commit.gitCommitLookup(repo, referenceId)) {
                    GitOid commitId = Commit.gitCommitCreateV(
                            repo,
                            "HEAD",
                            signature,
                            signature,
                            null,
                            "Another commit",
                            tree,
                            parentCommit1);
                    assertNotNull(commitId);
                    // Create a new commit with the previous commits as the parents
                    referenceId = Refs.gitReferenceNameToId(repo, "HEAD");
                    assert referenceId != null;
                    try (GitCommit parentCommit2 = Commit.gitCommitLookup(repo, referenceId)) {
                        commitId = Commit.gitCommitCreateV(
                                repo,
                                "HEAD",
                                signature,
                                signature,
                                null,
                                "A third commit",
                                tree,
                                parentCommit2, // first, the most recent commit
                                parentCommit1);
                        assertNotNull(commitId);
                    }
                }
            }
        }
    }

    @Test
    void gitCommitCommitter() throws GitException {
        try (GitRepository repo = Repository.gitRepositoryOpen(PATH)) {
            GitOid referenceId = Refs.gitReferenceNameToId(repo, "HEAD");
            assert referenceId != null;
            try (GitCommit commit = Commit.gitCommitLookup(repo, referenceId);
                 GitSignature committer = Commit.gitCommitCommitter(commit)) {
                assertNotNull(committer);
                assertEquals(TestUtils.NAME, committer.name());
                assertEquals(TestUtils.EMAIL, committer.email());
            }
        }
    }

    @Test
    void gitCommitAuthor() throws GitException {
        try (GitRepository repo = Repository.gitRepositoryOpen(PATH)) {
            GitOid referenceId = Refs.gitReferenceNameToId(repo, "HEAD");
            assert referenceId != null;
            try (GitCommit commit = Commit.gitCommitLookup(repo, referenceId);
                 GitSignature author = Commit.gitCommitAuthor(commit)) {
                assertNotNull(author);
                assertEquals(TestUtils.NAME, author.name());
                assertEquals(TestUtils.EMAIL, author.email());
            }
        }
    }
}