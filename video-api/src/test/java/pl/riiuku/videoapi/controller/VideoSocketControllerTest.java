package pl.riiuku.videoapi.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.messaging.converter.ByteArrayMessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VideoSocketControllerTest {

    private final WebSocketStompClient client =
            new WebSocketStompClient
                    (new StandardWebSocketClient(createContainer()));;


    @LocalServerPort
    private int port;

    @Test
    public void sendVideoBytes() throws InterruptedException, ExecutionException, TimeoutException, IOException {
        //given
        byte[] file = Files.readAllBytes(Paths.get("src/test/resources/static/test.jpg"));
        UUID roomId = UUID.randomUUID();
        BlockingQueue<byte[]> values = new ArrayBlockingQueue<>(1);
        client.setMessageConverter(new ByteArrayMessageConverter());
        StompSession stomp = client
                .connect("ws://localhost:" + port + "/socket", new StompSessionHandlerAdapter() {
                })
                .get(1L, TimeUnit.SECONDS);
        //when
        stomp.subscribe("/topic/video-call/" + roomId, new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return byte[].class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                values.add((byte[]) payload);
            }
        });
        StompHeaders stompHeaders = new StompHeaders();
        stompHeaders.setDestination("/app/video-call");
        stompHeaders.add("room_id", roomId.toString());
        stompHeaders.add("user_id", roomId.toString());

        stomp.send(stompHeaders, file);

        //then
        // NOTE -> performance of method assertEquals for big byte[] is terrible!
        assertArrayEquals(file, values.poll(1L, TimeUnit.SECONDS));

    }

    private WebSocketContainer createContainer() {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.setDefaultMaxBinaryMessageBufferSize(1024 * 1024);
        container.setDefaultMaxTextMessageBufferSize(1024 * 1024);
        return container;
    }
}