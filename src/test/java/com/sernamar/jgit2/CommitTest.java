package com.sernamar.jgit2;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CommitTest {

    private static final String PATH = "/tmp/repo";
    private static final String NAME = "name";
    private static final String EMAIL = "name@mail.com";
    private static final String MESSAGE = "Initial commit";

    @BeforeAll
    static void beforeAll() {
        // Initialize libgit2
        Global.gitLibgit2Init();

        // Create a repo in `/tmp/repo`, and add an initial commit
        try (GitRepository repo = Repository.gitRepositoryInit(PATH);
             GitIndex index = Repository.gitRepositoryIndex(repo)) {
            GitOid treeId = Index.gitIndexWriteTree(index);
            try (GitTree tree = Tree.gitTreeLookup(repo, treeId);
                 GitSignature signature = Signature.gitSignatureNow(NAME, EMAIL)) {
                Commit.gitCommitCreateV(
                        repo,
                        "HEAD",
                        signature,
                        signature,
                        null,
                        MESSAGE,
                        tree);
            }
        }
    }

    @AfterAll
    static void afterAll() throws IOException {
        // Delete the /tmp/repo directory
        try (Stream<Path> paths = Files.walk(Paths.get(PATH))) {
            paths.sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }

        // Shutdown libgit2
        Global.gitLibgit2Shutdown();
    }

    @Test
    void gitCommitLookup() {
        try (GitRepository repo = Repository.gitRepositoryOpen(PATH)) {
            GitOid referenceId = Refs.gitReferenceNameToId(repo, "HEAD");
            assert referenceId != null;
            try (GitCommit parentCommit = Commit.gitCommitLookup(repo, referenceId)) {
                assertNotNull(parentCommit);
            }
        }
    }

    @Test
    void gitCommitMessage() {
        try (GitRepository repo = Repository.gitRepositoryOpen(PATH)) {
            GitOid referenceId = Refs.gitReferenceNameToId(repo, "HEAD");
            assert referenceId != null;
            try (GitCommit parentCommit = Commit.gitCommitLookup(repo, referenceId)) {
                String message = Commit.gitCommitMessage(parentCommit);
                assertEquals(MESSAGE, message);
            }
        }
    }

    @Test
    void gitCommitCreateV() {
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
                 GitSignature signature = Signature.gitSignatureNow(NAME, EMAIL)) {
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
    void gitCommitCommitter() {
        try (GitRepository repo = Repository.gitRepositoryOpen(PATH)) {
            GitOid referenceId = Refs.gitReferenceNameToId(repo, "HEAD");
            assert referenceId != null;
            try (GitCommit commit = Commit.gitCommitLookup(repo, referenceId)) {
                GitSignature committer = Commit.gitCommitCommitter(commit);
                assertNotNull(committer);
                try (GitSignature signature = Signature.gitSignatureNow(NAME, EMAIL)) {
                    assertEquals(signature.name(), committer.name());
                    assertEquals(signature.email(), committer.email());
                }
            }
        }
    }

    @Test
    void gitCommitAuthor() {
        try (GitRepository repo = Repository.gitRepositoryOpen(PATH)) {
            GitOid referenceId = Refs.gitReferenceNameToId(repo, "HEAD");
            assert referenceId != null;
            try (GitCommit commit = Commit.gitCommitLookup(repo, referenceId)) {
                GitSignature author = Commit.gitCommitAuthor(commit);
                assertNotNull(author);
                try (GitSignature signature = Signature.gitSignatureNow(NAME, EMAIL)) {
                    assertEquals(signature.name(), author.name());
                    assertEquals(signature.email(), author.email());
                }
            }
        }
    }
}