package org.example;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Message {
    private final int cType;      // command code
    private final int bUserId;    // used id
    private final long wLen;      // message length
    private final String message; // message

    Message(int command_code, int user_id, String message) {
        this.cType = command_code;
        this.bUserId = user_id;
        this.wLen = message.length();
        this.message = message;
    }

    public byte[] getBytes() {
        byte[] message_bytes = this.message.getBytes(StandardCharsets.UTF_8);
        byte[] byte_arr = new byte[Integer.BYTES * 2 + Long.BYTES + message_bytes.length];   // byte arr[cType + bUserId + wLen + message]

        ByteBuffer byteBuffer = ByteBuffer.wrap(byte_arr); // big-endian by default
        byteBuffer.putInt(this.cType).putInt(this.bUserId).putLong(this.wLen).put(message_bytes);      // putting cType, bUserId, wLen, message in as bytes

        return byte_arr;
    }

    public String getMessage() {
        return message;
    }
}