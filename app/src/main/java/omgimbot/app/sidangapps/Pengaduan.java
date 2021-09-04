package omgimbot.app.sidangapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import omgimbot.app.sidangapps.features.dashboard.DashboardTamanBacaActivity;

public class Pengaduan extends AppCompatActivity {

    FloatingActionButton addAduan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaduan);

        addAduan = findViewById(R.id.mTambah);
        addAduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pengaduan.this, AddPengaduan.class));
                Animatoo.animateSlideLeft(Pengaduan.this);
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Pengaduan.this, DashboardTamanBacaActivity.class));
        Animatoo.animateSlideRight(Pengaduan.this);
    }
}
