package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

public final class GitRepository extends OpaqueType {
    public GitRepository(MemorySegment segment) {
        super(segment);
    }
}
