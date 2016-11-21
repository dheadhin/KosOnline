package id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.MainActivity;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.R;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.helper.SessionManagement;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.helper.pemilikRealmHelper;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.model.pemilikModel;
import io.realm.Realm;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email_sign_in_button)
    Button emailSignInButton;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.email)
    AutoCompleteTextView email;

    SessionManagement session;
    pemilikRealmHelper pemilikHelper;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        session = new SessionManagement(this);
        realm = Realm.getDefaultInstance();
        pemilikHelper = new pemilikRealmHelper(LoginActivity.this, realm);

    }

    @OnClick(R.id.email_sign_in_button)
    public void onClick() {
        if (isValid()) {
            // menyimpan informasi login ke dalam shared preferences
            pemilikModel pemilik = pemilikHelper.loginValid(email.getText().toString(), password.getText().toString());
            session.setActiveInformation(pemilik.getPemilikId());
            session.setKeyIsLoggedIn(true);
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    private boolean isValid() {
        pemilikModel pemilik = pemilikHelper.loginValid(email.getText().toString(), password.getText().toString());
        return pemilik != null;
    }
}
