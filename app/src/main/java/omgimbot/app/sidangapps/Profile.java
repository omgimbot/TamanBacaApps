package omgimbot.app.sidangapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import omgimbot.app.sidangapps.Utils.GsonHelper;
import omgimbot.app.sidangapps.features.auth.login.LoginActivity;
import omgimbot.app.sidangapps.features.auth.login.model.LoginResponse;
import omgimbot.app.sidangapps.features.dashboard.DashboardDonaturActivity;
import omgimbot.app.sidangapps.features.dashboard.DashboardTamanBacaActivity;

public class Profile extends AppCompatActivity {

    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    @BindView(R.id.namaLengkap)
    TextView namaLengkap;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.alamatLengkap)
    TextView alamatLengkap;
    LoginResponse mProfile;
    String nama, alamat, notlp, emails, className;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Profile");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        nama = (mProfile.getResult().getNama().contains(" "))
                ? mProfile.getResult().getNama() : mProfile.getResult().getNama();
        alamat = (mProfile.getResult().getAlamat().contains(" "))
                ? mProfile.getResult().getAlamat() : mProfile.getResult().getAlamat();
        notlp = (mProfile.getResult().getNoTelpon().contains(" "))
                ? mProfile.getResult().getNoTelpon() : mProfile.getResult().getNoTelpon();
        emails = (mProfile.getResult().getEmail().contains(" "))
                ? mProfile.getResult().getEmail() : mProfile.getResult().getEmail();
        namaLengkap.setText(nama);
        alamatLengkap.setText(alamat);
        phone.setText(notlp);
        email.setText(emails);
        Intent intent = this.getIntent();
        bundle = intent.getExtras();
        if (bundle != null) {
            className = getIntent().getExtras().getString("className");
        }
    }

    public void goToDashboardTamanBaca() {
        Intent a = new Intent(this, DashboardTamanBacaActivity.class);
        startActivity(a);
        finish();
    }

    public void goToDashboardDonatur() {
        Intent a = new Intent(this, DashboardDonaturActivity.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed() {
        // ...
        if (className.equals("DashboardDonaturActivity")) {
            this.goToDashboardDonatur();
            Animatoo.animateSlideRight(this);
        } else {
            this.goToDashboardTamanBaca();
            Animatoo.animateSlideRight(this);
        }
        super.onBackPressed();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
