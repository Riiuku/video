package pl.riiuku.videoapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.riiuku.videoapi.api.RoomRequest;
import pl.riiuku.videoapi.domain.Room;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
}