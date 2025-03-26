package com.sernamar.examples;

import com.sernamar.jgit2.GitCommit;
import com.sernamar.jgit2.GitOid;
import com.sernamar.jgit2.GitRepository;

import java.nio.file.Paths;

import static com.sernamar.jgit2.Commit.gitCommitLookup;
import static com.sernamar.jgit2.Commit.gitCommitMessage;
import static com.sernamar.jgit2.Global.gitLibgit2Init;
import static com.sernamar.jgit2.Global.gitLibgit2Shutdown;
import static com.sernamar.jgit2.Refs.gitReferenceNameToId;
import static com.sernamar.jgit2.Repository.gitRepositoryOpen;

public class GetLastCommitMessage {
    public static void main(String[] args) {
        // Initialize libgit2
        gitLibgit2Init();

        // Get the current path
        String path = Paths.get("").toAbsolutePath().toString();

        // Open repository
        try (GitRepository repo = gitRepositoryOpen(path)) {

            // Get the reference id of "HEAD"
            // Note that `GitOid` represents a fixed-size value and does not allocate resources that need to be
            // explicitly released, so it does not require try-with-resources.
            GitOid referenceId = gitReferenceNameToId(repo, "HEAD");

            // Get last commit
            try (GitCommit commit = gitCommitLookup(repo, referenceId)){

                // Get commit message and print it
                String message = gitCommitMessage(commit);
                System.out.println("Commit message:");
                System.out.println(message);
            }
        }

        // Shutdown libgit2
        gitLibgit2Shutdown();
    }
}
