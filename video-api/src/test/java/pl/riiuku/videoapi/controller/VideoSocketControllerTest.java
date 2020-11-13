package pl.riiuku.videoapi.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.messaging.converter.ByteArrayMessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VideoSocketControllerTest {

    WebSocketStompClient client =
            new WebSocketStompClient(
                    new SockJsClient(Collections.singletonList(
                            new WebSocketTransport(
                                    new StandardWebSocketClient()))));


    @LocalServerPort
    private int port;

    @Test
    public void sendVideoBytes() throws InterruptedException, ExecutionException, TimeoutException {

        BlockingQueue<String> values = new ArrayBlockingQueue<>(1);

        client.setMessageConverter(new StringMessageConverter());
        StompSession stomp = client
                .connect("ws://localhost:" + port + "/socket", new StompSessionHandlerAdapter() {
                })
                .get(1L, TimeUnit.SECONDS);


        stomp.subscribe("/topic/video-call", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return String.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                values.add((String) payload);
            }
        });

        stomp.send("/app/video-call", "Hello");

        assertEquals("Hello, String test", values.poll(2, TimeUnit.SECONDS));
    }
}