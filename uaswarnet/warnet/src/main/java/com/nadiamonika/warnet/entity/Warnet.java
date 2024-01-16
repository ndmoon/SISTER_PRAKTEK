/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nadiamonika.warnet.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author User
 */
public class Warnet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String kodePelanggan;
    private Integer jenis;
    private Integer lama;
    private Integer tarif;
    private Integer total;

    public Warnet() {
    }

    public Warnet(Long id, String kodePelanggan, Integer jenis, Integer lama, Integer tarif, Integer total) {
        this.id = id;
        this.kodePelanggan = kodePelanggan;
        this.jenis = jenis;
        this.lama = lama;
        this.tarif = tarif;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKodePelanggan() {
        return kodePelanggan;
    }

    public void setKodePelanggan(String kodePelanggan) {
        this.kodePelanggan = kodePelanggan;
    }

    public Integer getJenis() {
        return jenis;
    }

    public void setJenis(Integer jenis) {
        this.jenis = jenis;
    }

    public Integer getLama() {
        return lama;
    }

    public void setLama(Integer lama) {
        this.lama = lama;
    }

    public Integer getTarif() {
        return tarif;
    }

    public void setTarif(Integer tarif) {
        this.tarif = tarif;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Warnet{" + "id=" + id + ", kode Pelanggan=" + kodePelanggan + ", jenis=" + jenis + ", lama=" + lama
                + ", tarif=" + tarif + '}';
    }

}
