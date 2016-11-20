package id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.helper;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.database.kosRealmObject;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.model.kosModel;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by DHEA on 20/11/2016.
 */

public class kosRealmHelper {
    private static final String TAG = "kosRealmHelper";

    private Realm realm;

    /**
     * constructor to create instance realm
     *
     * @param context
     */
    public kosRealmHelper(Context context, Realm mRealm) {
        realm = mRealm;
    }

    public void addKos(kosModel kosModel) {
        kosRealmObject kosRealmObject = new kosRealmObject();
        kosRealmObject.setKosId(kosModel.getKosId());
        kosRealmObject.setIdPemilik(kosModel.getIdPemilik());
        kosRealmObject.setKosAlamat(kosModel.getKosAlamat());
        kosRealmObject.setKosHarga(kosModel.getKosHarga());
        kosRealmObject.setKosKontakPengelola(kosModel.getKosKontakPengelola());
        kosRealmObject.setKosLuasKamar(kosModel.getKosLuasKamar());
        kosRealmObject.setKosNama(kosModel.getKosNama());
        kosRealmObject.setKosPengelola(kosModel.getKosPengelola());
        kosRealmObject.setKosTelp(kosModel.getKosTelp());

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(kosRealmObject);
        realm.commitTransaction();
    }

    public ArrayList<kosModel> getListKos() {
        ArrayList<kosModel> data = new ArrayList<>();

        RealmResults<kosRealmObject> rCall = realm.where(kosRealmObject.class).findAll();
        if (rCall.size() > 0)
            showLog("Size : " + rCall.size());

        for (int i = 0; i < rCall.size(); i++) {
            data.add(new kosModel(rCall.get(i)));
        }

        return data;
    }

    public kosModel getKos(String id) {
        kosRealmObject pemilik = realm.where(kosRealmObject.class).equalTo("kosId", id).findFirst();
        return new kosModel(pemilik);
    }

    public void clearKos() {
        RealmResults<kosRealmObject> data = realm.where(kosRealmObject.class).findAll();
        realm.beginTransaction();
        data.clear();
        realm.commitTransaction();
    }

    private void showLog(String s) {
        Log.d(TAG, s);
    }
}
