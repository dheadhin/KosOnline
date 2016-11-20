package id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by DHEA on 20/11/2016.
 */

public class pemilikRealmObject extends RealmObject {
    @PrimaryKey
    private String pemilikId;
    private String pemilikEmail;
    private String pemilikNama;
    private String pemilikNoTelp;
    private String pemilikPassword;

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
