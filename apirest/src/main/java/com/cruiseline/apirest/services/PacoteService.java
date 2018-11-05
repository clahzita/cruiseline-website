package com.cruiseline.apirest.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.cruiseline.apirest.models.Pacote;
import com.cruiseline.apirest.repositories.PacoteRepository;

@Service
public class PacoteService {

  PacoteRepository pacoteRepository;
  
  public PacoteService() {
    pacoteRepository = PacoteRepository.getInstance();
  }
  
  public List<Pacote> findAll() {
    return pacoteRepository.findAll();
  }

  public Pacote findById(int id) {
    return pacoteRepository.findById(id);
  }
  

}
