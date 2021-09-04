package omgimbot.app.sidangapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import omgimbot.app.sidangapps.features.auth.login.LoginActivity;

public class SplashScreen extends AppCompatActivity {

    private int time_milis = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                Animatoo.animateSlideUp(SplashScreen.this);
            }
        }, time_milis);
    }
}
