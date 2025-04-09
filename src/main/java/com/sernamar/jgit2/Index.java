package com.sernamar.jgit2;

import com.sernamar.jgit2.bindings.git_oid;
import com.sernamar.jgit2.utils.GitException;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_1.*;
import static com.sernamar.jgit2.utils.GitError.getGitErrorMessage;

public final class Index {

    private Index() {
        // Prevent instantiation
    }

    /**
     * Add or update an index entry from a file on disk
     * <p>
     * The file `path` must be relative to the repository's
     * working folder and must be readable.
     * <p>
     * This method will fail in bare index instances.
     * <p>
     * This forces the file to be added to the index, not looking
     * at gitignore rules.  Those rules can be evaluated through
     * the git_status APIs (in status.h) before calling this.
     * <p>
     * If this file currently is the result of a merge conflict, this
     * file will no longer be marked as conflicting.  The data about
     * the conflict will be moved to the "resolve undo" (REUC) section.
     *
     * @param index an existing index.
     * @param path  filename to add.
     * @throws GitException if the index is bare or the file cannot be read.
     */
    public static void gitIndexAddByPath(GitIndex index, String path) throws GitException {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment pathSegment = arena.allocateFrom(path);
            int ret = git_index_add_bypath(index.segment(), pathSegment);
            if (ret < 0) {
                throw new GitException("Failed to add file to index: " + getGitErrorMessage());
            }
        }
    }

    /**
     * Write an existing index object from memory back to disk
     * using an atomic file lock.
     *
     * @param index an existing index.
     * @throws GitException if the index cannot be written.
     */
    public static void gitIndexWrite(GitIndex index) throws GitException {
        int ret = git_index_write(index.segment());
        if (ret < 0) {
            throw new GitException("Failed to write index: " + getGitErrorMessage());
        }
    }

    /**
     * Write the index as a tree.
     * <p>
     * This method will scan the index and write a representation
     * of its current state back to disk; it recursively creates
     * tree objects for each of the subtrees stored in the index,
     * but only returns the OID of the root tree. This is the OID
     * that can be used e.g. to create a commit.
     * <p>
     * The index instance cannot be bare, and needs to be associated
     * to an existing repository.
     * <p>
     * The index must not contain any file in conflict.
     *
     * @param index index to write.
     * @return the OID where to store the written tree.
     * @throws GitException if the index is bare or contains unmerged entries.
     */
    public static GitOid gitIndexWriteTree(GitIndex index) throws GitException {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment oidSegment = git_oid.allocate(arena);
            int ret = git_index_write_tree(oidSegment, index.segment());
            if (ret == GIT_EUNMERGED()) {
                throw new GitException("Cannot write tree with unmerged entries: " + getGitErrorMessage());
            } else if (ret < 0) {
                throw new GitException("Failed to write index tree: " + getGitErrorMessage());
            }
            return new GitOid(oidSegment);
        }
    }
}
