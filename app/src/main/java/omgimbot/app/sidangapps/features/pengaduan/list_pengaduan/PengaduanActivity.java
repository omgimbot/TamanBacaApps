package omgimbot.app.sidangapps.features.pengaduan.list_pengaduan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import omgimbot.app.sidangapps.Prefs;
import omgimbot.app.sidangapps.Utils.GsonHelper;
import omgimbot.app.sidangapps.features.auth.login.model.LoginResponse;
import omgimbot.app.sidangapps.features.pengaduan.tambah_pengaduan.AddPengaduan;
import omgimbot.app.sidangapps.App;
import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.features.dashboard.DashboardDonaturActivity;
import omgimbot.app.sidangapps.features.dashboard.DashboardTamanBacaActivity;
import omgimbot.app.sidangapps.features.pengaduan.model.Pengaduan;
import omgimbot.app.sidangapps.features.taman_baca.buku.listbuku.BukuActivity;
import omgimbot.app.sidangapps.features.taman_baca.buku.tambahbuku.AddBukuActivity;
import omgimbot.app.sidangapps.ui.SweetDialogs;

public class PengaduanActivity extends AppCompatActivity implements IPengaduanView{
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    FloatingActionButton addAduan;
    SweetAlertDialog sweetAlertDialog;
    PengaduanAdapter adapter ;
    PengaduanPresenter presenter ;
    Bundle bundle ;
    String className ;
    LoginResponse mProfile;
    String idUser ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaduan);
        ButterKnife.bind(this);
        presenter = new PengaduanPresenter(this) ;
        Intent intent = this.getIntent();
        bundle = intent.getExtras();
        if (bundle != null) {
            className = getIntent().getExtras().getString("className");
        }

        addAduan = findViewById(R.id.mTambah);
        addAduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ac= new Intent(PengaduanActivity.this, AddPengaduan.class);
                ac.putExtra("className" , className);
                startActivity(ac);
                finish();
                Animatoo.animateSlideLeft(PengaduanActivity.this);
            }
        });

        this.initView();
        presenter.showPengaduan(idUser);
    }

//    @Override
//    public void onBackPressed() {
//        startActivity(new Intent(Pengaduan.this, DashboardTamanBacaActivity.class));
//        Animatoo.animateSlideRight(Pengaduan.this);
//
//    }

    @Override
    public void initView() {
        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        idUser = (mProfile.getResult().get_id().contains(" "))
                ? mProfile.getResult().get_id() : mProfile.getResult().get_id();
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.clearFocus();
//        addAduan.setOnClickListener(view ->this.goToAddPengaduan());
    }

    @Override
    public void clearLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            activity.getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void showLoadingIndicator() {
        sweetAlertDialog.show();
    }

    @Override
    public void hideLoadingIndicator() {
        sweetAlertDialog.dismiss();
    }

    @Override
    public void onDataReady(List<Pengaduan> result) {
        Log.d("data" , new Gson().toJson(result));
        adapter = new PengaduanAdapter(result, this);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if (result.isEmpty()){
//            empty_store.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }else {
//            empty_store.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onNetworkError(String cause) {
        Log.d("Error", cause);
        SweetDialogs.endpointError(this);
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

    @Override
    public void goToDashboardTamanBaca() {
        Intent a = new Intent(this, DashboardTamanBacaActivity.class);
        startActivity(a);
        finish();
    }

    @Override
    public void goToDashboardDonatur() {
        Intent a = new Intent(this, DashboardDonaturActivity.class);
        startActivity(a);
        finish();
    }

    @Override
    public void goToAddPengaduan() {
        Intent a = new Intent(this, AddBukuActivity.class);
        startActivity(a);
        finish();
    }

    @Override
    public void refresh() {
        Intent a = new Intent(this, BukuActivity.class);
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
}
