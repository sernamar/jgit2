package com.sernamar.jgit2.utils;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_1.git_error_last;

public final class GitError {

    private GitError() {
        // Prevent instantiation
    }

    public static String getGitErrorMessage() {
        MemorySegment error = git_error_last();
        if (error == MemorySegment.NULL) {
            return "Unknown error";
        }
        return error.getString(0);
    }
}
