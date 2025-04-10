package com.sernamar.jgit2;

import com.sernamar.jgit2.utils.GitException;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_1.GIT_EINVALIDSPEC;
import static com.sernamar.jgit2.bindings.git2_1.git_branch_create;
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
     * A proper reference is written in the refs/heads namespace
     * pointing to the provided target commit.
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
     * A proper reference is written in the refs/heads namespace
     * pointing to the provided target commit.
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
}
