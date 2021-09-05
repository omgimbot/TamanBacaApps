package omgimbot.app.sidangapps.features.donatur.donasi_lain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import omgimbot.app.sidangapps.App;
import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.features.auth.login.model.Users;
import omgimbot.app.sidangapps.features.dashboard.DashboardDonaturActivity;
import omgimbot.app.sidangapps.features.donasi.DonasiAdapter;
import omgimbot.app.sidangapps.features.donatur.add_donasi.AddDonasiActivity;
import omgimbot.app.sidangapps.features.donatur.add_donasi.IDonasiView;
import omgimbot.app.sidangapps.features.donatur.buku.BukuActivity;
import omgimbot.app.sidangapps.features.donatur.buku.BukuPresenter;
import omgimbot.app.sidangapps.features.donatur.model.Donasi;
import omgimbot.app.sidangapps.features.taman_baca.buku.model.Buku;
import omgimbot.app.sidangapps.ui.SweetDialogs;

public class DonasiLain extends AppCompatActivity implements IDonasiLainView , DonasiLainAdapter.onSelected{
    List<Users> product = null;
    DonasiLainPresenter presenter;
    SweetAlertDialog sweetAlertDialog ;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSearch)
    SearchView mSearch;
    DonasiLainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donasi_lain);
        ButterKnife.bind(this);
        presenter = new DonasiLainPresenter(this);
        this.initView();
        presenter.showTamanBaca();
    }

    @Override
    public void initView() {
        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<Users> dataFilter = new ArrayList<>();
                for (Users data : product) {
                    String nama = data.getNama().toLowerCase();
                    int position = bruteforce(nama, query);
                    int endindex = position + 1;
                    if (position > -1) {
                        dataFilter.add(data);
                    }
                }
                adapter.setFilter(dataFilter);
                if(dataFilter.isEmpty())
                    Toast.makeText(DonasiLain.this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String nextText) {
                //Data akan berubah saat user menginputkan text/kata kunci pada SearchView
//                nextText = nextText.toLowerCase();
                if (nextText.length() < 1) {
                    ArrayList<Users> dataFilter = new ArrayList<>();
                    for (Users data : product) {
                        dataFilter.add(data);

                    }
                    adapter.setFilter(dataFilter);
                }
                return true;
            }
        });
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.clearFocus();
//        mTambah.setOnClickListener(view ->this.goToAddBuku());
    }

    public static int bruteforce(String text, String tobematched) {
        int length = text.length() + 1;//length of the text
        int plength = tobematched.length();//length of the pattern;
        //loop condition
        for (int i = 0; i < length - plength; i++) {
            //initialization of j
            int j = 0;
            while ((j < plength) && (text.charAt(i + j) == tobematched.charAt(j))) {
                j++;
            }
            if (j == plength) {
                return i;
            }
        }
        return -1;
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
    public void onDataReady(List<Users> result) {
        Log.d("data", new Gson().toJson(result));
        product = result ;
        adapter = new DonasiLainAdapter(product, this, this);
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
    public void goToDashboard() {
        Intent a = new Intent(this, DashboardDonaturActivity.class);
        startActivity(a);
        finish();
    }

    @Override
    public void refresh() {
        Intent a = new Intent(this, omgimbot.app.sidangapps.features.taman_baca.buku.listbuku.BukuActivity.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed() {
        // ...

        this.goToDashboard();
        super.onBackPressed();
    }

    @Override
    public void onDonasi(Users data) {
        Intent i = new Intent(this, AddDonasiActivity.class);
        i.putExtra("className", "donasiLain");
        i.putExtra("data", (Serializable) data);
        startActivity(i);
        finish();
    }

    @Override
    public void onDonasiSuccess() {
        SweetDialogs.commonSuccessWithIntent(this, "Berhasil Memuat Permintaan", view -> this.refresh());
    }
}
