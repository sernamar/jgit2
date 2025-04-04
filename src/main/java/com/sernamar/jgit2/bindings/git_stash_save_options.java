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
 * struct git_stash_save_options {
 *     unsigned int version;
 *     uint32_t flags;
 *     const git_signature *stasher;
 *     const char *message;
 *     git_strarray paths;
 * }
 * }
 */
public class git_stash_save_options {

    git_stash_save_options() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        git2.C_INT.withName("version"),
        git2.C_INT.withName("flags"),
        git2.C_POINTER.withName("stasher"),
        git2.C_POINTER.withName("message"),
        git_strarray.layout().withName("paths")
    ).withName("git_stash_save_options");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final OfInt version$LAYOUT = (OfInt)$LAYOUT.select(groupElement("version"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * unsigned int version
     * }
     */
    public static final OfInt version$layout() {
        return version$LAYOUT;
    }

    private static final long version$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * unsigned int version
     * }
     */
    public static final long version$offset() {
        return version$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * unsigned int version
     * }
     */
    public static int version(MemorySegment struct) {
        return struct.get(version$LAYOUT, version$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * unsigned int version
     * }
     */
    public static void version(MemorySegment struct, int fieldValue) {
        struct.set(version$LAYOUT, version$OFFSET, fieldValue);
    }

    private static final OfInt flags$LAYOUT = (OfInt)$LAYOUT.select(groupElement("flags"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * uint32_t flags
     * }
     */
    public static final OfInt flags$layout() {
        return flags$LAYOUT;
    }

    private static final long flags$OFFSET = 4;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * uint32_t flags
     * }
     */
    public static final long flags$offset() {
        return flags$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * uint32_t flags
     * }
     */
    public static int flags(MemorySegment struct) {
        return struct.get(flags$LAYOUT, flags$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * uint32_t flags
     * }
     */
    public static void flags(MemorySegment struct, int fieldValue) {
        struct.set(flags$LAYOUT, flags$OFFSET, fieldValue);
    }

    private static final AddressLayout stasher$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("stasher"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * const git_signature *stasher
     * }
     */
    public static final AddressLayout stasher$layout() {
        return stasher$LAYOUT;
    }

    private static final long stasher$OFFSET = 8;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * const git_signature *stasher
     * }
     */
    public static final long stasher$offset() {
        return stasher$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * const git_signature *stasher
     * }
     */
    public static MemorySegment stasher(MemorySegment struct) {
        return struct.get(stasher$LAYOUT, stasher$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * const git_signature *stasher
     * }
     */
    public static void stasher(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(stasher$LAYOUT, stasher$OFFSET, fieldValue);
    }

    private static final AddressLayout message$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("message"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * const char *message
     * }
     */
    public static final AddressLayout message$layout() {
        return message$LAYOUT;
    }

    private static final long message$OFFSET = 16;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * const char *message
     * }
     */
    public static final long message$offset() {
        return message$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * const char *message
     * }
     */
    public static MemorySegment message(MemorySegment struct) {
        return struct.get(message$LAYOUT, message$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * const char *message
     * }
     */
    public static void message(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(message$LAYOUT, message$OFFSET, fieldValue);
    }

    private static final GroupLayout paths$LAYOUT = (GroupLayout)$LAYOUT.select(groupElement("paths"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * git_strarray paths
     * }
     */
    public static final GroupLayout paths$layout() {
        return paths$LAYOUT;
    }

    private static final long paths$OFFSET = 24;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * git_strarray paths
     * }
     */
    public static final long paths$offset() {
        return paths$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * git_strarray paths
     * }
     */
    public static MemorySegment paths(MemorySegment struct) {
        return struct.asSlice(paths$OFFSET, paths$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * git_strarray paths
     * }
     */
    public static void paths(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, paths$OFFSET, paths$LAYOUT.byteSize());
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

