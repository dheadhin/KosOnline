package id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.model;

import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.database.kosRealmObject;

/**
 * Created by putri aditya on 11/21/2016.
 */
public class kosModel {
    private String kosId;
    private String idPemilik;
    private String kosAlamat;
    private int kosHarga;
    private String kosKontakPengelola;
    private String kosLuasKamar;
    private String kosNama;
    private String kosPengelola;
    private String kosTelp;

    public kosModel(){}

    public kosModel(kosRealmObject kosRealmObject){
        this.kosId = kosRealmObject.getKosId();
        this.idPemilik = kosRealmObject.getIdPemilik();
        this.kosAlamat = kosRealmObject.getKosAlamat();
        this.kosHarga = kosRealmObject.getKosHarga();
        this.kosKontakPengelola = kosRealmObject.getKosKontakPengelola();
        this.kosLuasKamar = kosRealmObject.getKosLuasKamar();
        this.kosNama = kosRealmObject.getKosNama();
        this.kosPengelola = kosRealmObject.getKosPengelola();
        this.kosTelp = kosRealmObject.getKosTelp();
    }

    public String getKosId() {
        return kosId;
    }

    public void setKosId(String kosId) {
        this.kosId = kosId;
    }

    public String getIdPemilik() {
        return idPemilik;
    }

    public void setIdPemilik(String idPemilik) {
        this.idPemilik = idPemilik;
    }

    public String getKosAlamat() {
        return kosAlamat;
    }

    public void setKosAlamat(String kosAlamat) {
        this.kosAlamat = kosAlamat;
    }

    public int getKosHarga() {
        return kosHarga;
    }

    public void setKosHarga(int kosHarga) {
        this.kosHarga = kosHarga;
    }

    public String getKosKontakPengelola() {
        return kosKontakPengelola;
    }

    public void setKosKontakPengelola(String kosKontakPengelola) {
        this.kosKontakPengelola = kosKontakPengelola;
    }

    public String getKosLuasKamar() {
        return kosLuasKamar;
    }

    public void setKosLuasKamar(String kosLuasKamar) {
        this.kosLuasKamar = kosLuasKamar;
    }

    public String getKosNama() {
        return kosNama;
    }

    public void setKosNama(String kosNama) {
        this.kosNama = kosNama;
    }

    public String getKosPengelola() {
        return kosPengelola;
    }

    public void setKosPengelola(String kosPengelola) {
        this.kosPengelola = kosPengelola;
    }

    public String getKosTelp() {
        return kosTelp;
    }

    public void setKosTelp(String kosTelp) {
        this.kosTelp = kosTelp;
    }
}
