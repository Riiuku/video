package pl.riiuku.videoapi.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.riiuku.videoapi.api.RoomRequest;
import pl.riiuku.videoapi.domain.Room;
import pl.riiuku.videoapi.service.room.RoomSchedulerService;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private RoomSchedulerService roomSchedulerService;

    @Test
    @Order(1)
    public void shouldReturnEmptyListOfRooms() throws Exception {
        mockMvc.perform(get("/rooms"))
                .andDo(print())
                .andExpect(content().string("[]"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void shouldReturnDifferentNameIfAlreadyExistsFiftyTimes() throws Exception {
        String name = "Test";
        for (int i = 0; i < 50; i++) {
            String testName = i == 0 ? "Test" : "Test_" + i;
            mockMvc.perform(post("/rooms")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(new RoomRequest(name, null))))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name", is(testName)));
        }

        Mockito.verify(roomSchedulerService, Mockito.times(50)).createNewDeleteTask(org.mockito.ArgumentMatchers.any());
    }

    @Test
    @Order(3)
    public void shouldReturnListWithFiftyElementsOfRoom() throws Exception {
        mockMvc.perform(get("/rooms"))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(50)))
                .andExpect(jsonPath("$[8].name", equalTo("Test_8")))
                .andExpect(status().isOk());
    }


    @Test
    @Order(5)
    public void shouldExtendTimeOfChosenRoom() throws Exception {
        String responseBody = mockMvc.perform(get("/rooms")).andReturn().getResponse().getContentAsString();
        RoomResponse body = new ObjectMapper()
                .readValue(responseBody, new TypeReference<List<RoomResponse>>() {}).get(1);

        mockMvc.perform(patch("/rooms/" + body.publicId + "/time"))
                .andExpect(status().isOk());

        Mockito.verify(roomSchedulerService, Mockito.times(1)).extentTimeOfRoomLife(UUID.fromString(body.publicId));

    }


    private static class RoomResponse {
        @JsonProperty(value = "name")
        String name;
        @JsonProperty(value = "lifeTime")
        String lifeTime;
        @JsonProperty(value = "publicId")
        String publicId;
        @JsonProperty(value = "maxSize")
        Integer maxSize;
    }
}