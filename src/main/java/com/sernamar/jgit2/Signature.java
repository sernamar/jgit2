package com.sernamar.jgit2;

import com.sernamar.jgit2.bindings.git_signature;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_1.git_signature_now;
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
     * @param name  the name for the signature.
     * @return the signature.
     */
    public static GitSignature gitSignatureNow(String name, String email) {
        Arena arena = Arena.ofAuto();
        MemorySegment signatureSegment = git_signature.allocate(arena);
        MemorySegment nameSegment = arena.allocateFrom(name);
        MemorySegment emailSegment = arena.allocateFrom(email);
        int ret = git_signature_now(signatureSegment, nameSegment, emailSegment);
        if (ret < 0) {
            throw new RuntimeException("Failed to create signature: " + getGitErrorMessage());
        }
        return new GitSignature(signatureSegment);
    }
}
