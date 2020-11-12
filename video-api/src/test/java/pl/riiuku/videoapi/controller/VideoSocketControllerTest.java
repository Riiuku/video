package pl.riiuku.videoapi.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.messaging.converter.ByteArrayMessageConverter;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
    public void testConnection() throws InterruptedException, ExecutionException, TimeoutException {
        client.setMessageConverter(new ByteArrayMessageConverter());
        StompSession stomp = client
                .connect("ws://localhost:" + port + "/socket", new StompSessionHandlerAdapter() {})
                .get(1L, TimeUnit.SECONDS);




    }
}