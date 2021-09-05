package omgimbot.app.sidangapps.features.pengaduan.tambah_pengaduan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import omgimbot.app.sidangapps.App;
import omgimbot.app.sidangapps.Prefs;
import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.Utils.GsonHelper;
import omgimbot.app.sidangapps.features.auth.login.model.LoginResponse;
import omgimbot.app.sidangapps.features.pengaduan.list_pengaduan.PengaduanActivity;
import omgimbot.app.sidangapps.features.pengaduan.model.Pengaduan;
import omgimbot.app.sidangapps.features.taman_baca.buku.listbuku.BukuActivity;
import omgimbot.app.sidangapps.features.taman_baca.buku.model.Buku;
import omgimbot.app.sidangapps.features.taman_baca.buku.tambahbuku.AddBukuPresenter;
import omgimbot.app.sidangapps.ui.SweetDialogs;
import omgimbot.app.sidangapps.ui.TopSnakbar;

public class AddPengaduan extends AppCompatActivity implements IAddPengaduanView {
    SweetAlertDialog sweetAlertDialog;
    AddPengaduanPresenter presenter;

    @BindView(R.id.mJudul)
    EditText mJudul;
    @BindView(R.id.mDeskripsi)
    EditText mDeskripsi;
    LoginResponse mProfile;
    String idUser,className ;
    Bundle bundle ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pengaduan);
        ButterKnife.bind(this);
        presenter = new AddPengaduanPresenter(this);
        this.initView();
        Intent intent = this.getIntent();
        bundle = intent.getExtras();
        if (bundle != null) {
            className = getIntent().getExtras().getString("className");
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
    }

    @OnClick(R.id.mSubmit)
    public void onSubmit() {
        this.onInputPengaduan();
    }

    @Override
    public void onInputPengaduan() {
        String judul = mJudul.getText().toString();
        String deskripsi = mDeskripsi.getText().toString();
        if (!judul.equals(""))
            if (!deskripsi.equals("")) {
                Pengaduan model = new Pengaduan();
                model.setIdUser(idUser);
                model.setJudul(judul);
                model.setDeskripsi(deskripsi);
                presenter.inputPengaduan(model);
            } else
                TopSnakbar.showWarning(this, "Jumlah Tidak Boleh Kosong !");
        else
            TopSnakbar.showWarning(this, "Judul Tidak Boleh Kosong !");

    }

    @Override
    public void onSuccess() {
        SweetDialogs.commonSuccessWithIntent(this, "Berhasil Memuat Permintaan", View -> this.goToPengaduan());
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

    @Override
    public void goToPengaduan() {
        Intent a = new Intent(this, PengaduanActivity.class);
        a.putExtra("className" , className);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed() {
        // ...

        this.goToPengaduan();
        Animatoo.animateSlideRight(AddPengaduan.this);
        super.onBackPressed();
    }


}
