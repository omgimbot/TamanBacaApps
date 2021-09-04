package omgimbot.app.sidangapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import omgimbot.app.sidangapps.features.auth.login.LoginActivity;
import omgimbot.app.sidangapps.features.dashboard.DashboardTamanBacaActivity;

public class Profile extends AppCompatActivity {

    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;

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

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, DashboardTamanBacaActivity.class));
        Animatoo.animateSlideRight(this);
    }
}
