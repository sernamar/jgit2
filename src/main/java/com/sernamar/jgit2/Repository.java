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
     * Creates a new bare Git repository in the given folder.
     * <p>
     * Note that the libgit2 library _must_ be initialized using
     * `git_libgit2_init` before any APIs can be called, including
     * this one.
     *
     * @param path the path to the repository
     * @return the repository.
     */
    public static GitRepository gitRepositoryInit(String path) {
        Arena arena = Arena.ofAuto();
        MemorySegment repoSegment = arena.allocate(C_POINTER);
        MemorySegment pathSegment = arena.allocateFrom(path);

        int ret = git_repository_init(repoSegment, pathSegment, 0);
        if (ret < 0) {
            throw new RuntimeException("Failed to initialize repository: " + getGitErrorMessage());
        }
        return new GitRepository(repoSegment.get(C_POINTER, 0));
    }

    /**
     * Creates a new Git repository in the given folder.
     * <p>
     * Note that the libgit2 library _must_ be initialized using
     * `git_libgit2_init` before any APIs can be called, including
     * this one.
     *
     * @param path   the path to the repository
     * @param isBare if true, a Git repository without a working directory is
     *               created at the pointed path. If false, provided path will be
     *               considered as the working directory into which the .git directory
     *               will be created.
     * @return the repository.
     */
    public static GitRepository gitRepositoryInit(String path, boolean isBare) {
        Arena arena = Arena.ofAuto();
        MemorySegment repoSegment = arena.allocate(C_POINTER);
        MemorySegment pathSegment = arena.allocateFrom(path);

        int ret = git_repository_init(repoSegment, pathSegment, isBare ? 1 : 0);
        if (ret < 0) {
            throw new RuntimeException("Failed to initialize repository: " + getGitErrorMessage());
        }
        return new GitRepository(repoSegment.get(C_POINTER, 0));
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

    /**
     * Get the Index file for this repository.
     * <p>
     * If a custom index has not been set, the default
     * index for the repository will be returned (the one
     * located in `.git/index`).
     * <p>
     * The index must be freed once it's no longer being used by
     * the user.
     *
     * @param repo the repository.
     * @return the index.
     */
    public static GitIndex gitRepositoryIndex(GitRepository repo) {
        Arena arena = Arena.ofAuto();
        MemorySegment indexSegment = arena.allocate(C_POINTER);

        int ret = git_repository_index(indexSegment, repo.segment());
        if (ret < 0) {
            throw new RuntimeException("Failed to get repository index: " + getGitErrorMessage());
        }
        return new GitIndex(indexSegment.get(C_POINTER, 0));
    }
}
