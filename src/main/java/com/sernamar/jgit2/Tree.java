package com.sernamar.jgit2;

import com.sernamar.jgit2.utils.GitException;

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
     * @throws GitException if the tree could not be located.
     */
    public static GitTree gitTreeLookup(GitRepository repo, GitOid id) throws GitException {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment treeSegment = arena.allocate(C_POINTER);
            MemorySegment repoSegment = repo.segment();
            MemorySegment oidSegment = id.toSegment(arena);

            int ret = git_tree_lookup(treeSegment, repoSegment, oidSegment);
            if (ret < 0) {
                throw new GitException("Failed to lookup tree: " + getGitErrorMessage());
            }
            return new GitTree(treeSegment.get(C_POINTER, 0), true);
        }
    }
}
