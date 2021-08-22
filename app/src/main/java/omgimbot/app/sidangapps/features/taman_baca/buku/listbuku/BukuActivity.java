package omgimbot.app.sidangapps.features.taman_baca.buku.listbuku;

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
import android.widget.Button;

import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import omgimbot.app.sidangapps.App;
import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.features.dashboard.DashboardActivity;
import omgimbot.app.sidangapps.features.dashboard.DashboardAdminActivity;
import omgimbot.app.sidangapps.features.donasi.add_donasi.AddDonasiActivity;
import omgimbot.app.sidangapps.features.donasi.list_donasi.ListDonasiActivity;
import omgimbot.app.sidangapps.features.taman_baca.TamanBacaAdapter;
import omgimbot.app.sidangapps.features.taman_baca.TamanBacaPresenter;
import omgimbot.app.sidangapps.features.taman_baca.buku.model.Buku;
import omgimbot.app.sidangapps.features.taman_baca.buku.tambahbuku.AddBukuActivity;
import omgimbot.app.sidangapps.features.taman_baca.model.TamanBaca;
import omgimbot.app.sidangapps.ui.SweetDialogs;

public class BukuActivity extends AppCompatActivity implements IBukuView , BukuAdapter.onSelected {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.mTambah)
    Button mTambah;
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    SweetAlertDialog sweetAlertDialog;
    BukuAdapter adapter ;
    BukuPresenter presenter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buku);
        ButterKnife.bind(this);
        presenter= new BukuPresenter(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Buku");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.initView();
        presenter.showBuku();
    }

    @Override
    public void initView() {
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.clearFocus();
        mTambah.setOnClickListener(view ->this.goToAddBuku());
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
    public void onDataReady(List<Buku> result) {
        Log.d("data" , new Gson().toJson(result));
        adapter = new BukuAdapter(result, this,this);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
    public void goToDashboard() {
        Intent a = new Intent(this, DashboardAdminActivity.class);
        startActivity(a);
        finish();
    }

    @Override
    public void goToAddBuku() {
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

        this.goToDashboard();
        super.onBackPressed();
    }


//    @OnClick(R.id.mDonasi)
//    public void gootDonasi(){
//        Intent i = new Intent(this, AddDonasiActivity.class);
//        startActivity(i);
//        finish();
//    }

    @Override
    public void edit(Buku data) {
        Intent i = new Intent(this, AddBukuActivity.class);
        i.putExtra("className", "edit");
        i.putExtra("data", (Serializable) data);
        startActivity(i);
        finish();
    }

    @Override
    public void delete(Buku data) {
        presenter.deleteBuku(data.getId());
    }

    @Override
    public void onDeleteSuccess() {
        SweetDialogs.commonSuccessWithIntent(this , "Berhasil Memuat Permintaan" , view->this.refresh());
    }
}
