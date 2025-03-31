package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_1.git_revwalk_free;

public class GitRevwalk implements AutoCloseable {
    private MemorySegment segment;
    private final boolean owned;

    GitRevwalk(MemorySegment segment, boolean owned) {
        this.segment = segment;
        this.owned = owned;
    }

    MemorySegment segment() {
        return segment;
    }

    @Override
    public void close() {
        if (owned && segment != MemorySegment.NULL) {
            git_revwalk_free(segment);
            segment = MemorySegment.NULL;
        }
    }
}
