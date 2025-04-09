package com.sernamar.jgit2;

import com.sernamar.jgit2.bindings.git_oid;
import com.sernamar.jgit2.utils.GitException;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_1.*;
import static com.sernamar.jgit2.bindings.git2_2.C_POINTER;
import static com.sernamar.jgit2.utils.GitError.getGitErrorMessage;

public final class Revwalk {

    private Revwalk() {
        // Prevent instantiation
    }

    /**
     * Allocate a new revision walker to iterate through a repo.
     * <p>
     * This revision walker uses a custom memory pool and an internal
     * commit cache, so it is relatively expensive to allocate.
     * <p>
     * For maximum performance, this revision walker should be
     * reused for different walks.
     * <p>
     * This revision walker is *not* thread safe: it may only be
     * used to walk a repository on a single thread; however,
     * it is possible to have several revision walkers in
     * several different threads walking the same repository.
     *
     * @param repo the repo to walk through.
     * @return the new revision walker.
     * @throws GitException if the walker could not be created.
     */
    public static GitRevwalk gitRevwalkNew(GitRepository repo) throws GitException {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment walkSegment = arena.allocate(C_POINTER);
            int ret = git_revwalk_new(walkSegment, repo.segment());
            if (ret < 0) {
                throw new GitException("Failed to create revwalk: " + getGitErrorMessage());
            }
            return new GitRevwalk(walkSegment.get(C_POINTER, 0), true);
        }
    }

    /**
     * Push the repository's HEAD.
     *
     * @param walk the walker being used for the traversal.
     * @throws GitException if the push fails.
     */
    public static void gitRevwalkPushHead(GitRevwalk walk) throws GitException {
        int ret = git_revwalk_push_head(walk.segment());
        if (ret < 0) {
            throw new GitException("Failed to push HEAD: " + getGitErrorMessage());
        }
    }

    /**
     * Change the sorting mode when iterating through the
     * repository's contents.
     * <p>
     * Changing the sorting mode resets the walker.
     *
     * @param walk the walker being used for the traversal.
     * @throws GitException if the sorting mode could not be set.
     */
    public static void gitRevwalkSorting(GitRevwalk walk) throws GitException {
        gitRevwalkSorting(walk, GIT_SORT_NONE());
    }

    /**
     * Change the sorting mode when iterating through the
     * repository's contents.
     * <p>
     * Changing the sorting mode resets the walker.
     *
     * @param walk the walker being used for the traversal.
     * @param sortMode combination of GIT_SORT_XXX flags.
     * @throws GitException if the sorting mode could not be set.
     */
    public static void gitRevwalkSorting(GitRevwalk walk, int sortMode) throws GitException {
        int ret = git_revwalk_sorting(walk.segment(), sortMode);
        if (ret < 0) {
            throw new GitException("Failed to set revwalk sorting: " + getGitErrorMessage());
        }
    }

    /**
     * Get the next commit from the revision walk.
     * <p>
     * The initial call to this method is *not* blocking when
     * iterating through a repo with a time-sorting mode.
     * <p>
     * Iterating with Topological or inverted modes makes the initial
     * call blocking to preprocess the commit list, but this block should be
     * mostly unnoticeable on most repositories (topological preprocessing
     * times at 0.3s on the git.git repo).
     * <p>
     * The revision walker is reset when the walk is over.
     *
     * @param walk the walker to pop the commit from.
     * @return the oid of the next commit.
     */
    public static GitOid gitRevwalkNext(GitRevwalk walk) {
        Arena arena = Arena.ofAuto();
        MemorySegment oidSegment = git_oid.allocate(arena);
        int ret = git_revwalk_next(oidSegment, walk.segment());
        if (ret == GIT_ITEROVER()) {
            return null; // No more commits to iterate
        }
        return new GitOid(oidSegment);
    }
}
