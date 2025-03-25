package com.sernamar.jgit2;

import com.sernamar.jgit2.bindings.git_oid;
import com.sernamar.jgit2.types.GitOid;
import com.sernamar.jgit2.types.GitOidShorten;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_2.*;
import static com.sernamar.jgit2.utils.GitError.getGitErrorMessage;

public final class Oid {

    private Oid() {
        // Prevent instantiation
    }

    /**
     * Parse a hex formatted object id into a git_oid.
     * <p>
     * The appropriate number of bytes for the given object ID type will
     * be read from the string - 40 bytes for SHA1, 64 bytes for SHA256.
     * The given string need not be NUL terminated.
     *
     * @param string input hex string; must be pointing at the start of
     *               the hex sequence and have at least the number of bytes
     *               needed for an oid encoded in hex (40 bytes for sha1,
     *               256 bytes for sha256).
     * @return the OID.
     */
    public static GitOid gitOidFromString(String string) {
        Arena arena = Arena.ofAuto();
        MemorySegment oidSegment = git_oid.allocate(arena);
        MemorySegment stringSegment = arena.allocateFrom(string);
        int ret = git_oid_fromstr(oidSegment, stringSegment);
        if (ret < 0) {
            throw new RuntimeException("Failed to convert string to OID: " + getGitErrorMessage());
        }
        return new GitOid(oidSegment);
    }

    /**
     * Format a git_oid into a buffer as a hex format c-string.
     * <p>
     * If there are any input parameter errors (out == NULL, n == 0, oid ==
     * NULL), then a pointer to an empty string is returned, so that the
     * return value can always be printed.
     *
     * @param oid    the oid structure to format.
     * @param length the size of the out buffer.
     * @return the formatted string.
     */
    public static String gitOidToString(GitOid oid, long length) {
        Arena arena = Arena.ofAuto();
        MemorySegment stringSegment = arena.allocateFrom("");
        MemorySegment ret = git_oid_tostr(stringSegment, length + 1, oid.segment());
        return ret.getString(0);
    }

    /**
     * Create a new OID shortener.
     * <p>
     * The OID shortener is used to process a list of OIDs
     * in text form and return the shortest length that would
     * uniquely identify all of them.
     * <p>
     * E.g. look at the result of `git log --abbrev`.
     *
     * @param minLength the minimal length for all identifiers,
     *                  which will be used even if shorter OIDs would still
     *                  be unique.
     * @return the OID shortener, or `null` if OOM (out of memory).
     */
    public static GitOidShorten gitOidShortenNew(long minLength) {
        return new GitOidShorten(git_oid_shorten_new(minLength));
    }

    /**
     * Free an OID shortener.
     *
     * @param shortenerId the OID shortener.
     */
    public static void gitOidShortenFree(GitOidShorten shortenerId) {
        git_oid_shorten_free(shortenerId.segment());
    }

    /**
     * Add a new OID to set of shortened OIDs and calculate
     * the minimal length to uniquely identify all the OIDs in
     * the set.
     * <p>
     * The OID is expected to be a 40-char hexadecimal string.
     * The OID is owned by the user and will not be modified
     * or freed.
     * <p>
     * For performance reasons, there is a hard-limit of how many
     * OIDs can be added to a single set (around ~32000, assuming
     * a mostly randomized distribution), which should be enough
     * for any kind of program, and keeps the algorithm fast and
     * memory-efficient.
     * <p>
     * Attempting to add more than those OIDs will result in a
     * GIT_ERROR_INVALID error
     *
     * @param shortenerId the OID shortener.
     * @param textId      an OID in text form.
     * @return the minimal length to uniquely identify all OIDs
     *         added so far to the set.
     */
    public static int gitOidShortenAdd(GitOidShorten shortenerId, String textId) {
        Arena arena = Arena.ofAuto();
        MemorySegment textIdSegment = arena.allocateFrom(textId);
        int ret = git_oid_shorten_add(shortenerId.segment(), textIdSegment);
        if (ret < 0) {
            throw new RuntimeException("Failed to add OID to shortener: " + getGitErrorMessage());
        }
        return ret;
    }
}
