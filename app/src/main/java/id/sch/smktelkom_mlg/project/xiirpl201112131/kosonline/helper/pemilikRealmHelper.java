package id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.helper;

import android.content.Context;
import android.util.Log;

import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.database.pemilikRealmObject;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.model.pemilikModel;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by DHEA on 20/11/2016.
 */

public class pemilikRealmHelper {
    private static final String TAG = "pemilikRealmHelper";

    private Realm realm;

    /**
     * constructor to create instance realm
     *
     * @param context
     */
    public pemilikRealmHelper(Context context, Realm mRealm) {
        realm = mRealm;
    }

    public void addPemilik(pemilikModel pemilikModel) {
        pemilikRealmObject pemilikRealmObject = new pemilikRealmObject();
        pemilikRealmObject.setPemilikId(pemilikModel.getPemilikId());
        pemilikRealmObject.setPemilikEmail(pemilikModel.getPemilikEmail());
        pemilikRealmObject.setPemilikNama(pemilikModel.getPemilikNama());
        pemilikRealmObject.setPemilikNoTelp(pemilikModel.getPemilikNoTelp());
        pemilikRealmObject.setPemilikPassword(pemilikModel.getPemilikPassword());

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(pemilikRealmObject);
        realm.commitTransaction();
    }

    public pemilikModel getPemilik(String id) {
        pemilikRealmObject pemilik = realm.where(pemilikRealmObject.class).equalTo("pemilikId", id).findFirst();
        return new pemilikModel(pemilik);
    }

    public pemilikModel loginValid(String email, String password) {
        pemilikRealmObject pemilik = realm.where(pemilikRealmObject.class).equalTo("pemilikEmail", email).equalTo("pemilikPassword", password).findFirst();
        return new pemilikModel(pemilik);
    }

    public void getListPemilik() {
        //ArrayList<pemilikModel> data = new ArrayList<>();

        RealmResults<pemilikRealmObject> rCall = realm.where(pemilikRealmObject.class).findAll();
        if (rCall.size() > 0)
            showLog("Size : " + rCall.size());

        for (int i = 0; i < rCall.size(); i++) {
            //data.add(new pemilikModel(rCall.get(i)));
            showLog("Id " + rCall.get(i).getPemilikId());
            showLog("Nama " + rCall.get(i).getPemilikNama());
            showLog("Telp " + rCall.get(i).getPemilikNoTelp());
            showLog("Email " + rCall.get(i).getPemilikEmail());
            showLog("Password " + rCall.get(i).getPemilikPassword());
        }
    }

    public void clearKos() {
        RealmResults<pemilikRealmObject> data = realm.where(pemilikRealmObject.class).findAll();
        realm.beginTransaction();
        data.clear();
        realm.commitTransaction();
    }

    private void showLog(String s) {
        Log.d(TAG, s);
    }
}
