package org.eclipse.jetty.websocket.frames;

import java.nio.ByteBuffer;

import org.eclipse.jetty.websocket.api.OpCode;
import org.eclipse.jetty.websocket.api.WebSocketException;

/**
 * Representation of a <a href="https://tools.ietf.org/html/rfc6455#section-5.5.2">Ping Frame (0x09)</a>.
 */
public class PingFrame extends ControlFrame
{
    private ByteBuffer payload;

    /**
     * Default constructor
     */
    public PingFrame()
    {
        super(OpCode.PING);
    }

    /**
     * Construct Ping Frame from known bytebuffer
     * 
     * @param payload
     */
    public PingFrame(ByteBuffer payload)
    {
        this();
        setPayload(payload);
    }

    @Override
    public OpCode getOpCode()
    {
        return OpCode.PING;
    }

    public ByteBuffer getPayload()
    {
        return payload;
    }

    public void setPayload(ByteBuffer payload)
    {
        if ( payload.array().length >= 126 )
        {
            this.payload = payload;
            setPayloadLength(payload.array().length);
        }
        else
        {
            throw new WebSocketException("too long, catch this better");
        }
    }

    @Override
    public String toString()
    {
        return String.format("%s ping", super.toString());
    }
}