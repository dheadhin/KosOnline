package id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by DHEA on 20/11/2016.
 */

public class kosRealmObject extends RealmObject {
    @PrimaryKey
    private String kosId;
    private String idPemilik;
    private String kosAlamat;
    private int kosHarga;
    private String kosKontakPengelola;
    private String kosLuasKamar;
    private String kosNama;
    private String kosPengelola;
    private String kosTelp;

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

