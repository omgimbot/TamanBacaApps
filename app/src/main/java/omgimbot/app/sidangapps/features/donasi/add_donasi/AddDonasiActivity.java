package omgimbot.app.sidangapps.features.donasi.add_donasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.features.dashboard.DashboardActivity;
import omgimbot.app.sidangapps.features.taman_baca.TamanBacaActivity;

public class AddDonasiActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donasi);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Donasi Buku");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    public void goToDashboard() {
        Intent a = new Intent(this, TamanBacaActivity.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed() {
        // ...

        this.goToDashboard();
        super.onBackPressed();
    }

    @OnClick(R.id.mWa)
    public void goToWa(){
        final String phoneD = "+6282279058667";
        String url = "https://api.whatsapp.com/send?phone=" + phoneD;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setPackage("com.whatsapp");
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}