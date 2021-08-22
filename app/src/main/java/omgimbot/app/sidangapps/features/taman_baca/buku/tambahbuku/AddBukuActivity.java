package omgimbot.app.sidangapps.features.taman_baca.buku.tambahbuku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import omgimbot.app.sidangapps.App;
import omgimbot.app.sidangapps.Prefs;
import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.Utils.GsonHelper;
import omgimbot.app.sidangapps.features.auth.login.model.LoginResponse;
import omgimbot.app.sidangapps.features.dashboard.DashboardAdminActivity;
import omgimbot.app.sidangapps.features.taman_baca.buku.listbuku.BukuActivity;
import omgimbot.app.sidangapps.features.taman_baca.buku.model.Buku;
import omgimbot.app.sidangapps.ui.SweetDialogs;
import omgimbot.app.sidangapps.ui.TopSnakbar;

public class AddBukuActivity extends AppCompatActivity implements IAddBukuView {
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    @BindView(R.id.mJudul)
    EditText mJudul;

    @BindView(R.id.mKategori)
    EditText mKategori;

    @BindView(R.id.mDeskripsi)
    EditText mDeskripsi;

    @BindView(R.id.mJumlah)
    EditText mJumlah;
    AddBukuPresenter presenter;
    LoginResponse mProfile;
    String nama , id;
    SweetAlertDialog sweetAlertDialog;
    Bundle bundle;
    String className = "default" ;
    Buku data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_buku);
        ButterKnife.bind(this);
        presenter = new AddBukuPresenter(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Input Buku");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        nama = (mProfile.getResult().getNama().contains(" "))
                ? mProfile.getResult().getNama() : mProfile.getResult().getNama();
        Intent intent = this.getIntent();
        bundle = intent.getExtras();
        if (bundle != null) {
            className = getIntent().getExtras().getString("className");
            if (className.equals("edit")) {
                data = (Buku) getIntent().getExtras().getSerializable("data");

                mJudul.setText(data.getJudul());
                mKategori.setText(data.getKategori());
                mDeskripsi.setText(data.getDeskripsi());
                mJumlah.setText(String.valueOf(data.getJumlah()));
                id = data.getId();
            }
        }
        this.initView();
    }

    @Override
    public void initView() {
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);

    }

    @OnClick(R.id.mSubmit)
    public void onSubmit() {
        this.onInputBuku();
    }

    @Override
    public void onInputBuku() {
        String judul = mJudul.getText().toString();
        String kategori = mKategori.getText().toString();
        String deskripsi = mDeskripsi.getText().toString();
        String jumlah = mJumlah.getText().toString();
        if (!judul.equals(""))
            if (!kategori.equals(""))
                if (!deskripsi.equals(""))
                    if (!jumlah.equals("")) {
                        Buku model = new Buku();
                        model.setNama(nama);
                        model.setJudul(judul);
                        model.setDeskripsi(deskripsi);
                        model.setKategori(kategori);
                        model.setJumlah(Integer.parseInt(jumlah));
                        if (className.equals("edit")){
                            Log.d("dataedit" , new Gson().toJson(data.getId()));
                            presenter.editBuku(id,model);}
                        else
                            presenter.inputBuku(model);
                    } else
                        TopSnakbar.showWarning(this, "Jumlah Tidak Boleh Kosong !");
                else
                    TopSnakbar.showWarning(this, "Deskripsi Tidak Boleh Kosong !");
            else
                TopSnakbar.showWarning(this, "Kategori Tidak Boleh Kosong !");
        else
            TopSnakbar.showWarning(this, "Judul Tidak Boleh Kosong !");

    }

    @Override
    public void onSuccess() {
        SweetDialogs.commonSuccess(this, "Berhasil Memuat Permintaan", true);
    }

    @Override
    public void onFailed(String rm) {
        SweetDialogs.commonWarning(this, "Gagal Memuat Permintaan", rm, false);
    }


    @Override
    public void onNetworkError(String cause) {
        Log.d("Error", cause);
        SweetDialogs.endpointError(this);
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
    public void goToBuku() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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
}
