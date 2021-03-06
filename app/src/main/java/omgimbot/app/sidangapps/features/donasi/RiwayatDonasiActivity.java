package omgimbot.app.sidangapps.features.donasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import omgimbot.app.sidangapps.App;
import omgimbot.app.sidangapps.Prefs;
import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.Utils.GsonHelper;
import omgimbot.app.sidangapps.features.auth.login.model.LoginResponse;
import omgimbot.app.sidangapps.features.dashboard.DashboardDonaturActivity;
import omgimbot.app.sidangapps.features.dashboard.DashboardTamanBacaActivity;
import omgimbot.app.sidangapps.features.donatur.model.Donasi;
import omgimbot.app.sidangapps.features.taman_baca.buku.model.Buku;
import omgimbot.app.sidangapps.ui.SweetDialogs;

public class RiwayatDonasiActivity extends AppCompatActivity implements IRiwayatDonasiView , DonasiAdapter.onSelected {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    @BindView(R.id.empty_store)
    LinearLayout empty_store;
    SweetAlertDialog sweetAlertDialog;
    DonasiAdapter adapter;
    RiwayatDonasiPresenter presenter;
    List<Buku> product = null;
    LoginResponse mProfile;
    String idUser;
    Bundle bundle;
    String className;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_donasi);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Riwayat Donasi");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.initView();
        presenter = new RiwayatDonasiPresenter(this);
        Intent intent = this.getIntent();
        bundle = intent.getExtras();
        if (bundle != null) {
            className = getIntent().getExtras().getString("className");
            if (className.equals("DashboardDonaturActivity"))
                presenter.listDonasiDonatur(idUser);
            else
                presenter.listDonasiTamanBaca(idUser);
        }
    }

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
    public void onDataReady(List<Donasi> result) {
        Log.d("dataDonasi", new Gson().toJson(result));
        adapter = new DonasiAdapter(result, this, className,this);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if (result.isEmpty()){
            empty_store.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }else {
            empty_store.setVisibility(View.GONE);
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
    public void goToDashboardDonatur() {
        Intent a = new Intent(this, DashboardDonaturActivity.class);
        startActivity(a);
        finish();
    }

    @Override
    public void goToDashboardTamanBaca() {
        Intent a = new Intent(this, DashboardTamanBacaActivity.class);
        startActivity(a);
        finish();
    }

    @Override
    public void refresh() {
        Intent a = new Intent(this, RiwayatDonasiActivity.class);
        a.putExtra("className" , className);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed() {
        // ...
        if (className.equals("DashboardDonaturActivity"))
            this.goToDashboardDonatur();
        else
            this.goToDashboardTamanBaca();
        super.onBackPressed();
    }

    @Override
    public void onTerima(Donasi data) {
        data.setStatus("Diterima");
        presenter.onTerima(data.getId(),data);
    }

    @Override
    public void onSuccess() {
        SweetDialogs.commonSuccessWithIntent(this,"Berhasil Memuat Permintaan" , view->this.refresh());
    }
}
