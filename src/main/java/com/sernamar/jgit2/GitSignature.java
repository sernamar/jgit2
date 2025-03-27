package com.sernamar.jgit2;

import com.sernamar.jgit2.bindings.git_signature;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_1.git_signature_free;

public final class GitSignature implements AutoCloseable {
    private MemorySegment segment;

    GitSignature(MemorySegment segment) {
        this.segment = segment;
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
        if (segment != MemorySegment.NULL) {
            git_signature_free(segment);
            segment = MemorySegment.NULL;
        }
    }
}
