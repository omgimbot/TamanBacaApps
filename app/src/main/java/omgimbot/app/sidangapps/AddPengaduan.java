package omgimbot.app.sidangapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class AddPengaduan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pengaduan);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AddPengaduan.this, Pengaduan.class));
        Animatoo.animateSlideRight(AddPengaduan.this);
    }
}
