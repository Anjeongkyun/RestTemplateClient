package com.daon.host.ws.stomp_client;

import com.daon.host.ws.stomp.StompFrame;

/**
 * STOMP message subscription listener interface.
 */
public interface StompMessageListener {

    /**
     * Subscription message received callback.
     *
     * @param stompFrame STOMP message frame
     */
    void onMessage(StompFrame stompFrame);
}