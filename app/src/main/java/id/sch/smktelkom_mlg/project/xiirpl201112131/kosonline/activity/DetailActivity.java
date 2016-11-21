package id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.R;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.adapter.kosAdapter;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.helper.kosRealmHelper;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.helper.pemilikRealmHelper;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.model.kosModel;
import io.realm.Realm;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.alamat)
    TextView alamat;
    @BindView(R.id.kosTelp)
    TextView kosTelp;
    @BindView(R.id.pemilik)
    TextView pemilik;
    @BindView(R.id.pemilikTelp)
    TextView pemilikTelp;
    @BindView(R.id.pengelola)
    TextView pengelola;
    @BindView(R.id.pengelolaTelp)
    TextView pengelolaTelp;
    @BindView(R.id.luas)
    TextView luas;
    @BindView(R.id.harga)
    TextView harga;

    private kosRealmHelper kosHelper;
    private pemilikRealmHelper pemilikHelper;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final String kosId = getIntent().getExtras().getString("kosId");

        realm = Realm.getDefaultInstance();
        kosHelper = new kosRealmHelper(DetailActivity.this, realm);
        pemilikHelper = new pemilikRealmHelper(DetailActivity.this, realm);

        kosModel kos = kosHelper.getKos(kosId);

        getSupportActionBar().setTitle(kos.getKosNama());

        setText(kos);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setText(kosModel kos){
        alamat.setText(kos.getKosAlamat());
        kosTelp.setText(kos.getKosTelp());
        pemilikHelper.getListPemilik();
        pemilik.setText(pemilikHelper.getPemilik(kos.getIdPemilik()).getPemilikNama());
        pemilikTelp.setText(pemilikHelper.getPemilik(kos.getIdPemilik()).getPemilikNoTelp());
        pengelola.setText(kos.getKosPengelola());
        pengelolaTelp.setText(kos.getKosKontakPengelola());
        luas.setText(kos.getKosLuasKamar());
        harga.setText(""+kos.getKosHarga());
    }
}
