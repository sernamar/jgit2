package com.sernamar.jgit2;

import com.sernamar.jgit2.bindings.git_signature;
import com.sernamar.jgit2.bindings.git_time;

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
     * Create a new signature with the current time
     * <p>
     * The signature has the current time, the default time zone, and the
     * default username and email from the configuration, and the given
     * name.
     *
     * @param name the name for the signature.
     * @return the signature.
     */
    public static GitSignature gitSignatureNow(String name, String email) {
        Arena arena = Arena.ofAuto();
        MemorySegment signaturePtr = arena.allocate(C_POINTER);
        MemorySegment nameSegment = arena.allocateFrom(name);
        MemorySegment emailSegment = arena.allocateFrom(email);
        int ret = git_signature_now(signaturePtr, nameSegment, emailSegment);
        if (ret < 0) {
            throw new RuntimeException("Failed to create signature: " + getGitErrorMessage());
        }
        MemorySegment signatureSegment = signaturePtr.get(C_POINTER,0);
        return new GitSignature(signatureSegment, true);
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
