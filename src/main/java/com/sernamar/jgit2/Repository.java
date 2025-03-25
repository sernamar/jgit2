package com.sernamar.jgit2;

import com.sernamar.jgit2.types.GitRepository;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_2.C_POINTER;
import static com.sernamar.jgit2.bindings.git2_2.git_repository_open;
import static com.sernamar.jgit2.utils.GitError.getGitErrorMessage;

public final class Repository {

    private Repository() {
        // Prevent instantiation
    }

    /**
     * Open a git repository.
     * <p>
     * The `path` argument must point to either a git repository
     * folder, or an existing work dir.
     * <p>
     * The method will automatically detect if 'path' is a normal
     * or bare repository or fail is 'path' is neither.
     * <p>
     * Note that the libgit2 library _must_ be initialized using
     * `git_libgit2_init` before any APIs can be called, including
     * this one.
     *
     * @param path the path to the repository
     * @return the repository.
     */
    public static GitRepository gitRepositoryOpen(String path) {
        Arena arena = Arena.ofAuto();
        MemorySegment repoSegment = arena.allocate(C_POINTER);
        MemorySegment pathSegment = arena.allocateFrom(path);

        int ret = git_repository_open(repoSegment, pathSegment);
        if (ret < 0) {
            throw new RuntimeException("Failed to open repository: " + getGitErrorMessage());
        }
        return new GitRepository(repoSegment.get(C_POINTER, 0));
    }
}
