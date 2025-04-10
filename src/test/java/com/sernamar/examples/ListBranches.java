package com.sernamar.examples;

import com.sernamar.jgit2.*;
import com.sernamar.jgit2.utils.GitException;

import java.util.ArrayList;
import java.util.List;

import static com.sernamar.jgit2.Branch.gitBranchName;
import static com.sernamar.jgit2.Branch.gitBranchNext;
import static com.sernamar.jgit2.Commit.gitCommitCreateV;
import static com.sernamar.jgit2.Global.gitLibgit2Init;
import static com.sernamar.jgit2.Global.gitLibgit2Shutdown;
import static com.sernamar.jgit2.Index.gitIndexWriteTree;
import static com.sernamar.jgit2.Message.gitMessagePrettify;
import static com.sernamar.jgit2.Refs.gitReferenceNameToId;
import static com.sernamar.jgit2.Repository.gitRepositoryIndex;
import static com.sernamar.jgit2.Repository.gitRepositoryInit;
import static com.sernamar.jgit2.Signature.gitSignatureNow;
import static com.sernamar.jgit2.TestUtils.deleteRepoDirectory;
import static com.sernamar.jgit2.Tree.gitTreeLookup;

public class ListBranches {
    private static final String PATH = "/tmp/repo";
    private static final String NAME = "sernamar";
    private static final String EMAIL = "sernamar@protonmail.com";

    private static void createCommit(GitRepository repo, GitTree tree, GitCommit parentCommit, String message) throws GitException {
        try (GitSignature signature = gitSignatureNow(NAME, EMAIL)) {
            if ((parentCommit == null)) {
                gitCommitCreateV(repo, "HEAD", signature, signature, null, gitMessagePrettify(message), tree);
            } else {
                gitCommitCreateV(repo, "HEAD", signature, signature, null, gitMessagePrettify(message), tree, parentCommit);
            }
        }
    }

    public static void main(String[] args) {
        gitLibgit2Init();

        try (GitRepository repo = gitRepositoryInit(PATH);
             GitIndex index = gitRepositoryIndex(repo)) {
            // Create initial commit
            GitOid treeId = gitIndexWriteTree(index);
            try (GitTree tree = gitTreeLookup(repo, treeId)) {
                createCommit(repo, tree, null, "Initial commit");
            }

            // Create new branch
            String branchName = "new_branch";
            GitOid commitId = gitReferenceNameToId(repo, "HEAD");
            assert commitId != null;
            try (GitCommit commit = Commit.gitCommitLookup(repo, commitId);
                 GitReference branchRef = Branch.gitBranchCreate(repo, branchName, commit)) {
                System.out.println("Branch created: " + branchName);
            }

            // List branches
            List<String> branches = new ArrayList<>();
            try (GitBranchIterator branchIterator = Branch.gitBranchIteratorNew(repo, GitBranchT.LOCAL)) {
                GitReference branchRef;
                while ((branchRef = gitBranchNext(GitBranchT.LOCAL, branchIterator)) != null) {
                    branches.add(gitBranchName(branchRef));
                }
                System.out.println("Branches: " + branches);

            }
        } catch (GitException e) {
            System.err.println(e.getMessage());
        } finally {
            deleteRepoDirectory(PATH);
        }

        gitLibgit2Shutdown();
    }
}
