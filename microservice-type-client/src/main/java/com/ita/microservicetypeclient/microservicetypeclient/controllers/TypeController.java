package com.ita.microservicetypeclient.microservicetypeclient.controllers;

import com.ita.microservicetypeclient.microservicetypeclient.entities.Type;
import com.ita.microservicetypeclient.microservicetypeclient.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.*;

@RestController
public class TypeController {
    private final TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping(path = "/income")
    public List<Type> getAllIncomeTypes() {
        return typeService.getAllTypes("income");
    }

    @GetMapping(path = "/outlay")
    public List<Type> getAllOutlayTypes() {
        return typeService.getAllTypes("outlay");
    }

    @GetMapping(path = "")
    public ResponseEntity<List<String>> getAllTypes() {
        List<String> types = typeService.getAllTypes(null).stream().map(Type::getType).collect(Collectors.toList());
        return ResponseEntity.ok(types);
    }

    @PostMapping(path = "")
    public ResponseEntity addType(@RequestBody Type type) {
        typeService.addType(type);
        return ResponseEntity.ok().build();
    }
}
