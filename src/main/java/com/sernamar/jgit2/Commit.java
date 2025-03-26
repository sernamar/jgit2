package com.sernamar.jgit2;

import com.sernamar.jgit2.bindings.git_oid;

import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.util.Arrays;

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

    public static GitOid gitCommitCreateV(GitRepository repo,
                                          String updateRef,
                                          GitSignature author,
                                          GitSignature committer,
                                          String messageEncoding,
                                          String message,
                                          GitTree tree,
                                          GitCommit... parents) {
        Arena arena = Arena.ofAuto();
        MemorySegment idSegment = git_oid.allocate(arena);
        MemorySegment updateRefSegment = arena.allocateFrom(updateRef);
        MemorySegment messageEncodingSegment = arena.allocateFrom(messageEncoding);
        MemorySegment messageSegment = arena.allocateFrom(message);

        // Convert parents to an array of MemorySegments
        MemorySegment[] parentSegments = Arrays.stream(parents)
                .map(GitCommit::segment)
                .toArray(MemorySegment[]::new);

        // Create a FunctionDescriptor for the variadic parameters
        MemoryLayout[] variadicLayouts = new MemoryLayout[parents.length];
        Arrays.fill(variadicLayouts, C_POINTER);


        // As `git_commit_create_v` is a variadic function, we need to use the `makeInvoker` method
        // to create an invoker that can be used to call the function.
        git_commit_create_v invoker = git_commit_create_v.makeInvoker(variadicLayouts);
        int ret = invoker.apply(
                idSegment,
                repo.segment(),
                updateRefSegment,
                author.segment(),
                committer.segment(),
                messageEncodingSegment,
                messageSegment,
                tree.segment(),
                parents.length,
                (Object[]) parentSegments
        );
        if (ret < 0) {
            throw new RuntimeException("Failed to create the commit: " + getGitErrorMessage());
        }
        return new GitOid(idSegment);
    }
}
