package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

abstract class OpaqueDataType implements AutoCloseable {
    private MemorySegment segment; // zero-length memory segment
    private final boolean owned;

    OpaqueDataType(MemorySegment segment, boolean owned) {
        this.segment = segment;
        this.owned = owned;
    }

    MemorySegment segment() {
        return segment;
    }

    abstract void free(MemorySegment segment);

    @Override
    public void close() {
        if (owned && segment != MemorySegment.NULL) {
            free(segment);
            segment = MemorySegment.NULL;
        }
    }
}
