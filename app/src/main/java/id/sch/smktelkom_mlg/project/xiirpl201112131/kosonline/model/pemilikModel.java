package id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.model;

import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.database.pemilikRealmObject;

/**
 * Created by putri aditya on 11/21/2016.
 */

public class pemilikModel {
    private String pemilikId;
    private String pemilikEmail;
    private String pemilikNama;
    private String pemilikNoTelp;
    private String pemilikPassword;

    public pemilikModel(){
    }

    public pemilikModel(pemilikRealmObject obj){
        this.pemilikId = obj.getPemilikId();
        this.pemilikEmail = obj.getPemilikEmail();
        this.pemilikNama = obj.getPemilikNama();
        this.pemilikNoTelp = obj.getPemilikNoTelp();
        this.pemilikPassword = obj.getPemilikPassword();
    }

    public String getPemilikPassword() {
        return pemilikPassword;
    }

    public void setPemilikPassword(String pemilikPassword) {
        this.pemilikPassword = pemilikPassword;
    }

    public String getPemilikNoTelp() {
        return pemilikNoTelp;
    }

    public void setPemilikNoTelp(String pemilikNoTelp) {
        this.pemilikNoTelp = pemilikNoTelp;
    }

    public String getPemilikNama() {
        return pemilikNama;
    }

    public void setPemilikNama(String pemilikNama) {
        this.pemilikNama = pemilikNama;
    }

    public String getPemilikEmail() {
        return pemilikEmail;
    }

    public void setPemilikEmail(String pemilikEmail) {
        this.pemilikEmail = pemilikEmail;
    }

    public String getPemilikId() {
        return pemilikId;
    }

    public void setPemilikId(String pemilikId) {
        this.pemilikId = pemilikId;
    }
}
