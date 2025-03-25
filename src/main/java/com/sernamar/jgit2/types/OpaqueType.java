package com.sernamar.jgit2.types;

import java.lang.foreign.MemorySegment;

abstract class OpaqueType {
    private final MemorySegment segment;

    protected OpaqueType(MemorySegment segment) {
        this.segment = segment;
    }

    public MemorySegment segment() {
        return segment;
    }
}
