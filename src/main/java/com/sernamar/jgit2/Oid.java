package com.sernamar.jgit2;

import com.sernamar.jgit2.bindings.git_oid;
import com.sernamar.jgit2.utils.GitException;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2.GIT_OID_SHA1_HEXSIZE;
import static com.sernamar.jgit2.bindings.git2_1.GIT_ERROR_INVALID;
import static com.sernamar.jgit2.bindings.git2_2.*;
import static com.sernamar.jgit2.utils.GitError.getGitErrorMessage;

public final class Oid {

    private Oid() {
        // Prevent instantiation
    }

    /**
     * Parse a hex formatted object id into a GitOid object.
     * <p>
     * The appropriate number of bytes for the given object ID type will
     * be read from the string - 40 bytes for SHA1, 64 bytes for SHA256.
     *
     * @param string input hex string; must have at least the number of bytes
     *               needed for an oid encoded in hex (40 bytes for sha1,
     *               256 bytes for sha256).
     * @return the OID.
     * @throws GitException if the conversion fails.
     */
    public static GitOid gitOidFromString(String string) throws GitException {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment oidSegment = git_oid.allocate(arena);
            MemorySegment stringSegment = arena.allocateFrom(string);

            int ret = git_oid_fromstr(oidSegment, stringSegment);
            if (ret < 0) {
                throw new GitException("Failed to convert string to OID: " + getGitErrorMessage());
            }
            return new GitOid(oidSegment);
        }
    }

    /**
     * Format a GitOid object into a hex format String.
     * <p>
     * If the buffer is smaller than the size of a hex-formatted oid string
     * plus an additional byte (GIT_OID_SHA_HEXSIZE + 1 for SHA1 or
     * GIT_OID_SHA256_HEXSIZE + 1 for SHA256), then the function will
     * throw an IllegalArgumentException.
     * <p>
     * If there are any input parameter errors (out == null, n == 0,
     * oid == null), then an empty string is returned, so that the
     * return value can always be printed.
     *
     * @param id     the oid structure to format.
     * @return the formatted string.
     * @throws IllegalArgumentException if the buffer size is too small.
     */
    public static String gitOidToString(GitOid id) {
        // TODO: Support SHA256 (see: https://libgit2.org/docs/reference/main/common/git_feature_t.html)
        long defaultLength = GIT_OID_SHA1_HEXSIZE() + 1;
        return gitOidToString(id, defaultLength);
    }

    /**
     * Format a GitOid object into a hex format String.
     * <p>
     * If the buffer is smaller than the size of a hex-formatted oid string
     * plus an additional byte (GIT_OID_SHA_HEXSIZE + 1 for SHA1 or
     * GIT_OID_SHA256_HEXSIZE + 1 for SHA256), then the function will
     * throw an IllegalArgumentException.
     * <p>
     * If there are any input parameter errors (out == null, n == 0,
     * oid == null), then an empty string is returned, so that the
     * return value can always be printed.
     *
     * @param id     the oid structure to format.
     * @param length the size of the out buffer.
     * @return the formatted string.
     * @throws IllegalArgumentException if the buffer size is too small.
     */
    public static String gitOidToString(GitOid id, long length) {
        // TODO: Support SHA256 (see: https://libgit2.org/docs/reference/main/common/git_feature_t.html)
        long requiredLength = GIT_OID_SHA1_HEXSIZE() + 1;
        if (length < requiredLength) {
            throw new IllegalArgumentException("Buffer size is too small");
        }
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment stringSegment = arena.allocate(length);
            MemorySegment oidSegment = id.toSegment(arena);

            MemorySegment ret = git_oid_tostr(stringSegment, length, oidSegment);
            return ret.getString(0);
        }
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
        return new GitOidShorten(git_oid_shorten_new(minLength), true);
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
     * @throws GitException if the addition fails.
     */
    public static int gitOidShortenAdd(GitOidShorten shortenerId, String textId) throws GitException {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment shortenerIdSegment = shortenerId.segment();
            MemorySegment textIdSegment = arena.allocateFrom(textId);

            int ret = git_oid_shorten_add(shortenerIdSegment, textIdSegment);
            if (ret == GIT_ERROR_INVALID()) {
                throw new GitException("Attempting to add more than the maximum number of OIDs: " + getGitErrorMessage());
            } else if (ret < 0) {
                throw new GitException("Failed to add OID to shortener: " + getGitErrorMessage());
            }
            return ret;
        }
    }
}
