package com.cruiseline.apirest.services;

import org.springframework.stereotype.Service;
import com.cruiseline.apirest.models.Cabine;
import com.cruiseline.apirest.repositories.PacoteRepository;

@Service
public class CabineService {
  
  PacoteRepository pacoteRepository;
  
  public CabineService() {
    pacoteRepository = PacoteRepository.getInstance();
  }
  
  public Cabine save(int idPacote, Cabine cabine) {

    return pacoteRepository.updateCabine(idPacote, cabine);
  }
  
}
