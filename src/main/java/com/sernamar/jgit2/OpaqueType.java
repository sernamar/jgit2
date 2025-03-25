package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

abstract class OpaqueType {
    private final MemorySegment segment;

    protected OpaqueType(MemorySegment segment) {
        this.segment = segment;
    }

    MemorySegment segment() {
        return segment;
    }
}
