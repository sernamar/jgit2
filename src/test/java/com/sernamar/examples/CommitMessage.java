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

        // Create Git* variables
        GitRepository repo = null;
        GitCommit commit = null;

        try {
            // Open repository
            repo = Repository.gitRepositoryOpen(path);

            // Get the reference id of the main branch (refs/heads/main)
            String reference = "refs/heads/main";
            GitOid referenceId = Refs.gitReferenceNameToId(repo, reference);

            // Get last commit
            commit = Commit.gitCommitLookup(repo, referenceId);

            // Get commit message and print it
            String message = Commit.gitCommitMessage(commit);
            System.out.println("Commit message:");
            System.out.println(message);
        } finally {
            if (commit != null) commit.close(); // Frees native memory (equivalent to `Commit.gitCommitFree(commit)`)
            // `GitOid` is immutable and not allocated with a corresponding free function, so it does not need to be freed
            if (repo != null) repo.close(); // Frees native memory (equivalent to `Repository.gitRepositoryFree(repo)`)
        }

        // Shutdown libgit2
        Global.gitLibgit2Shutdown();
    }
}
