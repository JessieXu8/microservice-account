package com.ita.microservicetypeclient.microservicetypeclient.services;

import com.ita.microservicetypeclient.microservicetypeclient.entities.Type;
import com.ita.microservicetypeclient.microservicetypeclient.repositories.TypeRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TypeService {
    private final TypeRepository typeRepository;

    @Autowired
    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<Type> getAllTypes(String accountKind) {
        if (StringUtils.isEmpty(accountKind)) {
            return typeRepository.findAll();
        }
        return typeRepository.findAllByAccountKind(accountKind);
    }

    public void addType(Type type) {
        if (type.getType() == null) {
            return;
        }
        typeRepository.save(type);
    }
}
