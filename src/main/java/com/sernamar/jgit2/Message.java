package com.sernamar.jgit2;

import com.sernamar.jgit2.bindings.git_buf;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_1.git_message_prettify;
import static com.sernamar.jgit2.bindings.git2_2.git_buf_dispose;
import static com.sernamar.jgit2.utils.GitError.getGitErrorMessage;

public final class Message {

    private Message() {
        // Prevent instantiation
    }

    /**
     * Clean up excess whitespace, make sure there is a trailing newline in the message,
     * and remove lines which start with the character `#`.
     *
     * @param message the message to be prettified.
     * @return the prettified message.
     */
    public static String gitMessagePrettify(String message) {
        return gitMessagePrettify(message, true, '#');
    }

    /**
     * Clean up excess whitespace and make sure there is a trailing newline in the message.
     * <p>
     * Optionally, it can remove lines which start with the comment character.
     *
     * @param message       the message to be prettified.
     * @param stripComments `true` to remove comment lines, `false` to leave them in.

     * @return the prettified message.
     */
    public static String gitMessagePrettify(String message, boolean stripComments) {
        return gitMessagePrettify(message, stripComments, '#');
    }

    /**
     * Clean up excess whitespace and make sure there is a trailing newline in the message.
     * <p>
     * Optionally, it can remove lines which start with the comment character.
     *
     * @param message       the message to be prettified.
     * @param stripComments `true` to remove comment lines, `false` to leave them in.
     * @param commentChar   comment character. Lines starting with this character are considered
     *                      to be comments and removed if `strip_comments` is non-zero.
     * @return the prettified message.
     */
    public static String gitMessagePrettify(String message, boolean stripComments, char commentChar) {
        Arena arena = Arena.ofAuto();
        MemorySegment outSegment = git_buf.allocate(arena);
        MemorySegment messageSegment = arena.allocateFrom(message);
        int ret = git_message_prettify(outSegment, messageSegment, stripComments ? 1 : 0, (byte) commentChar);
        if (ret < 0) {
            throw new RuntimeException("Failed to prettify message: " + getGitErrorMessage());
        }
        String prettifiedMessage = git_buf.ptr(outSegment).getString(0);
        // De-allocate native (off-heap) memory here, so there's no need to create a `AutoCloseable` `GitBuf` class
        // for now and de-allocate the memory in the `close` method.
        git_buf_dispose(outSegment);
        return prettifiedMessage;
    }
}
