package com.sernamar.jgit2.examples;

import com.sernamar.jgit2.*;

import java.nio.file.Paths;

public class CommitMessage {
    public static void main(String[] args) {
        // Initialize libgit2
        Global.gitLibgit2Init();

        // Get the current path
        var path = Paths.get("").toAbsolutePath().toString();

        // Open repository
        var repo = Repository.gitRepositoryOpen(path);

        // Get the reference id of the main branch (refs/heads/main)
        var reference = "refs/heads/main";
        var referenceId = Refs.gitReferenceNameToId(repo, reference);

        // Get last commit
        var commit = Commit.gitCommitLookup(repo, referenceId);

        // Get commit message and print it
        var message = Commit.gitCommitMessage(commit);
        System.out.println("Commit message:");
        System.out.println(message);

        // Shutdown libgit2
        Global.gitLibgit2Shutdown();
    }
}
