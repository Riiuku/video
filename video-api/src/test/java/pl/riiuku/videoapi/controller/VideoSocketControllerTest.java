package pl.riiuku.videoapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.messaging.converter.ByteArrayMessageConverter;
import org.springframework.messaging.converter.JsonbMessageConverter;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import pl.riiuku.videoapi.domain.VideoCallSocketMessage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VideoSocketControllerTest {

    WebSocketStompClient client =
            new WebSocketStompClient(new StandardWebSocketClient());


    @LocalServerPort
    private int port;

    @Test
    public void sendVideoBytes() throws InterruptedException, ExecutionException, TimeoutException, IOException {
        //given
        byte[] file = Files.readAllBytes(Paths.get("src/test/resources/static/test.jpg"));
        BlockingQueue<Byte> values = new ArrayBlockingQueue<>(1);
        client.setMessageConverter(new ByteArrayMessageConverter());
        StompSession stomp = client
                .connect("ws://localhost:" + port + "/socket", new StompSessionHandlerAdapter() {
                })
                .get(2L, TimeUnit.SECONDS);
        //when
        stomp.subscribe("/topic/video-call", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return Object.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                System.out.println("WCHODZI");
                values.add((Byte) payload);
            }
        });

        stomp.send("/app/video-call", file);

        //then
        assertEquals(file[1], values.poll(10L, TimeUnit.SECONDS));
    }
}