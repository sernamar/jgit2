package com.sernamar.jgit2;

import com.sernamar.jgit2.utils.GitException;

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
     * @throws GitException if the version cannot be retrieved.
     */
    public static String gitLibgit2Version() throws GitException {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment majorSegment = arena.allocate(C_INT);
            MemorySegment minorSegment = arena.allocate(C_INT);
            MemorySegment revSegment = arena.allocate(C_INT);

            int ret = git_libgit2_version(majorSegment, minorSegment, revSegment);
            if (ret < 0) {
                throw new GitException("Failed to get libgit2 version: " + getGitErrorMessage());
            }

            int major = majorSegment.get(C_INT, 0);
            int minor = minorSegment.get(C_INT, 0);
            int rev = revSegment.get(C_INT, 0);

            return major + "." + minor + "." + rev;
        }
    }
}
