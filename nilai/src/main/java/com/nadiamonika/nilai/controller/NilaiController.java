/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nadiamonika.nilai.controller;

import com.nadiamonika.nilai.entity.Nilai;
import com.nadiamonika.nilai.service.NilaiService;
import com.nadiamonika.nilai.vo.ResponseTemplate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asus
 */

@RestController
@RequestMapping("api/v1/nilai")
public class NilaiController {
    @Autowired
    private NilaiService nilaiService;
    
    @PostMapping
    public void insert(@RequestBody Nilai nilai){
        nilaiService.insert(nilai); 
    }
    
    @GetMapping(path = "{id}")
    public ResponseTemplate getNilaiById(@PathVariable("id") Long nilaiId){
        return nilaiService.getNilaiById(nilaiId);
    }
    
    @PutMapping(path = "{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Nilai nilai) {
        nilai.setId(id);

        nilaiService.update(nilai);
    }
    
    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") Long id) {
        nilaiService.delete(id);
    }

}
