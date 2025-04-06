package com.sernamar.jgit2;

import com.sernamar.jgit2.utils.OpaqueDataType;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_1.git_index_free;

public class GitIndex extends OpaqueDataType {

    GitIndex(MemorySegment segment, boolean owned) {
        super(segment, owned);
    }

    @Override
    protected void free(MemorySegment segment) {
        git_index_free(segment);
    }
}
