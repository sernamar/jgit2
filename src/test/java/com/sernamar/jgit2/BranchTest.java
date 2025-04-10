package com.sernamar.jgit2;

import com.sernamar.jgit2.utils.GitException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.sernamar.jgit2.Refs.gitReferenceNameToId;
import static com.sernamar.jgit2.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;

class BranchTest {

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
    void gitBranchCreate() throws GitException {
        String branchName = "new_branch";
        try (GitRepository repo = Repository.gitRepositoryOpen(PATH)) {
            GitOid commitId = gitReferenceNameToId(repo, "HEAD");
            assert commitId != null;
            GitCommit commit = Commit.gitCommitLookup(repo, commitId);
            GitReference branchRef = Branch.gitBranchCreate(repo, branchName, commit);
            assertNotNull(branchRef);
            assertThrows(GitException.class, () -> Branch.gitBranchCreate(repo, branchName, commit));
            Branch.gitBranchDelete(branchRef);
            assertThrows(GitException.class, () -> Branch.gitBranchDelete(branchRef));
        }
    }

    @Test
    void gitBranchIteratorNew() throws GitException {
        try (GitRepository repo = Repository.gitRepositoryOpen(PATH);
             GitBranchIterator branchIterator = Branch.gitBranchIteratorNew(repo, GitBranchT.LOCAL)) {
            assertNotNull(branchIterator);
        }
    }

    @Test
    void gitBranchNext() throws GitException {
        create3Branches(PATH);
        try (GitRepository repo = Repository.gitRepositoryOpen(PATH);
             GitBranchIterator branchIterator = Branch.gitBranchIteratorNew(repo, GitBranchT.LOCAL)) {
            int branchCount = 0;
            while (Branch.gitBranchNext(GitBranchT.LOCAL, branchIterator) != null) {
                branchCount++;
            }
            assertEquals(3 + 1, branchCount); // +1 for HEAD
        }
    }

    @Test
    void gitBranchName() throws GitException {
        String branchName = "new_branch";
        try (GitRepository repo = Repository.gitRepositoryOpen(PATH)) {
            GitOid commitId = gitReferenceNameToId(repo, "HEAD");
            assert commitId != null;
            GitCommit commit = Commit.gitCommitLookup(repo, commitId);
            GitReference branchRef = Branch.gitBranchCreate(repo, branchName, commit);
            assertEquals(branchName, Branch.gitBranchName(branchRef));
            Branch.gitBranchDelete(branchRef);
        }
    }
}