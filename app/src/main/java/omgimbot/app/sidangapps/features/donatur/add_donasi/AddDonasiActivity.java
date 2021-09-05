package omgimbot.app.sidangapps.features.donatur.add_donasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import omgimbot.app.sidangapps.features.auth.login.model.Users;
import omgimbot.app.sidangapps.features.donatur.buku.BukuActivity;
import omgimbot.app.sidangapps.features.donatur.donasi_lain.DonasiLain;
import omgimbot.app.sidangapps.features.donatur.model.Donasi;
import omgimbot.app.sidangapps.features.taman_baca.TamanBacaActivity;
import omgimbot.app.sidangapps.features.taman_baca.buku.model.Buku;
import omgimbot.app.sidangapps.ui.SweetDialogs;
import omgimbot.app.sidangapps.ui.TopSnakbar;

public class AddDonasiActivity extends AppCompatActivity implements IDonasiView {
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;

    @BindView(R.id.mJudulBuku)
    TextView mJudulBuku;

    @BindView(R.id.mDeskripsi)
    TextView mDeskripsi;

    @BindView(R.id.mJumlah)
    TextView mJumlah;

    @BindView(R.id.mSubmit)
    Button mSubmit;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    LoginResponse mProfile;
    String nama, idUser;
    SweetAlertDialog sweetAlertDialog;
    Bundle bundle;
    String className = "default";
    String pengiriman = "kosong";
    Buku data;
    Users tamanBaca;
    DonasiPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donasi);
        ButterKnife.bind(this);
        presenter = new DonasiPresenter(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Donasi Buku");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = this.getIntent();
        bundle = intent.getExtras();
        if (bundle != null) {
            className = getIntent().getExtras().getString("className");
            if (className.equals("edit")) {
                data = (Buku) getIntent().getExtras().getSerializable("data");

                mJudulBuku.setText(data.getJudul());
                mDeskripsi.setText(data.getDeskripsi());
                mDeskripsi.setEnabled(false);
                mJudulBuku.setEnabled(false);
            } else {
                tamanBaca = (Users) getIntent().getExtras().getSerializable("data");
            }
        }
        this.initView();
    }

    @Override
    public void initView() {
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);
        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        idUser = (mProfile.getResult().get_id().contains(" "))
                ? mProfile.getResult().get_id() : mProfile.getResult().get_id();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioAmbil:
                        pengiriman = "Diambil";
                        break;
                    case R.id.radioAntar:
                        pengiriman = "Diantar";
                        break;
                }
            }
        });
        mSubmit.setOnClickListener(view -> this.onCreateDonasi());
    }

    @Override
    public void onCreateDonasi() {

        if (!mJumlah.getText().toString().equals(""))
            if (!pengiriman.equals("kosong")) {
                Donasi model = new Donasi();
                String jenisPengiriman = pengiriman;
                int jumlah = Integer.parseInt(mJumlah.getText().toString());
                if (className.equals("edit")) {
                    String idBuku = data.getId();


                    model.setJudul(data.getJudul());
                    model.setKategori(data.getKategori());
                    model.setTamanBaca(data.getNama());
                    model.setPengiriman(jenisPengiriman);
                    model.setJumlah(jumlah);
                    model.setIdUser(idUser);
                    model.setIdTamanBaca(data.getIdUser());
                }else{
                    model.setJudul(mJudulBuku.getText().toString());
                    model.setKategori("Umum");
                    model.setTamanBaca(tamanBaca.getNama());
                    model.setPengiriman(jenisPengiriman);
                    model.setJumlah(jumlah);
                    model.setIdUser(idUser);
                    model.setIdTamanBaca(tamanBaca.get_id());
                }
                presenter.CreateDonasi(model);
            } else
                TopSnakbar.showWarning(this, "anda belum memilih jenis pengiriman");
        else
            TopSnakbar.showWarning(this, "anda belum mengisi jumlah buku");
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

    public void goToDashboard() {
        Intent a = new Intent(this, BukuActivity.class);
        startActivity(a);
        finish();
    }
    public void gotoDonasiLain() {
        Intent a = new Intent(this, DonasiLain.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onCreateDonasiSuccess() {
        SweetDialogs.commonSuccess(this, "Berhasil Memuat Permintaan", true);
    }

    @Override
    public void onBackPressed() {
        // ...
        if(className.equals("edit"))
            this.goToDashboard();
        else
            this.gotoDonasiLain();

//        this.goToDashboard();
        super.onBackPressed();
    }

    @OnClick(R.id.mWa)
    public void goToWa() {
        final String phoneD = tamanBaca.getNoTelpon().replaceFirst("0", "+62");
        String url = "https://api.whatsapp.com/send?phone=" + phoneD;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setPackage("com.whatsapp");
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}