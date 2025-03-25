package com.sernamar.examples;

import com.sernamar.jgit2.*;
import com.sernamar.jgit2.types.GitCommit;
import com.sernamar.jgit2.types.GitOid;
import com.sernamar.jgit2.types.GitRepository;

import java.nio.file.Paths;

public class CommitMessage {
    public static void main(String[] args) {
        // Initialize libgit2
        Global.gitLibgit2Init();

        // Get the current path
        String path = Paths.get("").toAbsolutePath().toString();

        // Open repository
        GitRepository repo = Repository.gitRepositoryOpen(path);
        System.out.println("Check if I can access segment() method: " + repo.segment());

        // Get the reference id of the main branch (refs/heads/main)
        String reference = "refs/heads/main";
        GitOid referenceId = Refs.gitReferenceNameToId(repo, reference);

        // Get last commit
        GitCommit commit = Commit.gitCommitLookup(repo, referenceId);

        // Get commit message and print it
        String message = Commit.gitCommitMessage(commit);
        System.out.println("Commit message:");
        System.out.println(message);

        // Shutdown libgit2
        Global.gitLibgit2Shutdown();
    }
}
