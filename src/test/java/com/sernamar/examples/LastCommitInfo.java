package com.sernamar.examples;

import com.sernamar.jgit2.GitCommit;
import com.sernamar.jgit2.GitOid;
import com.sernamar.jgit2.GitRepository;
import com.sernamar.jgit2.GitSignature;

import java.nio.file.Paths;
import java.time.OffsetDateTime;

import static com.sernamar.jgit2.Commit.*;
import static com.sernamar.jgit2.Global.gitLibgit2Init;
import static com.sernamar.jgit2.Global.gitLibgit2Shutdown;
import static com.sernamar.jgit2.Refs.gitReferenceNameToId;
import static com.sernamar.jgit2.Repository.gitRepositoryOpen;
import static com.sernamar.jgit2.Signature.*;

public class LastCommitInfo {
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
            assert referenceId != null;
            try (GitCommit commit = gitCommitLookup(repo, referenceId)) {

                // Get author and print it
                GitSignature author = gitCommitAuthor(commit);
                String authorName = gitSignatureName(author);
                String authorEmail = gitSignatureEmail(author);
                OffsetDateTime authorTime = gitSignatureTime(author);
                System.out.println("Author: " + authorName + " <" + authorEmail + ">");
                System.out.println("Author time: " + authorTime);

                // Get committer and print it
                GitSignature committer = gitCommitCommitter(commit);
                String committerName = gitSignatureName(committer);
                String committerEmail = gitSignatureEmail(committer);
                OffsetDateTime committerTime = gitSignatureTime(committer);
                System.out.println("Committer: " + committerName + " <" + committerEmail + ">");
                System.out.println("Committer time: " + committerTime);

                // Get commit message and print it
                String message = gitCommitMessage(commit);
                System.out.println();
                System.out.println(message);
            }
        }

        // Shutdown libgit2
        gitLibgit2Shutdown();
    }
}
