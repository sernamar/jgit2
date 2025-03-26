package com.sernamar.jgit2;

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

    @Override
    public void close() {
        if (segment != MemorySegment.NULL) {
            git_signature_free(segment);
            segment = MemorySegment.NULL;
        }
    }
}
