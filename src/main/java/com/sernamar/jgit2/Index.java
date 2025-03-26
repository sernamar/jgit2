package com.sernamar.jgit2;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_1.git_index_add_bypath;
import static com.sernamar.jgit2.bindings.git2_1.git_index_write;
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
     */
    public static void gitIndexAddByPath(GitIndex index, String path) {
        Arena arena = Arena.ofAuto();
        MemorySegment pathSegment = arena.allocateFrom(path);
        int ret = git_index_add_bypath(index.segment(), pathSegment);
        if (ret < 0) {
            throw new RuntimeException("Failed to add file to index: " + getGitErrorMessage());
        }
    }

    /**
     * Write an existing index object from memory back to disk
     * using an atomic file lock.
     *
     * @param index an existing index.
     */
    public static void gitIndexWrite(GitIndex index) {
        int ret = git_index_write(index.segment());
        if (ret < 0) {
            throw new RuntimeException("Failed to write index: " + getGitErrorMessage());
        }
    }
}
