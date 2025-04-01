package com.sernamar.jgit2;

import com.sernamar.jgit2.bindings.git_signature;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_1.git_signature_free;

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
