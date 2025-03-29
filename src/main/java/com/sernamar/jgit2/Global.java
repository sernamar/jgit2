package com.sernamar.jgit2;

import static com.sernamar.jgit2.bindings.git2_1.git_libgit2_init;
import static com.sernamar.jgit2.bindings.git2_1.git_libgit2_shutdown;
import static com.sernamar.jgit2.utils.GitError.getGitErrorMessage;

public final class Global {

    private Global() {
        // Prevent instantiation
    }

    /**
     * Init the global state.
     * <p>
     * This function must be called before any other libgit2 function in
     * order to set up global state and threading.
     * <p>
     * This function may be called multiple times - it will return the number
     * of times the initialization has been called (including this one) that have
     * not subsequently been shutdown.
     *
     * @return the number of initializations of the library.
     * @throws RuntimeException if the initialization fails.
     */
    public static int gitLibgit2Init() {
        int ret = git_libgit2_init();
        if (ret < 0) {
            throw new RuntimeException("Failed to initialize libgit2: " + getGitErrorMessage());
        }
        return ret;
    }

    /**
     * Shutdown the global state
     * <p>
     * Clean up the global state and threading context after calling it as
     * many times as `gitLibgit2Init()` was called - it will return the
     * number of remaining initializations that have not been shutdown
     * (after this one).
     *
     * @return the number of remaining initializations of the library.
     * @throws RuntimeException if the shutdown fails.
     */
    public static int gitLibgit2Shutdown() {
        int ret = git_libgit2_shutdown();
        if (ret < 0) {
            throw new RuntimeException("Failed to shutdown libgit2: " + getGitErrorMessage());
        }
        return ret;
    }
}
