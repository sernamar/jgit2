package com.sernamar.examples;

import com.sernamar.jgit2.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.sernamar.jgit2.Commit.gitCommitCreateV;
import static com.sernamar.jgit2.Commit.gitCommitLookup;
import static com.sernamar.jgit2.Global.gitLibgit2Init;
import static com.sernamar.jgit2.Global.gitLibgit2Shutdown;
import static com.sernamar.jgit2.Index.*;
import static com.sernamar.jgit2.Message.gitMessagePrettify;
import static com.sernamar.jgit2.Refs.gitReferenceNameToId;
import static com.sernamar.jgit2.Repository.gitRepositoryIndex;
import static com.sernamar.jgit2.Repository.gitRepositoryInit;
import static com.sernamar.jgit2.Signature.gitSignatureNow;
import static com.sernamar.jgit2.Tree.gitTreeLookup;

public class CreateCommits {
    private static final String path = "/tmp/repo";
    private static final String name = "sernamar";
    private static final String email = "sernamar@protonmail.com";

    private static void createInitialCommit(GitRepository repo) {
        try (GitSignature signature = gitSignatureNow(name, email); // Create signature
             GitIndex index = gitRepositoryIndex(repo)) { // Get the index of the repository
            GitOid treeId = gitIndexWriteTree(index); // Write the index contents to the ODB as a tree
            try (GitTree tree = gitTreeLookup(repo, treeId)) { // Lookup the tree object from the repository
                // Create the initial commit
                String message = gitMessagePrettify("Initial commit");
                GitOid commitId = gitCommitCreateV(
                        repo,
                        "HEAD",
                        signature,
                        signature,
                        null,
                        message,
                        tree
                );
                // Print the commit ID
                System.out.println("Commit ID: " + commitId);
            }
        }
    }

    private static void createCommit(GitRepository repo) {
        try (GitSignature signature = gitSignatureNow(name, email); // Create signature
             GitIndex index = gitRepositoryIndex(repo)) { // Get the index of the repository

            // Create a new file in the repository
            String filename = "hello.txt";
            String content = "Hello, world!";
            createFile(filename, content);

            // Add the file to the index
            gitIndexAddByPath(index, filename);

            // Write the in-memory index to disk
            gitIndexWrite(index);

            // Write the index contents to the ODB as a tree
            GitOid treeId = gitIndexWriteTree(index);

            // Lookup the tree object from the repository
            try (GitTree tree = gitTreeLookup(repo, treeId)) {
                // Lookup the HEAD reference and resolve to OID
                GitOid referenceId = gitReferenceNameToId(repo, "HEAD");

                // if referenceId is null, create a new commit with no parent
                if (referenceId == null) {
                    createInitialCommit(repo);
                } else {
                    // Lookup the parent commit from the repository
                    try (GitCommit parentCommit = gitCommitLookup(repo, referenceId)) {
                        // Create the commit
                        String message = gitMessagePrettify("Add hello.txt");
                        GitOid commitId = gitCommitCreateV(
                                repo,
                                "HEAD",
                                signature,
                                signature,
                                null,
                                message,
                                tree,
                                parentCommit
                        );
                        // Print the commit ID
                        System.out.println("Commit ID: " + commitId);
                    }
                }
            }
        }
    }

    private static void createFile(String filename, String content) {
        try {
            Path file = Paths.get(path, filename);
            Files.write(file, content.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        // Initialize libgit2
        gitLibgit2Init();

        try (GitRepository repo = gitRepositoryInit(path)) {
            createInitialCommit(repo);
            createCommit(repo);
        }

        // Shutdown libgit2
        gitLibgit2Shutdown();
    }
}
