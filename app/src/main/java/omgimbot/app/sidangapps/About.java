package omgimbot.app.sidangapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import omgimbot.app.sidangapps.features.dashboard.DashboardTamanBacaActivity;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, DashboardTamanBacaActivity.class));
        Animatoo.animateSlideRight(this);
    }
}
