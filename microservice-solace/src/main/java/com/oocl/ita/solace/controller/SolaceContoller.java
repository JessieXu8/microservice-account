package com.oocl.ita.solace.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.ita.solace.dto.JsonDTO;
import com.oocl.ita.solace.producer.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/solace")
public class SolaceContoller {

    @Autowired
    MessageSender messageSender;
    @Autowired
    private ObjectMapper objectMapper;
    @PostMapping
    public ResponseEntity send(@RequestBody JsonDTO dto) throws Exception {
        try{
            messageSender.sendMessage("tutorial/queue", objectMapper.writeValueAsString(dto ));
            return ResponseEntity.ok().build();
        } catch (JsonProcessingException jpex) {
            jpex.printStackTrace();
            throw new Exception(jpex);
        }
    }
}

