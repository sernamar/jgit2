package com.sernamar.jgit2;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_2.C_INT;
import static com.sernamar.jgit2.bindings.git2_2.git_libgit2_version;
import static com.sernamar.jgit2.utils.GitError.getGitErrorMessage;

public final class Common {

    private Common() {
        // Prevent instantiation
    }

    /**
     * Return the version of the libgit2 library
     * being currently used.
     *
     * @return the version of the libgit2 library.
     */
    public static String gitLibgit2Version() {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment majorSegment = arena.allocate(C_INT);
            MemorySegment minorSegment = arena.allocate(C_INT);
            MemorySegment revSegment = arena.allocate(C_INT);

            int ret = git_libgit2_version(majorSegment, minorSegment, revSegment);
            if (ret < 0) {
                throw new RuntimeException("Failed to get libgit2 version: " + getGitErrorMessage());
            }
            return  majorSegment.get(C_INT,0) + "." +
                    minorSegment.get(C_INT,0) + "." +
                    revSegment.get(C_INT,0);
        }
    }
}
