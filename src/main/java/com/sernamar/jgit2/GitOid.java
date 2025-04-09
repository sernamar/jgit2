package com.sernamar.jgit2;

import com.sernamar.jgit2.bindings.git_oid;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HexFormat;

public final class GitOid {
    private final byte[] id;
    public static final int LENGTH = (int) git_oid.sizeof();

    public GitOid(byte[] id) {
        if (id == null || id.length != LENGTH) {
            throw new IllegalArgumentException("Invalid OID length");
        }
        this.id = Arrays.copyOf(id, LENGTH);
    }

    public GitOid(MemorySegment segment) {
        if (segment == null || segment.byteSize() != LENGTH) {
            throw new IllegalArgumentException("Invalid OID segment");
        }
        // Copy the segment to a byte array
        MemorySegment idSegment = git_oid.id(segment);
        ByteBuffer idBuffer = idSegment.asByteBuffer();

        // Transfer the bytes to the id array
        this.id = new byte[LENGTH];
        idBuffer.get(id);
    }

    MemorySegment toSegment(SegmentAllocator allocator) {
        MemorySegment oidSegment = git_oid.allocate(allocator);
        MemorySegment idSegment = git_oid.id(oidSegment);
        idSegment.copyFrom(MemorySegment.ofArray(id));
        return oidSegment;
    }

    private String toHexString() {
        return HexFormat.of().formatHex(id);
    }

    @Override
    public String toString() {
        return toHexString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof GitOid other)) return false;
        return Arrays.equals(this.id, other.id);
    }
}
