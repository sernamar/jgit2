package com.sernamar.jgit2;

import com.sernamar.jgit2.bindings.git_buf;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_2.git_buf_dispose;

// Doesn't extend the OpaqueDataType because this class is not an opaque data type.
// We keep a MemorySegment reference to the underlying data so we can free it when we are done with it.
public final class GitBuf implements AutoCloseable {
    private MemorySegment segment;
    private final boolean owned;

    GitBuf(MemorySegment segment, boolean owned) {
        this.segment = segment;
        this.owned = owned;
    }

    MemorySegment segment() {
        return segment;
    }

    public String contents() {
        return git_buf.ptr(segment).getString(0);
    }

    @Override
    public void close() {
        if (owned && segment != MemorySegment.NULL) {
            git_buf_dispose(segment);
            segment = MemorySegment.NULL;
        }
    }
}
