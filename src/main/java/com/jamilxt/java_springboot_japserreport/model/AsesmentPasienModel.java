package com.jamilxt.java_springboot_japserreport.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AsesmentPasienModel {
    private  String keluhan;
    private  String riwayatPenyakit;
    private  String riwayatObat;
    private  String riwayatObatDetail;
    private  String tekananDarah;
    private  String nadi;
    private  String suhu;
    private  String pernapasan;
    private  String beratBadan;
    private  String tinggiBadan;
    private  String asesFungsional;
    private  String asesFungsionalDetail;
    private  String asesKepRj1;
    private  String asesKepRj2;
    private  String asesKepHasilKajiRJ;
    private  String asesKepHasilKajiRjTind;
}
