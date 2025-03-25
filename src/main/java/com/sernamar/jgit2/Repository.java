package com.sernamar.jgit2;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_2.*;
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

    /**
     * Free a previously allocated repository
     * <p>
     * Note that after a repository is freed, all the objects it has spawned
     * will still exist until they are manually closed by the user
     * with `git_object_free`, but accessing any of the attributes of
     * an object without a backing repository will result in undefined
     * behavior
     *
     * @param repo repository handle to close. If NULL nothing occurs.
     */
    public static void gitRepositoryFree(GitRepository repo) {
        git_repository_free(repo.segment());
    }
}
