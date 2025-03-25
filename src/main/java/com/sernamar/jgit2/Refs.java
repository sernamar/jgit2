package com.sernamar.jgit2;

import com.sernamar.jgit2.bindings.git_oid;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_2.git_reference_name_to_id;
import static com.sernamar.jgit2.utils.GitError.getGitErrorMessage;

public final class Refs {

    private Refs() {
        // Prevent instantiation
    }

    /**
     * Lookup a reference by name and resolve immediately to OID.
     * <p>
     * This function provides a quick way to resolve a reference name straight
     * through to the object id that it refers to.  This avoids having to
     * allocate or free any `git_reference` objects for simple situations.
     * <p>
     * The name will be checked for validity.
     * See `git_reference_symbolic_create()` for rules about valid names.
     *
     * @param repo The repository in which to look up the reference
     * @param name The long name for the reference (e.g. HEAD, refs/heads/master, refs/tags/v0.1.0, ...)
     * @return the OID of the reference.
     */
    public static GitOid gitReferenceNameToId(GitRepository repo, String name) {
        Arena arena = Arena.ofAuto();
        MemorySegment oidSegment = git_oid.allocate(arena);
        MemorySegment refNameSegment = arena.allocateFrom(name);

        int ret = git_reference_name_to_id(oidSegment, repo.segment(), refNameSegment);
        if (ret < 0) {
            throw new RuntimeException("Failed to get the OID of the reference: " + getGitErrorMessage());
        }
        return new GitOid(oidSegment);
    }
}
