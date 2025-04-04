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
 * struct git_credential_ssh_custom {
 *     git_credential parent;
 *     char *username;
 *     char *publickey;
 *     size_t publickey_len;
 *     git_credential_sign_cb sign_callback;
 *     void *payload;
 * }
 * }
 */
public class git_credential_ssh_custom {

    git_credential_ssh_custom() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        git_credential.layout().withName("parent"),
        git2.C_POINTER.withName("username"),
        git2.C_POINTER.withName("publickey"),
        git2.C_LONG.withName("publickey_len"),
        git2.C_POINTER.withName("sign_callback"),
        git2.C_POINTER.withName("payload")
    ).withName("git_credential_ssh_custom");

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

    private static final AddressLayout username$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("username"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * char *username
     * }
     */
    public static final AddressLayout username$layout() {
        return username$LAYOUT;
    }

    private static final long username$OFFSET = 16;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * char *username
     * }
     */
    public static final long username$offset() {
        return username$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * char *username
     * }
     */
    public static MemorySegment username(MemorySegment struct) {
        return struct.get(username$LAYOUT, username$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * char *username
     * }
     */
    public static void username(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(username$LAYOUT, username$OFFSET, fieldValue);
    }

    private static final AddressLayout publickey$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("publickey"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * char *publickey
     * }
     */
    public static final AddressLayout publickey$layout() {
        return publickey$LAYOUT;
    }

    private static final long publickey$OFFSET = 24;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * char *publickey
     * }
     */
    public static final long publickey$offset() {
        return publickey$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * char *publickey
     * }
     */
    public static MemorySegment publickey(MemorySegment struct) {
        return struct.get(publickey$LAYOUT, publickey$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * char *publickey
     * }
     */
    public static void publickey(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(publickey$LAYOUT, publickey$OFFSET, fieldValue);
    }

    private static final OfLong publickey_len$LAYOUT = (OfLong)$LAYOUT.select(groupElement("publickey_len"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * size_t publickey_len
     * }
     */
    public static final OfLong publickey_len$layout() {
        return publickey_len$LAYOUT;
    }

    private static final long publickey_len$OFFSET = 32;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * size_t publickey_len
     * }
     */
    public static final long publickey_len$offset() {
        return publickey_len$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * size_t publickey_len
     * }
     */
    public static long publickey_len(MemorySegment struct) {
        return struct.get(publickey_len$LAYOUT, publickey_len$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * size_t publickey_len
     * }
     */
    public static void publickey_len(MemorySegment struct, long fieldValue) {
        struct.set(publickey_len$LAYOUT, publickey_len$OFFSET, fieldValue);
    }

    private static final AddressLayout sign_callback$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("sign_callback"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * git_credential_sign_cb sign_callback
     * }
     */
    public static final AddressLayout sign_callback$layout() {
        return sign_callback$LAYOUT;
    }

    private static final long sign_callback$OFFSET = 40;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * git_credential_sign_cb sign_callback
     * }
     */
    public static final long sign_callback$offset() {
        return sign_callback$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * git_credential_sign_cb sign_callback
     * }
     */
    public static MemorySegment sign_callback(MemorySegment struct) {
        return struct.get(sign_callback$LAYOUT, sign_callback$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * git_credential_sign_cb sign_callback
     * }
     */
    public static void sign_callback(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(sign_callback$LAYOUT, sign_callback$OFFSET, fieldValue);
    }

    private static final AddressLayout payload$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("payload"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * void *payload
     * }
     */
    public static final AddressLayout payload$layout() {
        return payload$LAYOUT;
    }

    private static final long payload$OFFSET = 48;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * void *payload
     * }
     */
    public static final long payload$offset() {
        return payload$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * void *payload
     * }
     */
    public static MemorySegment payload(MemorySegment struct) {
        return struct.get(payload$LAYOUT, payload$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * void *payload
     * }
     */
    public static void payload(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(payload$LAYOUT, payload$OFFSET, fieldValue);
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

