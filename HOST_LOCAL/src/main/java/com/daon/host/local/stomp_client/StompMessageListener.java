package com.daon.host.local.stomp_client;

import com.daon.host.local.stomp.StompFrame;

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