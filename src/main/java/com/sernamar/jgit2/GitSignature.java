package com.sernamar.jgit2;

import com.sernamar.jgit2.bindings.git_signature;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_1.git_signature_free;
/*
`GitSignature` objects can be created in two ways:
1. Using functions like `gitSignatureNew`, `gitSignatureNow`, or `gitSignatureDefault`, that create signatures that
 are owned by the caller.
2. Using functions like `gitCommitAuthor`, `gitCommitCommiter`, `gitSignatureLookup`, etc., that return signatures that
 are not owned by the caller.
The `owned` field indicates whether the `GitSignature` object is owned by the caller or not.
Only `GitSignature` objects that are owned by the caller should be freed using `git_signature_free`, which is called in the
`close` method.
 */
public final class GitSignature implements AutoCloseable {
    private MemorySegment segment;
    private final boolean owned;

    GitSignature(MemorySegment segment, boolean owned) {
        this.segment = segment;
        this.owned = owned;
    }

    MemorySegment segment() {
        return segment;
    }

    public String name() {
        return git_signature.name(segment).getString(0);
    }

    public String email() {
        return git_signature.email(segment).getString(0);
    }

    @Override
    public void close() {
        if (owned && segment != MemorySegment.NULL) {
            git_signature_free(segment);
            segment = MemorySegment.NULL;
        }
    }
}
