package omgimbot.app.sidangapps.features.dashboard;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mindorks.placeholderview.PlaceHolderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import omgimbot.app.sidangapps.About;
import omgimbot.app.sidangapps.features.donatur.donasi_lain.DonasiLain;
import omgimbot.app.sidangapps.features.pengaduan.list_pengaduan.PengaduanActivity;
import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.Utils.AdapterSliderBanner;
import omgimbot.app.sidangapps.Utils.ModelSliderBanner;
import omgimbot.app.sidangapps.features.auth.login.LoginActivity;
import omgimbot.app.sidangapps.features.donasi.RiwayatDonasiActivity;
import omgimbot.app.sidangapps.features.donatur.buku.BukuActivity;
import omgimbot.app.sidangapps.ui.DrawerHeader;
import omgimbot.app.sidangapps.ui.DrawerMenuItem;

import static omgimbot.app.sidangapps.App.getContext;

public class DashboardDonaturActivity extends AppCompatActivity {
    @BindView(R.id.drawerView)
    PlaceHolderView mDrawerView;
    @BindView(R.id.mainMenu)
    ImageButton mainMenuDashboard;
    @BindView(R.id.toolbar)
    Toolbar toolbarMain;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle mTogle;
    ImageView banners;
    ViewPager viewPager;
    LinearLayout indicatorDot;
    AdapterSliderBanner adapter;
    List<ModelSliderBanner> models;
    private int dotsCount;
    private ImageView[] dots;
    boolean BackPress = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_donatur);
        ButterKnife.bind(this);

        banners = findViewById(R.id.banner);
        viewPager = findViewById(R.id.viewPager);
        indicatorDot = findViewById(R.id.bannerDot);

        models = new ArrayList<>();
        models.add(new ModelSliderBanner(R.drawable.banners_a));
        models.add(new ModelSliderBanner(R.drawable.banners_b));
        models.add(new ModelSliderBanner(R.drawable.banners_c));
        models.add(new ModelSliderBanner(R.drawable.banners_d));

        adapter = new AdapterSliderBanner(models, this);
        dotsCount = adapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.nonactive_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            indicatorDot.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.active_dot));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.nonactive_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 3000, 5000);

        viewPager.setAdapter(adapter);
        viewPager.setPadding(35, 0, 35, 0);

        this.initViews();
    }

    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            DashboardDonaturActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else if (viewPager.getCurrentItem() == 2) {
                        viewPager.setCurrentItem(3);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    public void initViews() {
        this.setupDrawer();
        drawer = findViewById(R.id.dashboard);
        mTogle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
        drawer.setDrawerListener(mTogle);
        mTogle.syncState();
        ((AppCompatActivity) this).setSupportActionBar(toolbarMain);
        ((AppCompatActivity) this).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    private void setupDrawer() {
        mDrawerView
                .addView(new DrawerHeader(this))
                .addView(new DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_PROFILE))
                .addView(new DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_ABOUT))
                .addView(new DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_LOGOUT));

        mainMenuDashboard.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                if (!drawer.isDrawerOpen(Gravity.END)) drawer.openDrawer(Gravity.END);
                else drawer.closeDrawer(Gravity.START);
            }
        });
    }


    @OnClick(R.id.mBuku)
    public void gotoInputBuku() {
        Intent a = new Intent(this, BukuActivity.class);
        startActivity(a);
        finish();
    }
    @OnClick(R.id.mBukuLain)
    public void gotomBukuLain() {
        Intent a = new Intent(this, DonasiLain.class);
        startActivity(a);
        finish();
    }

    @OnClick(R.id.mAduan)
    public void gotoPengaduan() {
        Intent a = new Intent(this, PengaduanActivity.class);
        a.putExtra("className" , this.getClass().getSimpleName());
        startActivity(a);
        finish();
    }

    @OnClick(R.id.mAbout)
    public void gotoAbout() {
        Intent a = new Intent(this, About.class);
        startActivity(a);
        finish();
    }

    @OnClick(R.id.mRiwayat)
    public void gotoRiwayat() {
        Intent a = new Intent(this, RiwayatDonasiActivity.class);
        a.putExtra("className" , this.getClass().getSimpleName());
        startActivity(a);
        finish();
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
        Intent a = new Intent(this, LoginActivity.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (BackPress) {
            finishAffinity();
            return;
        }
        BackPress = true;
        Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                BackPress = false;
            }
        }, 2000);
    }
}
