package com.sernamar.examples;

import com.sernamar.jgit2.*;
import com.sernamar.jgit2.GitCommit;
import com.sernamar.jgit2.GitOid;
import com.sernamar.jgit2.GitRepository;

import java.nio.file.Paths;

public class CommitMessage {
    public static void main(String[] args) {
        // Initialize libgit2
        Global.gitLibgit2Init();

        // Get the current path
        String path = Paths.get("").toAbsolutePath().toString();

        // Open repository
        try (GitRepository repo = Repository.gitRepositoryOpen(path)) {

            // Get the reference id of "HEAD"
            // Note that `GitOid` represents a fixed-size value and does not allocate resources that need to be
            // explicitly released, so it does not require try-with-resources.
            GitOid referenceId = Refs.gitReferenceNameToId(repo, "HEAD");

            // Get last commit
            try (GitCommit commit = Commit.gitCommitLookup(repo, referenceId)){

                // Get commit message and print it
                String message = Commit.gitCommitMessage(commit);
                System.out.println("Commit message:");
                System.out.println(message);
            }
        }

        // Shutdown libgit2
        Global.gitLibgit2Shutdown();
    }
}
