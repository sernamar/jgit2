package com.sernamar.jgit2;

import com.sernamar.jgit2.utils.GitException;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_1.git_clone;
import static com.sernamar.jgit2.bindings.git2_2.C_POINTER;
import static com.sernamar.jgit2.utils.GitError.getGitErrorMessage;

public final class Clone {

    private Clone() {
        // Prevent instantiation
    }

    /**
     * Clone a remote repository.
     * <p>
     * By default, this creates its repository and initial remote to match
     * git's defaults. You can use the options in the callback to
     * customize how these are created.
     * <p>
     * Note that the libgit2 library _must_ be initialized using
     * `git_libgit2_init` before any APIs can be called, including
     * this one.
     *
     * @param url       the remote repository to clone.
     * @param localPath local directory to clone to.
     * @return the cloned repository.
     * @throws GitException if the clone operation fails.
     */
    public static GitRepository gitClone(String url, String localPath) throws GitException {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment repoSegment = arena.allocate(C_POINTER);
            MemorySegment urlSegment = arena.allocateFrom(url);
            MemorySegment localPathSegment = arena.allocateFrom(localPath);
            // use `MemorySegment.NULL` for `optionsSegment` because if we pass `NULL` to `git_clone`,
            // the function works as though `GIT_OPTIONS_INIT` were passed.
            // This avoids needing to construct and manage the `git_clone_options` struct for now.
            MemorySegment optionsSegment = MemorySegment.NULL;

            int ret = git_clone(repoSegment, urlSegment, localPathSegment, optionsSegment);
            if (ret < 0) {
                throw new GitException("Failed to clone repository: " + getGitErrorMessage());
            }
            return new GitRepository(repoSegment.get(C_POINTER, 0), true);
        }
    }

    // TODO: Add an overloaded gitClone method that accepts a GitCloneOptions argument,
    // allowing callers to customize clone behavior. This will map to the full native signature:
    // int git_clone(git_repository **out, const char *url, const char *local_path, const git_clone_options *options);
}
