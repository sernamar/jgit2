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
 * struct {
 *     unsigned int version;
 *     uint32_t flags;
 *     git_submodule_ignore_t ignore_submodules;
 *     git_strarray pathspec;
 *     git_diff_notify_cb notify_cb;
 *     git_diff_progress_cb progress_cb;
 *     void *payload;
 *     uint32_t context_lines;
 *     uint32_t interhunk_lines;
 *     git_oid_t oid_type;
 *     uint16_t id_abbrev;
 *     git_off_t max_size;
 *     const char *old_prefix;
 *     const char *new_prefix;
 * }
 * }
 */
public class git_diff_options {

    git_diff_options() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        git2.C_INT.withName("version"),
        git2.C_INT.withName("flags"),
        git2.C_INT.withName("ignore_submodules"),
        MemoryLayout.paddingLayout(4),
        git_strarray.layout().withName("pathspec"),
        git2.C_POINTER.withName("notify_cb"),
        git2.C_POINTER.withName("progress_cb"),
        git2.C_POINTER.withName("payload"),
        git2.C_INT.withName("context_lines"),
        git2.C_INT.withName("interhunk_lines"),
        git2.C_INT.withName("oid_type"),
        git2.C_SHORT.withName("id_abbrev"),
        MemoryLayout.paddingLayout(2),
        git2.C_LONG.withName("max_size"),
        git2.C_POINTER.withName("old_prefix"),
        git2.C_POINTER.withName("new_prefix")
    ).withName("$anon$383:9");

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

    private static final OfInt ignore_submodules$LAYOUT = (OfInt)$LAYOUT.select(groupElement("ignore_submodules"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * git_submodule_ignore_t ignore_submodules
     * }
     */
    public static final OfInt ignore_submodules$layout() {
        return ignore_submodules$LAYOUT;
    }

    private static final long ignore_submodules$OFFSET = 8;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * git_submodule_ignore_t ignore_submodules
     * }
     */
    public static final long ignore_submodules$offset() {
        return ignore_submodules$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * git_submodule_ignore_t ignore_submodules
     * }
     */
    public static int ignore_submodules(MemorySegment struct) {
        return struct.get(ignore_submodules$LAYOUT, ignore_submodules$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * git_submodule_ignore_t ignore_submodules
     * }
     */
    public static void ignore_submodules(MemorySegment struct, int fieldValue) {
        struct.set(ignore_submodules$LAYOUT, ignore_submodules$OFFSET, fieldValue);
    }

    private static final GroupLayout pathspec$LAYOUT = (GroupLayout)$LAYOUT.select(groupElement("pathspec"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * git_strarray pathspec
     * }
     */
    public static final GroupLayout pathspec$layout() {
        return pathspec$LAYOUT;
    }

    private static final long pathspec$OFFSET = 16;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * git_strarray pathspec
     * }
     */
    public static final long pathspec$offset() {
        return pathspec$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * git_strarray pathspec
     * }
     */
    public static MemorySegment pathspec(MemorySegment struct) {
        return struct.asSlice(pathspec$OFFSET, pathspec$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * git_strarray pathspec
     * }
     */
    public static void pathspec(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, pathspec$OFFSET, pathspec$LAYOUT.byteSize());
    }

    private static final AddressLayout notify_cb$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("notify_cb"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * git_diff_notify_cb notify_cb
     * }
     */
    public static final AddressLayout notify_cb$layout() {
        return notify_cb$LAYOUT;
    }

    private static final long notify_cb$OFFSET = 32;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * git_diff_notify_cb notify_cb
     * }
     */
    public static final long notify_cb$offset() {
        return notify_cb$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * git_diff_notify_cb notify_cb
     * }
     */
    public static MemorySegment notify_cb(MemorySegment struct) {
        return struct.get(notify_cb$LAYOUT, notify_cb$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * git_diff_notify_cb notify_cb
     * }
     */
    public static void notify_cb(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(notify_cb$LAYOUT, notify_cb$OFFSET, fieldValue);
    }

    private static final AddressLayout progress_cb$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("progress_cb"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * git_diff_progress_cb progress_cb
     * }
     */
    public static final AddressLayout progress_cb$layout() {
        return progress_cb$LAYOUT;
    }

    private static final long progress_cb$OFFSET = 40;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * git_diff_progress_cb progress_cb
     * }
     */
    public static final long progress_cb$offset() {
        return progress_cb$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * git_diff_progress_cb progress_cb
     * }
     */
    public static MemorySegment progress_cb(MemorySegment struct) {
        return struct.get(progress_cb$LAYOUT, progress_cb$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * git_diff_progress_cb progress_cb
     * }
     */
    public static void progress_cb(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(progress_cb$LAYOUT, progress_cb$OFFSET, fieldValue);
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

    private static final OfInt context_lines$LAYOUT = (OfInt)$LAYOUT.select(groupElement("context_lines"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * uint32_t context_lines
     * }
     */
    public static final OfInt context_lines$layout() {
        return context_lines$LAYOUT;
    }

    private static final long context_lines$OFFSET = 56;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * uint32_t context_lines
     * }
     */
    public static final long context_lines$offset() {
        return context_lines$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * uint32_t context_lines
     * }
     */
    public static int context_lines(MemorySegment struct) {
        return struct.get(context_lines$LAYOUT, context_lines$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * uint32_t context_lines
     * }
     */
    public static void context_lines(MemorySegment struct, int fieldValue) {
        struct.set(context_lines$LAYOUT, context_lines$OFFSET, fieldValue);
    }

    private static final OfInt interhunk_lines$LAYOUT = (OfInt)$LAYOUT.select(groupElement("interhunk_lines"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * uint32_t interhunk_lines
     * }
     */
    public static final OfInt interhunk_lines$layout() {
        return interhunk_lines$LAYOUT;
    }

    private static final long interhunk_lines$OFFSET = 60;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * uint32_t interhunk_lines
     * }
     */
    public static final long interhunk_lines$offset() {
        return interhunk_lines$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * uint32_t interhunk_lines
     * }
     */
    public static int interhunk_lines(MemorySegment struct) {
        return struct.get(interhunk_lines$LAYOUT, interhunk_lines$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * uint32_t interhunk_lines
     * }
     */
    public static void interhunk_lines(MemorySegment struct, int fieldValue) {
        struct.set(interhunk_lines$LAYOUT, interhunk_lines$OFFSET, fieldValue);
    }

    private static final OfInt oid_type$LAYOUT = (OfInt)$LAYOUT.select(groupElement("oid_type"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * git_oid_t oid_type
     * }
     */
    public static final OfInt oid_type$layout() {
        return oid_type$LAYOUT;
    }

    private static final long oid_type$OFFSET = 64;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * git_oid_t oid_type
     * }
     */
    public static final long oid_type$offset() {
        return oid_type$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * git_oid_t oid_type
     * }
     */
    public static int oid_type(MemorySegment struct) {
        return struct.get(oid_type$LAYOUT, oid_type$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * git_oid_t oid_type
     * }
     */
    public static void oid_type(MemorySegment struct, int fieldValue) {
        struct.set(oid_type$LAYOUT, oid_type$OFFSET, fieldValue);
    }

    private static final OfShort id_abbrev$LAYOUT = (OfShort)$LAYOUT.select(groupElement("id_abbrev"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * uint16_t id_abbrev
     * }
     */
    public static final OfShort id_abbrev$layout() {
        return id_abbrev$LAYOUT;
    }

    private static final long id_abbrev$OFFSET = 68;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * uint16_t id_abbrev
     * }
     */
    public static final long id_abbrev$offset() {
        return id_abbrev$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * uint16_t id_abbrev
     * }
     */
    public static short id_abbrev(MemorySegment struct) {
        return struct.get(id_abbrev$LAYOUT, id_abbrev$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * uint16_t id_abbrev
     * }
     */
    public static void id_abbrev(MemorySegment struct, short fieldValue) {
        struct.set(id_abbrev$LAYOUT, id_abbrev$OFFSET, fieldValue);
    }

    private static final OfLong max_size$LAYOUT = (OfLong)$LAYOUT.select(groupElement("max_size"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * git_off_t max_size
     * }
     */
    public static final OfLong max_size$layout() {
        return max_size$LAYOUT;
    }

    private static final long max_size$OFFSET = 72;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * git_off_t max_size
     * }
     */
    public static final long max_size$offset() {
        return max_size$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * git_off_t max_size
     * }
     */
    public static long max_size(MemorySegment struct) {
        return struct.get(max_size$LAYOUT, max_size$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * git_off_t max_size
     * }
     */
    public static void max_size(MemorySegment struct, long fieldValue) {
        struct.set(max_size$LAYOUT, max_size$OFFSET, fieldValue);
    }

    private static final AddressLayout old_prefix$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("old_prefix"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * const char *old_prefix
     * }
     */
    public static final AddressLayout old_prefix$layout() {
        return old_prefix$LAYOUT;
    }

    private static final long old_prefix$OFFSET = 80;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * const char *old_prefix
     * }
     */
    public static final long old_prefix$offset() {
        return old_prefix$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * const char *old_prefix
     * }
     */
    public static MemorySegment old_prefix(MemorySegment struct) {
        return struct.get(old_prefix$LAYOUT, old_prefix$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * const char *old_prefix
     * }
     */
    public static void old_prefix(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(old_prefix$LAYOUT, old_prefix$OFFSET, fieldValue);
    }

    private static final AddressLayout new_prefix$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("new_prefix"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * const char *new_prefix
     * }
     */
    public static final AddressLayout new_prefix$layout() {
        return new_prefix$LAYOUT;
    }

    private static final long new_prefix$OFFSET = 88;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * const char *new_prefix
     * }
     */
    public static final long new_prefix$offset() {
        return new_prefix$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * const char *new_prefix
     * }
     */
    public static MemorySegment new_prefix(MemorySegment struct) {
        return struct.get(new_prefix$LAYOUT, new_prefix$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * const char *new_prefix
     * }
     */
    public static void new_prefix(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(new_prefix$LAYOUT, new_prefix$OFFSET, fieldValue);
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

