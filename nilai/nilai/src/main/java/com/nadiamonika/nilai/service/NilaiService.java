/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nadiamonika.nilai.service;

import com.nadiamonika.nilai.entity.Nilai;
import com.nadiamonika.nilai.repository.NilaiRepository;
import com.nadiamonika.nilai.vo.Mahasiswa;
import com.nadiamonika.nilai.vo.Matakuliah;
import com.nadiamonika.nilai.vo.ResponseTemplate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author User
 */
@Service
public class NilaiService {

    @Autowired
    private NilaiRepository nilaiRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Nilai> getAllNilai() {
        return nilaiRepository.findAll();
    }

    public void insert(Nilai nilai) {
        nilaiRepository.save(nilai);
    }

    public ResponseTemplate getNilaiById(Long id) {
        ResponseTemplate vo = new ResponseTemplate();
        Nilai nilai = nilaiRepository.findById(id).get();
        Mahasiswa mahasiswa
                = restTemplate.getForObject("http://localhost:9001/api/v1/mahasiswa/"
                        + nilai.getIdmahasiswa(), Mahasiswa.class);
        Matakuliah matakuliah
                = restTemplate.getForObject("http://localhost:9002/api/v1/matakuliah/"
                        + nilai.getIdmatakuliah(), Matakuliah.class);
        vo.setNilai(nilai);
        vo.setMahasiswa(mahasiswa);
        vo.setMatakuliah(matakuliah);
        return vo;
    }
}
