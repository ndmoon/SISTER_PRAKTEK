/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nadiamonika.warnet.service;

import com.nadiamonika.warnet.entity.Warnet;
import com.nadiamonika.warnet.repository.WarnetRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class WarnetService {
    @Autowired
    private WarnetRepository warnetRepository;
    
    public void insert(Warnet warnet) {
        Optional<Warnet> wntOptional
                = warnetRepository.findWarnetByKodePelanggan(warnet.getKodePelanggan());
        if (wntOptional.isPresent()) {
            throw new IllegalStateException("Kode sudah ada");
        }
        warnetRepository.save(warnet);
    }
    
    public List<Warnet> getAll() {
        return warnetRepository.findAll();
    }
    
    public Warnet getWarnetById(Long id) {
        return warnetRepository.findById(id).get();
    }
    
    public void delete(Long id) {
        warnetRepository.deleteById(id);
    }
}
