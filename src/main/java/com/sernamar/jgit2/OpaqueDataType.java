package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

/*
 * Abstract class representing an opaque data type.
 *
 * A zero-length memory segment is used to represent a pointer to an opaque data structure used or allocated by a
 * foreign function. The segment consists of two values: the first is a valid pointer to the foreign memory region,
 * and the second is the size of the region. Since the Java runtime cannot determine the size of the region, it is
 * set to zero, which is why the segment is referred to as a "zero-length memory segment". This segment acts as a
 * placeholder that can later be used to reference the actual memory of the opaque structure. The size of the region
 * is implicitly determined by the native library, and the segment itself cannot be accessed directly. It can only be
 * passed to other foreign functions that accept pointers to such opaque structures.
 * See:
 * https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/lang/foreign/MemorySegment.html#wrapping-addresses
 *
 * This class implements AutoCloseable to ensure that memory management is properly handled, automatically freeing the
 * memory when the object is no longer needed. The close() method will invoke the free() method to release memory if
 * the object owns it.
 */
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
