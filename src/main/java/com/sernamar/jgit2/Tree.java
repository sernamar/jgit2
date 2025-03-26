package com.sernamar.jgit2;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_2.C_POINTER;
import static com.sernamar.jgit2.bindings.git2_2.git_tree_lookup;
import static com.sernamar.jgit2.utils.GitError.getGitErrorMessage;

public final class Tree {

    private Tree() {
        // Prevent instantiation
    }

    /**
     * Lookup a tree object from the repository.
     *
     * @param repo the repo to use when locating the tree.
     * @param id   identity of the tree to locate.
     * @return the located tree.
     */
    public static GitTree gitTreeLookup(GitRepository repo, GitOid id) {
        Arena arena = Arena.ofAuto();
        MemorySegment treeSegment = arena.allocate(C_POINTER);
        int ret = git_tree_lookup(treeSegment, repo.segment(), id.segment());
        if (ret < 0) {
            throw new RuntimeException("Failed to lookup tree: " + getGitErrorMessage());
        }
        return new GitTree(treeSegment.get(C_POINTER, 0));
    }
}
