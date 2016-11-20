package id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.core;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Process;
import android.support.v7.app.AlertDialog;

import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.MainActivity;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.helper.SessionManagement;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by DHEA on 20/11/2016.
 */

public class CoreApplication extends Application {
    public static CoreApplication app;
    SessionManagement session;

    public static CoreApplication getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        /*Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());*/

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("kosonline.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        session = new SessionManagement(this);

        app = this;
    }

    public SessionManagement getSession() {
        return session;
    }

    public void showLogoutConfirmation(Context konteks) {
        AlertDialog.Builder kotakBuilder = new AlertDialog.Builder(konteks);
        kotakBuilder.setIcon(android.R.drawable.ic_dialog_alert);
        kotakBuilder.setTitle("Logout Confirmation");
        kotakBuilder.setMessage("Do you want to logout ?");
        kotakBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logout();
                    }
                });
        kotakBuilder.setNegativeButton("no", null);
        kotakBuilder.create().show();
    }

    public void closeApplication() {
        Process.killProcess(Process.myPid());
    }

    public void logout() {
        getSharedPreferences("KosOnline", 0).edit().clear().commit();

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}