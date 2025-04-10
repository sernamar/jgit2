package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_2.git_reference_free;

public final class GitReference extends OpaqueDataType {

    GitReference(MemorySegment segment, boolean owned) {
        super(segment, owned);
    }

    @Override
    void free(MemorySegment segment) {
        git_reference_free(segment);}
}
