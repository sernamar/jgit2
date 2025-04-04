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
 * struct git_indexer_progress {
 *     unsigned int total_objects;
 *     unsigned int indexed_objects;
 *     unsigned int received_objects;
 *     unsigned int local_objects;
 *     unsigned int total_deltas;
 *     unsigned int indexed_deltas;
 *     size_t received_bytes;
 * }
 * }
 */
public class git_indexer_progress {

    git_indexer_progress() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        git2.C_INT.withName("total_objects"),
        git2.C_INT.withName("indexed_objects"),
        git2.C_INT.withName("received_objects"),
        git2.C_INT.withName("local_objects"),
        git2.C_INT.withName("total_deltas"),
        git2.C_INT.withName("indexed_deltas"),
        git2.C_LONG.withName("received_bytes")
    ).withName("git_indexer_progress");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final OfInt total_objects$LAYOUT = (OfInt)$LAYOUT.select(groupElement("total_objects"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * unsigned int total_objects
     * }
     */
    public static final OfInt total_objects$layout() {
        return total_objects$LAYOUT;
    }

    private static final long total_objects$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * unsigned int total_objects
     * }
     */
    public static final long total_objects$offset() {
        return total_objects$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * unsigned int total_objects
     * }
     */
    public static int total_objects(MemorySegment struct) {
        return struct.get(total_objects$LAYOUT, total_objects$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * unsigned int total_objects
     * }
     */
    public static void total_objects(MemorySegment struct, int fieldValue) {
        struct.set(total_objects$LAYOUT, total_objects$OFFSET, fieldValue);
    }

    private static final OfInt indexed_objects$LAYOUT = (OfInt)$LAYOUT.select(groupElement("indexed_objects"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * unsigned int indexed_objects
     * }
     */
    public static final OfInt indexed_objects$layout() {
        return indexed_objects$LAYOUT;
    }

    private static final long indexed_objects$OFFSET = 4;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * unsigned int indexed_objects
     * }
     */
    public static final long indexed_objects$offset() {
        return indexed_objects$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * unsigned int indexed_objects
     * }
     */
    public static int indexed_objects(MemorySegment struct) {
        return struct.get(indexed_objects$LAYOUT, indexed_objects$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * unsigned int indexed_objects
     * }
     */
    public static void indexed_objects(MemorySegment struct, int fieldValue) {
        struct.set(indexed_objects$LAYOUT, indexed_objects$OFFSET, fieldValue);
    }

    private static final OfInt received_objects$LAYOUT = (OfInt)$LAYOUT.select(groupElement("received_objects"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * unsigned int received_objects
     * }
     */
    public static final OfInt received_objects$layout() {
        return received_objects$LAYOUT;
    }

    private static final long received_objects$OFFSET = 8;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * unsigned int received_objects
     * }
     */
    public static final long received_objects$offset() {
        return received_objects$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * unsigned int received_objects
     * }
     */
    public static int received_objects(MemorySegment struct) {
        return struct.get(received_objects$LAYOUT, received_objects$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * unsigned int received_objects
     * }
     */
    public static void received_objects(MemorySegment struct, int fieldValue) {
        struct.set(received_objects$LAYOUT, received_objects$OFFSET, fieldValue);
    }

    private static final OfInt local_objects$LAYOUT = (OfInt)$LAYOUT.select(groupElement("local_objects"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * unsigned int local_objects
     * }
     */
    public static final OfInt local_objects$layout() {
        return local_objects$LAYOUT;
    }

    private static final long local_objects$OFFSET = 12;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * unsigned int local_objects
     * }
     */
    public static final long local_objects$offset() {
        return local_objects$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * unsigned int local_objects
     * }
     */
    public static int local_objects(MemorySegment struct) {
        return struct.get(local_objects$LAYOUT, local_objects$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * unsigned int local_objects
     * }
     */
    public static void local_objects(MemorySegment struct, int fieldValue) {
        struct.set(local_objects$LAYOUT, local_objects$OFFSET, fieldValue);
    }

    private static final OfInt total_deltas$LAYOUT = (OfInt)$LAYOUT.select(groupElement("total_deltas"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * unsigned int total_deltas
     * }
     */
    public static final OfInt total_deltas$layout() {
        return total_deltas$LAYOUT;
    }

    private static final long total_deltas$OFFSET = 16;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * unsigned int total_deltas
     * }
     */
    public static final long total_deltas$offset() {
        return total_deltas$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * unsigned int total_deltas
     * }
     */
    public static int total_deltas(MemorySegment struct) {
        return struct.get(total_deltas$LAYOUT, total_deltas$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * unsigned int total_deltas
     * }
     */
    public static void total_deltas(MemorySegment struct, int fieldValue) {
        struct.set(total_deltas$LAYOUT, total_deltas$OFFSET, fieldValue);
    }

    private static final OfInt indexed_deltas$LAYOUT = (OfInt)$LAYOUT.select(groupElement("indexed_deltas"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * unsigned int indexed_deltas
     * }
     */
    public static final OfInt indexed_deltas$layout() {
        return indexed_deltas$LAYOUT;
    }

    private static final long indexed_deltas$OFFSET = 20;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * unsigned int indexed_deltas
     * }
     */
    public static final long indexed_deltas$offset() {
        return indexed_deltas$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * unsigned int indexed_deltas
     * }
     */
    public static int indexed_deltas(MemorySegment struct) {
        return struct.get(indexed_deltas$LAYOUT, indexed_deltas$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * unsigned int indexed_deltas
     * }
     */
    public static void indexed_deltas(MemorySegment struct, int fieldValue) {
        struct.set(indexed_deltas$LAYOUT, indexed_deltas$OFFSET, fieldValue);
    }

    private static final OfLong received_bytes$LAYOUT = (OfLong)$LAYOUT.select(groupElement("received_bytes"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * size_t received_bytes
     * }
     */
    public static final OfLong received_bytes$layout() {
        return received_bytes$LAYOUT;
    }

    private static final long received_bytes$OFFSET = 24;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * size_t received_bytes
     * }
     */
    public static final long received_bytes$offset() {
        return received_bytes$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * size_t received_bytes
     * }
     */
    public static long received_bytes(MemorySegment struct) {
        return struct.get(received_bytes$LAYOUT, received_bytes$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * size_t received_bytes
     * }
     */
    public static void received_bytes(MemorySegment struct, long fieldValue) {
        struct.set(received_bytes$LAYOUT, received_bytes$OFFSET, fieldValue);
    }

    /**
     * Obtains a slice of {@code arrayParam} which selects the array element at {@code index}.
     * The returned segment has address {@code arrayParam.address() + index * layout().byteSize()}
     */
    public static MemorySegment asSlice(MemorySegment array, long index) {
        return array.asSlice(layout().byteSize() * index);
    }

    /**
     * The size (in bytes) of this struct
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

