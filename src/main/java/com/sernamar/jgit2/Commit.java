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

    /**
     * Close an open commit
     * <p>
     * This is a wrapper around git_object_free()
     * <p>
     * IMPORTANT:
     * It *is* necessary to call this method when you stop
     * using a commit. Failure to do so will cause a memory leak.
     *
     * @param commit the commit to close
     */
    public static void gitCommitFree(GitCommit commit) {
        git_commit_free(commit.segment());
    }

    /**
     * Create new commit in the repository using a variable argument list.
     * <p>
     * The message will **not** be cleaned up automatically. You can do that
     * with the `git_message_prettify()` function.
     * <p>
     * The parents for the commit are specified as a variable list of pointers
     * to `const git_commit *`. Note that this is a convenience method which may
     * not be safe to export for certain languages or compilers
     * <p>
     * All other parameters remain the same as `git_commit_create()`.
     *
     * @param repo            Repository where to store the commit
     * @param updateRef       If not NULL, name of the reference that
     *                        will be updated to point to this commit. If the reference
     *                        is not direct, it will be resolved to a direct reference.
     *                        Use "HEAD" to update the HEAD of the current branch and
     *                        make it point to this commit. If the reference doesn't
     *                        exist yet, it will be created. If it does exist, the first
     *                        parent must be the tip of this branch.
     * @param author          Signature with author and author time of commit
     * @param committer       Signature with committer and * commit time of commit
     * @param messageEncoding The encoding for the message in the
     *                        commit, represented with a standard encoding name.
     *                        E.g. "UTF-8". If NULL, no encoding header is written and
     *                        UTF-8 is assumed.
     * @param message         Full message for this commit
     * @param tree            An instance of a `git_tree` object that will
     *                        be used as the tree for the commit. This tree object must
     *                        also be owned by the given `repo`.
     * @param parents         Variable argument list of parents for this commit.
     * @return the OID of the newly created commit.
     * The created commit will be written to the Object Database and
     * the given reference will be updated to point to it
     */
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
        MemorySegment messageEncodingSegment =
                messageEncoding != null ? arena.allocateFrom(messageEncoding) : MemorySegment.NULL;
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
