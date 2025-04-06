package com.sernamar.jgit2.utils;

import java.lang.foreign.MemorySegment;

public abstract class OpaqueDataType implements AutoCloseable {
    private MemorySegment segment; // zero-length memory segment
    private final boolean owned;

    protected OpaqueDataType(MemorySegment segment, boolean owned) {
        this.segment = segment;
        this.owned = owned;
    }

    public MemorySegment segment() {
        return segment;
    }

    protected abstract void free(MemorySegment segment);

    @Override
    public void close() {
        if (owned && segment != MemorySegment.NULL) {
            free(segment);
            segment = MemorySegment.NULL;
        }
    }
}
