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
 * struct git_writestream {
 *     int (*write)(git_writestream *, const char *, size_t);
 *     int (*close)(git_writestream *);
 *     void (*free)(git_writestream *);
 * }
 * }
 */
public class git_writestream {

    git_writestream() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        git2.C_POINTER.withName("write"),
        git2.C_POINTER.withName("close"),
        git2.C_POINTER.withName("free")
    ).withName("git_writestream");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    /**
     * {@snippet lang=c :
     * int (*write)(git_writestream *, const char *, size_t)
     * }
     */
    public static class write {

        write() {
            // Should not be called directly
        }

        /**
         * The function pointer signature, expressed as a functional interface
         */
        public interface Function {
            int apply(MemorySegment _x0, MemorySegment _x1, long _x2);
        }

        private static final FunctionDescriptor $DESC = FunctionDescriptor.of(
            git2.C_INT,
            git2.C_POINTER,
            git2.C_POINTER,
            git2.C_LONG
        );

        /**
         * The descriptor of this function pointer
         */
        public static FunctionDescriptor descriptor() {
            return $DESC;
        }

        private static final MethodHandle UP$MH = git2.upcallHandle(write.Function.class, "apply", $DESC);

        /**
         * Allocates a new upcall stub, whose implementation is defined by {@code fi}.
         * The lifetime of the returned segment is managed by {@code arena}
         */
        public static MemorySegment allocate(write.Function fi, Arena arena) {
            return Linker.nativeLinker().upcallStub(UP$MH.bindTo(fi), $DESC, arena);
        }

        private static final MethodHandle DOWN$MH = Linker.nativeLinker().downcallHandle($DESC);

        /**
         * Invoke the upcall stub {@code funcPtr}, with given parameters
         */
        public static int invoke(MemorySegment funcPtr,MemorySegment _x0, MemorySegment _x1, long _x2) {
            try {
                return (int) DOWN$MH.invokeExact(funcPtr, _x0, _x1, _x2);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        }
    }

    private static final AddressLayout write$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("write"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int (*write)(git_writestream *, const char *, size_t)
     * }
     */
    public static final AddressLayout write$layout() {
        return write$LAYOUT;
    }

    private static final long write$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int (*write)(git_writestream *, const char *, size_t)
     * }
     */
    public static final long write$offset() {
        return write$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int (*write)(git_writestream *, const char *, size_t)
     * }
     */
    public static MemorySegment write(MemorySegment struct) {
        return struct.get(write$LAYOUT, write$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int (*write)(git_writestream *, const char *, size_t)
     * }
     */
    public static void write(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(write$LAYOUT, write$OFFSET, fieldValue);
    }

    /**
     * {@snippet lang=c :
     * int (*close)(git_writestream *)
     * }
     */
    public static class close {

        close() {
            // Should not be called directly
        }

        /**
         * The function pointer signature, expressed as a functional interface
         */
        public interface Function {
            int apply(MemorySegment _x0);
        }

        private static final FunctionDescriptor $DESC = FunctionDescriptor.of(
            git2.C_INT,
            git2.C_POINTER
        );

        /**
         * The descriptor of this function pointer
         */
        public static FunctionDescriptor descriptor() {
            return $DESC;
        }

        private static final MethodHandle UP$MH = git2.upcallHandle(close.Function.class, "apply", $DESC);

        /**
         * Allocates a new upcall stub, whose implementation is defined by {@code fi}.
         * The lifetime of the returned segment is managed by {@code arena}
         */
        public static MemorySegment allocate(close.Function fi, Arena arena) {
            return Linker.nativeLinker().upcallStub(UP$MH.bindTo(fi), $DESC, arena);
        }

        private static final MethodHandle DOWN$MH = Linker.nativeLinker().downcallHandle($DESC);

        /**
         * Invoke the upcall stub {@code funcPtr}, with given parameters
         */
        public static int invoke(MemorySegment funcPtr,MemorySegment _x0) {
            try {
                return (int) DOWN$MH.invokeExact(funcPtr, _x0);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        }
    }

    private static final AddressLayout close$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("close"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int (*close)(git_writestream *)
     * }
     */
    public static final AddressLayout close$layout() {
        return close$LAYOUT;
    }

    private static final long close$OFFSET = 8;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int (*close)(git_writestream *)
     * }
     */
    public static final long close$offset() {
        return close$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int (*close)(git_writestream *)
     * }
     */
    public static MemorySegment close(MemorySegment struct) {
        return struct.get(close$LAYOUT, close$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int (*close)(git_writestream *)
     * }
     */
    public static void close(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(close$LAYOUT, close$OFFSET, fieldValue);
    }

    /**
     * {@snippet lang=c :
     * void (*free)(git_writestream *)
     * }
     */
    public static class free {

        free() {
            // Should not be called directly
        }

        /**
         * The function pointer signature, expressed as a functional interface
         */
        public interface Function {
            void apply(MemorySegment _x0);
        }

        private static final FunctionDescriptor $DESC = FunctionDescriptor.ofVoid(
            git2.C_POINTER
        );

        /**
         * The descriptor of this function pointer
         */
        public static FunctionDescriptor descriptor() {
            return $DESC;
        }

        private static final MethodHandle UP$MH = git2.upcallHandle(free.Function.class, "apply", $DESC);

        /**
         * Allocates a new upcall stub, whose implementation is defined by {@code fi}.
         * The lifetime of the returned segment is managed by {@code arena}
         */
        public static MemorySegment allocate(free.Function fi, Arena arena) {
            return Linker.nativeLinker().upcallStub(UP$MH.bindTo(fi), $DESC, arena);
        }

        private static final MethodHandle DOWN$MH = Linker.nativeLinker().downcallHandle($DESC);

        /**
         * Invoke the upcall stub {@code funcPtr}, with given parameters
         */
        public static void invoke(MemorySegment funcPtr,MemorySegment _x0) {
            try {
                 DOWN$MH.invokeExact(funcPtr, _x0);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        }
    }

    private static final AddressLayout free$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("free"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * void (*free)(git_writestream *)
     * }
     */
    public static final AddressLayout free$layout() {
        return free$LAYOUT;
    }

    private static final long free$OFFSET = 16;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * void (*free)(git_writestream *)
     * }
     */
    public static final long free$offset() {
        return free$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * void (*free)(git_writestream *)
     * }
     */
    public static MemorySegment free(MemorySegment struct) {
        return struct.get(free$LAYOUT, free$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * void (*free)(git_writestream *)
     * }
     */
    public static void free(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(free$LAYOUT, free$OFFSET, fieldValue);
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

