package com.sernamar.jgit2;

import com.sernamar.jgit2.bindings.git_signature;
import com.sernamar.jgit2.bindings.git_time;
import com.sernamar.jgit2.utils.GitException;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static com.sernamar.jgit2.bindings.git2_1.git_signature_now;
import static com.sernamar.jgit2.bindings.git2_2.C_POINTER;
import static com.sernamar.jgit2.utils.GitError.getGitErrorMessage;

public final class Signature {

    private Signature() {
        // Prevent instantiation
    }

    /**
     * Create a new action signature with a timestamp of 'now'.
     * <p>
     * Call `git_signature_free()` to free the data.
     * <p>
     * Note: angle brackets ('<' and '>') characters are not allowed
     * to be used in either the `name` or the `email` parameter.
     *
     * @param name  name of the person.
     * @param email email of the person.
     * @return the signature.
     * @throws GitException if the signature could not be created.
     */
    public static GitSignature gitSignatureNow(String name, String email) throws GitException {
        Arena arena = Arena.ofAuto();
        MemorySegment signatureSegment = arena.allocate(C_POINTER);
        MemorySegment nameSegment = arena.allocateFrom(name);
        MemorySegment emailSegment = arena.allocateFrom(email);
        int ret = git_signature_now(signatureSegment, nameSegment, emailSegment);
        if (ret < 0) {
            throw new GitException("Failed to create signature: " + getGitErrorMessage());
        }
        return new GitSignature(signatureSegment.get(C_POINTER, 0), true);
    }

    /**
     * Get the full name of the author.
     *
     * @param signature the signature.
     * @return the full name of the author.
     */
    public static String gitSignatureName(GitSignature signature) {
        return git_signature.name(signature.segment()).getString(0);
    }

    /**
     * Get the email of the author.
     *
     * @param signature the signature.
     * @return the email of the author.
     */
    public static String gitSignatureEmail(GitSignature signature) {
        return git_signature.email(signature.segment()).getString(0);
    }

    /**
     * Get date of the signature.
     *
     * @param signature the signature.
     * @return the date of the signature.
     */
    public static OffsetDateTime gitSignatureTime(GitSignature signature) {
        MemorySegment when = git_signature.when(signature.segment());
        long time = git_time.time(when);
        int offset = git_time.offset(when);
        char sign = (char) git_time.sign(when);

        Instant instant = Instant.ofEpochSecond(time);
        int offsetMinutes = sign == '-' ? -offset : offset;
        ZoneOffset zoneOffset = ZoneOffset.ofTotalSeconds(offsetMinutes * 60);

        return OffsetDateTime.ofInstant(instant, zoneOffset);
    }
}
