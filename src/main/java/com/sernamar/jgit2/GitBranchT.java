package com.sernamar.jgit2;

import static com.sernamar.jgit2.bindings.git2_2.*;

/**
 * Git branch types.
 * <p>
 * This class is used to specify the type of branch when creating or listing branches.
 * <p>
 * The branch type can be one of the following:
 * <ul>
 * <li>LOCAL: A local branch</li>
 * <li>REMOTE: A remote branch</li>
 * <li>ALL: All branches (local and remote)</li>
 * </ul>
 */
public final class GitBranchT {

    public static final int LOCAL = GIT_BRANCH_LOCAL();
    public static final int REMOTE = GIT_BRANCH_REMOTE();
    public static final int ALL = GIT_BRANCH_ALL();

    private GitBranchT() {
        // Prevent instantiation
    }
}
