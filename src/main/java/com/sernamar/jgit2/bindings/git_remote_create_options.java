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
 * struct git_remote_create_options {
 *     unsigned int version;
 *     git_repository *repository;
 *     const char *name;
 *     const char *fetchspec;
 *     unsigned int flags;
 * }
 * }
 */
public class git_remote_create_options {

    git_remote_create_options() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        git2.C_INT.withName("version"),
        MemoryLayout.paddingLayout(4),
        git2.C_POINTER.withName("repository"),
        git2.C_POINTER.withName("name"),
        git2.C_POINTER.withName("fetchspec"),
        git2.C_INT.withName("flags"),
        MemoryLayout.paddingLayout(4)
    ).withName("git_remote_create_options");

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

    private static final AddressLayout repository$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("repository"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * git_repository *repository
     * }
     */
    public static final AddressLayout repository$layout() {
        return repository$LAYOUT;
    }

    private static final long repository$OFFSET = 8;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * git_repository *repository
     * }
     */
    public static final long repository$offset() {
        return repository$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * git_repository *repository
     * }
     */
    public static MemorySegment repository(MemorySegment struct) {
        return struct.get(repository$LAYOUT, repository$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * git_repository *repository
     * }
     */
    public static void repository(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(repository$LAYOUT, repository$OFFSET, fieldValue);
    }

    private static final AddressLayout name$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("name"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * const char *name
     * }
     */
    public static final AddressLayout name$layout() {
        return name$LAYOUT;
    }

    private static final long name$OFFSET = 16;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * const char *name
     * }
     */
    public static final long name$offset() {
        return name$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * const char *name
     * }
     */
    public static MemorySegment name(MemorySegment struct) {
        return struct.get(name$LAYOUT, name$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * const char *name
     * }
     */
    public static void name(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(name$LAYOUT, name$OFFSET, fieldValue);
    }

    private static final AddressLayout fetchspec$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("fetchspec"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * const char *fetchspec
     * }
     */
    public static final AddressLayout fetchspec$layout() {
        return fetchspec$LAYOUT;
    }

    private static final long fetchspec$OFFSET = 24;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * const char *fetchspec
     * }
     */
    public static final long fetchspec$offset() {
        return fetchspec$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * const char *fetchspec
     * }
     */
    public static MemorySegment fetchspec(MemorySegment struct) {
        return struct.get(fetchspec$LAYOUT, fetchspec$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * const char *fetchspec
     * }
     */
    public static void fetchspec(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(fetchspec$LAYOUT, fetchspec$OFFSET, fieldValue);
    }

    private static final OfInt flags$LAYOUT = (OfInt)$LAYOUT.select(groupElement("flags"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * unsigned int flags
     * }
     */
    public static final OfInt flags$layout() {
        return flags$LAYOUT;
    }

    private static final long flags$OFFSET = 32;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * unsigned int flags
     * }
     */
    public static final long flags$offset() {
        return flags$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * unsigned int flags
     * }
     */
    public static int flags(MemorySegment struct) {
        return struct.get(flags$LAYOUT, flags$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * unsigned int flags
     * }
     */
    public static void flags(MemorySegment struct, int fieldValue) {
        struct.set(flags$LAYOUT, flags$OFFSET, fieldValue);
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

