package com.sernamar.examples;

import com.sernamar.jgit2.*;
import com.sernamar.jgit2.utils.GitException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.sernamar.jgit2.Commit.*;
import static com.sernamar.jgit2.Global.gitLibgit2Init;
import static com.sernamar.jgit2.Global.gitLibgit2Shutdown;
import static com.sernamar.jgit2.Index.*;
import static com.sernamar.jgit2.Message.gitMessagePrettify;
import static com.sernamar.jgit2.Oid.gitOidToString;
import static com.sernamar.jgit2.Refs.gitReferenceNameToId;
import static com.sernamar.jgit2.Repository.gitRepositoryIndex;
import static com.sernamar.jgit2.Repository.gitRepositoryInit;
import static com.sernamar.jgit2.Revwalk.gitRevwalkNext;
import static com.sernamar.jgit2.Revwalk.gitRevwalkPushHead;
import static com.sernamar.jgit2.Signature.*;
import static com.sernamar.jgit2.TestUtils.deleteRepoDirectory;
import static com.sernamar.jgit2.Tree.gitTreeLookup;

public class CreateCommits {
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

            // Create hello.txt file and add it to the index
            String filename = "hello.txt";
            String content = "Hello, World!";
            try {
                Files.write(Paths.get(PATH, filename), content.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            gitIndexAddByPath(index, filename);
            gitIndexWrite(index);

            // Create another commit
            treeId = gitIndexWriteTree(index);
            try (GitTree tree = gitTreeLookup(repo, treeId)) {
                GitOid referenceId = gitReferenceNameToId(repo, "HEAD");
                assert referenceId != null;
                try (GitCommit parentCommit = gitCommitLookup(repo, referenceId)) {
                    createCommit(repo, tree, parentCommit, "Add hello.txt");
                }
            }
	    
            // Iterate through the commits, printing their information
            try (GitRevwalk walk = Revwalk.gitRevwalkNew(repo)) {
                gitRevwalkPushHead(walk);
                GitOid commitId;
                while ((commitId = gitRevwalkNext(walk)) != null) {
                    try (GitCommit commit = gitCommitLookup(repo, commitId)) {
                        GitSignature author = gitCommitAuthor(commit);
                        GitSignature committer = gitCommitCommitter(commit);
                        long length = 40 + 1; // SHA1 hex size + 1 for null terminator
                        System.out.println("Commit ID: " + gitOidToString(commitId, length));
                        System.out.println("Author: " + gitSignatureName(author) + " <" + gitSignatureEmail(author) + ">");
                        System.out.println("AuthorDate: " + gitSignatureTime(author));
                        System.out.println("Committer: " + gitSignatureName(committer) + " <" + gitSignatureEmail(committer) + ">");
                        System.out.println("CommitDate: " + gitSignatureTime(committer));
                        System.out.println("Message: " + gitCommitMessage(commit));
                    }
                }
            }
        } catch (GitException e) {
            System.err.println(e.getMessage());
        } finally {
            deleteRepoDirectory(PATH);
        }
        gitLibgit2Shutdown();
    }
}
