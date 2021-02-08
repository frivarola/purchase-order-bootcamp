package com.desafio.purchaseOrder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PurchaseOrderApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
        //enviar 1 producto
    void caso1() throws Exception {
        this.mockMvc.perform(
                post("/api/v1/purchase-request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\":\"frivarola\",\"articlesOrder\":[{\"productId\":102,\"discount\":0,\"quantity\":1}]}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
        //enviar 2 producto mas
    void caso2() throws Exception {
        this.mockMvc.perform(
                post("/api/v1/purchase-request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\":\"frivarola\",\"articlesOrder\":[{\"productId\":102,\"discount\":0,\"quantity\":1}, {\"productId\":103,\"discount\":20,\"quantity\":1}]}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
        //enviar 1 producto sin stock
    void caso3() throws Exception {
        this.mockMvc.perform(
                post("/api/v1/purchase-request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\":\"frivarola\",\"articlesOrder\":[{\"productId\":102,\"discount\":0,\"quantity\":100}]}"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
        //enviar 1 producto a otra persona
    void caso4() throws Exception {
        this.mockMvc.perform(
                post("/api/v1/purchase-request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\":\"beta\",\"articlesOrder\":[{\"productId\":101,\"discount\":0,\"quantity\":1}]}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
        //enviar 2 productos, 1 sin stock y 1 con stock
    void caso5() throws Exception {
        this.mockMvc.perform(
                post("/api/v1/purchase-request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\":\"frivarola\",\"articlesOrder\":[{\"productId\":102,\"discount\":0,\"quantity\":1}, {\"productId\":103,\"discount\":20,\"quantity\":100}]}"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    //enviar 3 productos, 2 con distinto descuento, 1 sin descuento
    @Test
    void caso6() throws Exception {
        this.mockMvc.perform(
                post("/api/v1/purchase-request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\":\"frivarola\",\"articlesOrder\":[{\"productId\":106,\"discount\":10,\"quantity\":1}, {\"productId\":105,\"discount\":20,\"quantity\":1}]}, {\"productId\":108,\"discount\":0,\"quantity\":1}]}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    //get cart user existente
    @Test
    void caso7() throws Exception {
        this.mockMvc.perform(
                get("/api/v1/purchase-request/cart/frivarola"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    //get cart otro user existente
    @Test
    void caso8() throws Exception {
        this.mockMvc.perform(
                get("/api/v1/purchase-request/cart/beta"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    //get cart otro user existente
    @Test
    void caso9() throws Exception {
        this.mockMvc.perform(
                get("/api/v1/purchase-request/cart/messi"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
