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
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author asus
 */
@Service
public class NilaiService {

    @Autowired
    private NilaiRepository nilaiRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Nilai> getNilai() {
        return nilaiRepository.findAll();
    }

    public void insert(Nilai nilai) {
        Optional<Nilai> mhsOptional = nilaiRepository.findNilaiById(nilai.getId());
        if (mhsOptional.isPresent()) {
            throw new IllegalStateException("Nilai sudah ada");
        }
        nilaiRepository.save(nilai);
    }


    public ResponseTemplate getNilaiById(Long nilaiId) {
        ResponseTemplate vo = new ResponseTemplate();
        Nilai nilai = nilaiRepository.findById(nilaiId)
                .orElseThrow(() -> new IllegalStateException("Nilai tidak ada"));
        Mahasiswa mahasiswa
                = restTemplate.getForObject("http://localhost:9005/api/v1/mahasiswa/"
                        + nilai.getMahasiswaId(), Mahasiswa.class);
        Matakuliah matakuliah
                = restTemplate.getForObject("http://localhost:9006/api/v1/matakuliah/"
                        + nilai.getMatakuliahId(), Matakuliah.class);
        vo.setNilai(nilai);
        vo.setMahasiswa(mahasiswa);
        vo.setMatakuliah(matakuliah);
        return vo;
    }

    public void update(Nilai nilai) {
        Long id = nilai.getId();
        Optional<Nilai> existingNilaiOptional = nilaiRepository.findById(id);

        if (existingNilaiOptional.isPresent()) {
            Nilai existingNilai = existingNilaiOptional.get();

            existingNilai.setNilai(nilai.getNilai());
            existingNilai.setMahasiswaId(nilai.getMahasiswaId());
            existingNilai.setMatakuliahId(nilai.getMatakuliahId());

            nilaiRepository.save(existingNilai);
        } else {
            throw new IllegalStateException("Mahasiswa dengan id " + id + " tidak ditemukan");
        }
    }

    public void delete(Long id) {
        nilaiRepository.deleteById(id);
    }

}
