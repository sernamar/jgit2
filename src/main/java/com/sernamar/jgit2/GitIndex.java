package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_1.git_index_free;

public class GitIndex implements AutoCloseable {
    private MemorySegment segment;

    GitIndex(MemorySegment segment) {
        this.segment = segment;
    }

    MemorySegment segment() {
        return segment;
    }

    @Override
    public void close() {
        if (segment != MemorySegment.NULL) {
            git_index_free(segment);
            segment = MemorySegment.NULL;
        }
    }
}
