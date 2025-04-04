// Generated by jextract

package com.sernamar.jgit2.bindings;

import java.lang.invoke.*;
import java.lang.foreign.*;
import java.nio.ByteOrder;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.lang.foreign.ValueLayout.*;
import static java.lang.foreign.MemoryLayout.PathElement.*;

/**
 * {@snippet lang=c :
 * union {
 *     char __size[4];
 *     int __align;
 * }
 * }
 */
public class pthread_barrierattr_t {

    pthread_barrierattr_t() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.unionLayout(
        MemoryLayout.sequenceLayout(4, git2.C_CHAR).withName("__size"),
        git2.C_INT.withName("__align")
    ).withName("$anon$114:9");

    /**
     * The layout of this union
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final SequenceLayout __size$LAYOUT = (SequenceLayout)$LAYOUT.select(groupElement("__size"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * char __size[4]
     * }
     */
    public static final SequenceLayout __size$layout() {
        return __size$LAYOUT;
    }

    private static final long __size$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * char __size[4]
     * }
     */
    public static final long __size$offset() {
        return __size$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * char __size[4]
     * }
     */
    public static MemorySegment __size(MemorySegment union) {
        return union.asSlice(__size$OFFSET, __size$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * char __size[4]
     * }
     */
    public static void __size(MemorySegment union, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, union, __size$OFFSET, __size$LAYOUT.byteSize());
    }

    private static long[] __size$DIMS = { 4 };

    /**
     * Dimensions for array field:
     * {@snippet lang=c :
     * char __size[4]
     * }
     */
    public static long[] __size$dimensions() {
        return __size$DIMS;
    }
    private static final VarHandle __size$ELEM_HANDLE = __size$LAYOUT.varHandle(sequenceElement());

    /**
     * Indexed getter for field:
     * {@snippet lang=c :
     * char __size[4]
     * }
     */
    public static byte __size(MemorySegment union, long index0) {
        return (byte)__size$ELEM_HANDLE.get(union, 0L, index0);
    }

    /**
     * Indexed setter for field:
     * {@snippet lang=c :
     * char __size[4]
     * }
     */
    public static void __size(MemorySegment union, long index0, byte fieldValue) {
        __size$ELEM_HANDLE.set(union, 0L, index0, fieldValue);
    }

    private static final OfInt __align$LAYOUT = (OfInt)$LAYOUT.select(groupElement("__align"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int __align
     * }
     */
    public static final OfInt __align$layout() {
        return __align$LAYOUT;
    }

    private static final long __align$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int __align
     * }
     */
    public static final long __align$offset() {
        return __align$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int __align
     * }
     */
    public static int __align(MemorySegment union) {
        return union.get(__align$LAYOUT, __align$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int __align
     * }
     */
    public static void __align(MemorySegment union, int fieldValue) {
        union.set(__align$LAYOUT, __align$OFFSET, fieldValue);
    }

    /**
     * Obtains a slice of {@code arrayParam} which selects the array element at {@code index}.
     * The returned segment has address {@code arrayParam.address() + index * layout().byteSize()}
     */
    public static MemorySegment asSlice(MemorySegment array, long index) {
        return array.asSlice(layout().byteSize() * index);
    }

    /**
     * The size (in bytes) of this union
     */
    public static long sizeof() { return layout().byteSize(); }

    /**
     * Allocate a segment of size {@code layout().byteSize()} using {@code allocator}
     */
    public static MemorySegment allocate(SegmentAllocator allocator) {
        return allocator.allocate(layout());
    }

    /**
     * Allocate an array of size {@code elementCount} using {@code allocator}.
     * The returned segment has size {@code elementCount * layout().byteSize()}.
     */
    public static MemorySegment allocateArray(long elementCount, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout()));
    }

    /**
     * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction} (if any).
     * The returned segment has size {@code layout().byteSize()}
     */
    public static MemorySegment reinterpret(MemorySegment addr, Arena arena, Consumer<MemorySegment> cleanup) {
        return reinterpret(addr, 1, arena, cleanup);
    }

    /**
     * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction} (if any).
     * The returned segment has size {@code elementCount * layout().byteSize()}
     */
    public static MemorySegment reinterpret(MemorySegment addr, long elementCount, Arena arena, Consumer<MemorySegment> cleanup) {
        return addr.reinterpret(layout().byteSize() * elementCount, arena, cleanup);
    }
}

