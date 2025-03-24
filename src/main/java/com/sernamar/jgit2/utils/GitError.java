package com.sernamar.jgit2.utils;

import com.sernamar.jgit2.bindings.git_error;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_1.git_error_last;

public final class GitError {

    private GitError() {
        // Prevent instantiation
    }

    public static String getGitErrorMessage() {
        MemorySegment errorSegment = git_error_last();
        MemorySegment messageSegment = git_error.message(errorSegment);
        if (messageSegment == MemorySegment.NULL) {
            return "Unknown error";
        }
        return messageSegment.getString(0);
    }
}
