package com.sernamar.jgit2;

import com.sernamar.jgit2.utils.GitException;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_1.*;
import static com.sernamar.jgit2.bindings.git2_2.C_POINTER;
import static com.sernamar.jgit2.utils.GitError.getGitErrorMessage;

public final class Branch {

    private Branch() {
        // Prevent instantiation
    }

    /**
     * Create a new branch pointing at a target commit
     * <p>
     * A new direct reference will be created pointing to
     * this target commit. If `force` is true and a reference
     * already exists with the given name, it'll be replaced.
     * <p>
     * The returned reference must be freed by the user.
     * <p>
     * The branch name will be checked for validity.
     * See `git_tag_create()` for rules about valid names.
     *
     * @param repo       the repository to create the branch in.
     * @param branchName name for the branch; this name is
     *                   validated for consistency. It should also not conflict with
     *                   an already existing branch name.
     * @param target     commit to which this branch should point. This object
     *                   must belong to the given `repo`.
     * @return a reference to the newly created branch.
     * <p>
     * A proper reference is written in the refs/heads namespace
     * pointing to the provided target commit.
     * @throws GitException if the branch name is invalid or if the branch could not be created.
     */
    public static GitReference gitBranchCreate(GitRepository repo, String branchName, GitCommit target) throws GitException {
        return gitBranchCreate(repo, branchName, target, false);
    }

    /**
     * Create a new branch pointing at a target commit
     * <p>
     * A new direct reference will be created pointing to
     * this target commit. If `force` is true and a reference
     * already exists with the given name, it'll be replaced.
     * <p>
     * The returned reference must be freed by the user.
     * <p>
     * The branch name will be checked for validity.
     * See `git_tag_create()` for rules about valid names.
     *
     * @param repo       the repository to create the branch in.
     * @param branchName name for the branch; this name is
     *                   validated for consistency. It should also not conflict with
     *                   an already existing branch name.
     * @param target     commit to which this branch should point. This object
     *                   must belong to the given `repo`.
     * @param force      overwrite existing branch.
     * @return a reference to the newly created branch.
     * <p>
     * A proper reference is written in the refs/heads namespace
     * pointing to the provided target commit.
     * @throws GitException if the branch name is invalid or if the branch could not be created.
     */
    public static GitReference gitBranchCreate(GitRepository repo, String branchName, GitCommit target, boolean force) throws GitException {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment branchSegment = arena.allocate(C_POINTER);
            MemorySegment repoSegment = repo.segment();
            MemorySegment branchNameSegment = arena.allocateFrom(branchName);
            MemorySegment targetSegment = target.segment();
            int forceInt = force ? 1 : 0;

            int ret = git_branch_create(branchSegment, repoSegment, branchNameSegment, targetSegment, forceInt);
            if (ret == GIT_EINVALIDSPEC()) {
                throw new GitException("Invalid branch name: " + branchName);
            } else if (ret < 0) {
                throw new GitException("Failed to create the branch: " + getGitErrorMessage());
            }
            return new GitReference(branchSegment.get(C_POINTER, 0), true);
        }
    }

    /**
     * Delete an existing branch reference.
     * <p>
     * Note that if the deletion succeeds, the reference object will not
     * be valid anymore, and should be freed immediately by the user using
     * `git_reference_free()`.
     *
     * @param branch a valid reference representing a branch.
     * @throws GitException if the branch could not be deleted.
     */
    public static void gitBranchDelete(GitReference branch) throws GitException {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment branchSegment = branch.segment();
            int ret = git_branch_delete(branchSegment);
            if (ret < 0) {
                throw new GitException("Failed to delete the branch: " + getGitErrorMessage());
            }
        }
    }

    /**
     * Create an iterator which loops over the requested branches.
     *
     * @param repo       repository where to find the branches.
     * @param branchType the type of branches to iterate over. It can be one of:
     *                   {@link GitBranchT#LOCAL}, {@link GitBranchT#REMOTE}, {@link GitBranchT#ALL}.
     * @return the branch iterator.
     * @throws GitException if the iterator could not be created.
     */
    public static GitBranchIterator gitBranchIteratorNew(GitRepository repo, int branchType) throws GitException {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment gitBranchIteratorSegment = arena.allocate(C_POINTER);
            MemorySegment repoSegment = repo.segment();

            int ret = git_branch_iterator_new(gitBranchIteratorSegment, repoSegment, branchType);
            if (ret < 0) {
                throw new GitException("Failed to create branch iterator: " + getGitErrorMessage());
            }
            return new GitBranchIterator(gitBranchIteratorSegment.get(C_POINTER, 0), true);
        }
    }

    /**
     * Retrieve the next branch from the iterator
     *
     * @param branchType the type of branch to retrieve. It can be one of:
     *                   {@link GitBranchT#LOCAL}, {@link GitBranchT#REMOTE}, {@link GitBranchT#ALL}.
     * @param iter       the branch iterator.
     * @return the next branch or null if there are no more branches.
     * @throws GitException if the branch could not be retrieved.
     */
    public static GitReference gitBranchNext(int branchType, GitBranchIterator iter) throws GitException {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment referenceSegment = arena.allocate(C_POINTER);
            MemorySegment branchTypeSegment = arena.allocate(C_INT);
            branchTypeSegment.set(C_INT, 0, branchType);
            MemorySegment iterSegment = iter.segment();

            int ret = git_branch_next(referenceSegment, branchTypeSegment, iterSegment);
            if (ret == GIT_ITEROVER()) {
                return null;
            } else if (ret < 0) {
                throw new GitException("Failed to get next branch: " + getGitErrorMessage());
            }
            return new GitReference(referenceSegment.get(C_POINTER, 0), true);
        }
    }

    /**
     * Get the branch name
     * <p>
     * Given a reference object, this will check that it really is a branch (ie.
     * it lives under "refs/heads/" or "refs/remotes/"), and return the branch part
     * of it.
     *
     * @param reference a reference object, ideally pointing to a branch
     * @return the branch name.
     * @throws GitException if the reference is invalid or if the branch name could not be retrieved.
     */
    public static String gitBranchName(GitReference reference) throws GitException {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment nameSegment = arena.allocate(C_POINTER);
            MemorySegment referenceSegment = reference.segment();

            int ret = git_branch_name(nameSegment, referenceSegment);
            if (ret == GIT_EINVALID()) {
                throw new GitException("Invalid reference: " + reference);
            } else if (ret < 0) {
                throw new GitException("Failed to get branch name: " + getGitErrorMessage());
            }
            return nameSegment.get(C_POINTER, 0).getString(0);
        }
    }
}
