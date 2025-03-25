package com.sernamar.jgit2;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_2.*;
import static com.sernamar.jgit2.utils.GitError.getGitErrorMessage;

public final class Commit {

    private Commit() {
        // Prevent instantiation
    }

    /**
     * Lookup a commit object from a repository.
     *
     * @param repo the repo to use when locating the commit.
     * @param id   identity of the commit to locate.
     * @return the looked up commit.
     */
    public static GitCommit gitCommitLookup(GitRepository repo, GitOid id) {
        Arena arena = Arena.ofAuto();
        MemorySegment commitSegment = arena.allocate(C_POINTER);
        int ret = git_commit_lookup(commitSegment, repo.segment(), id.segment());
        if (ret < 0) {
            throw new RuntimeException("Failed to get the commit: " + getGitErrorMessage());
        }
        return new GitCommit(commitSegment.get(C_POINTER, 0));
    }

    /**
     * Get the full message of a commit.
     * <p>
     * The returned message will be slightly prettified by removing any
     * potential leading newlines.
     *
     * @param commit a previously loaded commit.
     * @return the message of a commit
     */
    public static String gitCommitMessage(GitCommit commit) {
        return git_commit_message(commit.segment()).getString(0);
    }

    public static void gitCommitFree(GitCommit commit) {
        git_commit_free(commit.segment());
    }
}
