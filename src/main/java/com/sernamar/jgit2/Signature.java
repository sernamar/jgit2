package com.sernamar.jgit2;

import com.sernamar.jgit2.utils.GitException;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

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
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment signatureSegment = arena.allocate(C_POINTER);
            MemorySegment nameSegment = arena.allocateFrom(name);
            MemorySegment emailSegment = arena.allocateFrom(email);

            int ret = git_signature_now(signatureSegment, nameSegment, emailSegment);
            if (ret < 0) {
                throw new GitException("Failed to create signature: " + getGitErrorMessage());
            }
            return new GitSignature(signatureSegment.get(C_POINTER, 0), true);
        }
    }
}
