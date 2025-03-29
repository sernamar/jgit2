package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_1.git_index_free;

public class GitIndex implements AutoCloseable {
    private MemorySegment segment;
    private final boolean owned;

    GitIndex(MemorySegment segment, boolean owned) {
        this.segment = segment;
        this.owned = owned;
    }

    MemorySegment segment() {
        return segment;
    }

    @Override
    public void close() {
        if (owned && segment != MemorySegment.NULL) {
            git_index_free(segment);
            segment = MemorySegment.NULL;
        }
    }
}
