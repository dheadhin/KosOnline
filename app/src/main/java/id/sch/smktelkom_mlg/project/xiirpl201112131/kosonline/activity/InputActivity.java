package id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.R;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.core.CoreApplication;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.model.kosModel;

public class InputActivity extends AppCompatActivity {

    @BindView(R.id.editTextNamak)
    TextInputEditText editTextNamak;
    @BindView(R.id.editTextAlamatk)
    TextInputEditText editTextAlamatk;
    @BindView(R.id.editTextHarga)
    TextInputEditText editTextHarga;
    @BindView(R.id.editTextNamaPengelola)
    TextInputEditText editTextNamaPengelola;
    @BindView(R.id.editTextKontakPengelola)
    TextInputEditText editTextKontakPengelola;
    @BindView(R.id.editTextTelpK)
    TextInputEditText editTextTelpK;
    @BindView(R.id.editTextLuasKamar)
    TextInputEditText editTextLuasKamar;
    @BindView(R.id.buttonSimpan)
    Button buttonSimpan;

    //firebase
    private DatabaseReference mFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        ButterKnife.bind(this);
        mFirebaseRef = FirebaseDatabase.getInstance().getReference();
    }

    @OnClick(R.id.buttonSimpan)
    public void onClick() {
        if(isValid()){
            String kosNama = editTextNamak.getText().toString();
            String kosAlamat = editTextAlamatk.getText().toString();
            int kosHarga = Integer.parseInt(editTextHarga.getText().toString());
            String kosPengelola = editTextNamaPengelola.getText().toString();
            String kosTelpPengelola = editTextKontakPengelola.getText().toString();
            String kosTelp = editTextTelpK.getText().toString();
            String kosLuasKamar = editTextLuasKamar.getText().toString();

            DatabaseReference ref = mFirebaseRef.child("kosTabel").push();

            kosModel kos = new kosModel();
            kos.setKosNama(kosNama);
            kos.setKosHarga(kosHarga);
            kos.setKosAlamat(kosAlamat);
            kos.setKosPengelola(kosPengelola);
            kos.setKosKontakPengelola(kosTelpPengelola);
            kos.setKosTelp(kosTelp);
            kos.setKosLuasKamar(kosLuasKamar);
            kos.setIdPemilik(CoreApplication.getInstance().getSession().getActiveInformation());

            ref.setValue(kos);
            finish();
        }
    }

    private Boolean isValid(){
        Boolean valid = true;


        return valid;
    }
}
