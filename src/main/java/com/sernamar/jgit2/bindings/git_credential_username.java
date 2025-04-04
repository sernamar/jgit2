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
 * struct git_credential_username {
 *     git_credential parent;
 *     char username[1];
 * }
 * }
 */
public class git_credential_username {

    git_credential_username() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        git_credential.layout().withName("parent"),
        MemoryLayout.sequenceLayout(1, git2.C_CHAR).withName("username"),
        MemoryLayout.paddingLayout(7)
    ).withName("git_credential_username");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final GroupLayout parent$LAYOUT = (GroupLayout)$LAYOUT.select(groupElement("parent"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * git_credential parent
     * }
     */
    public static final GroupLayout parent$layout() {
        return parent$LAYOUT;
    }

    private static final long parent$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * git_credential parent
     * }
     */
    public static final long parent$offset() {
        return parent$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * git_credential parent
     * }
     */
    public static MemorySegment parent(MemorySegment struct) {
        return struct.asSlice(parent$OFFSET, parent$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * git_credential parent
     * }
     */
    public static void parent(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, parent$OFFSET, parent$LAYOUT.byteSize());
    }

    private static final SequenceLayout username$LAYOUT = (SequenceLayout)$LAYOUT.select(groupElement("username"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * char username[1]
     * }
     */
    public static final SequenceLayout username$layout() {
        return username$LAYOUT;
    }

    private static final long username$OFFSET = 16;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * char username[1]
     * }
     */
    public static final long username$offset() {
        return username$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * char username[1]
     * }
     */
    public static MemorySegment username(MemorySegment struct) {
        return struct.asSlice(username$OFFSET, username$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * char username[1]
     * }
     */
    public static void username(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, username$OFFSET, username$LAYOUT.byteSize());
    }

    private static long[] username$DIMS = { 1 };

    /**
     * Dimensions for array field:
     * {@snippet lang=c :
     * char username[1]
     * }
     */
    public static long[] username$dimensions() {
        return username$DIMS;
    }
    private static final VarHandle username$ELEM_HANDLE = username$LAYOUT.varHandle(sequenceElement());

    /**
     * Indexed getter for field:
     * {@snippet lang=c :
     * char username[1]
     * }
     */
    public static byte username(MemorySegment struct, long index0) {
        return (byte)username$ELEM_HANDLE.get(struct, 0L, index0);
    }

    /**
     * Indexed setter for field:
     * {@snippet lang=c :
     * char username[1]
     * }
     */
    public static void username(MemorySegment struct, long index0, byte fieldValue) {
        username$ELEM_HANDLE.set(struct, 0L, index0, fieldValue);
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

