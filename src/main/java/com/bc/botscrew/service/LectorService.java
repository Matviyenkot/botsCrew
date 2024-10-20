package com.bc.botscrew.service;

import com.bc.botscrew.dao.LectorRepo;
import com.bc.botscrew.entities.Lector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectorService{
    @Autowired
    private LectorRepo lectorRepository;

    public List<Lector> globalSearch(String template) {
        return lectorRepository.findByNameContaining(template);
    }
}
