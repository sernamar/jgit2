package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

public final class GitCommit extends OpaqueType {
    public GitCommit(MemorySegment segment) {
        super(segment);
    }
}
