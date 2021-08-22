package omgimbot.app.sidangapps.features.auth.regist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.Utils.LinkedHashMapAdapter;
import omgimbot.app.sidangapps.features.auth.login.LoginActivity;
import omgimbot.app.sidangapps.features.auth.login.LoginPresenter;
import omgimbot.app.sidangapps.features.auth.login.model.Users;
import omgimbot.app.sidangapps.ui.SweetDialogs;
import omgimbot.app.sidangapps.ui.TopSnakbar;

public class RegisterActivity extends AppCompatActivity implements IRegistView {
    @BindView(R.id.mSubmit)
    Button mSubmit;
    @BindView(R.id.mNama)
    EditText mNama;
    @BindView(R.id.mEmail)
    EditText mEmail;
    @BindView(R.id.mTlp)
    EditText mTlp;
    @BindView(R.id.mAlamat)
    EditText mAlamat;
    @BindView(R.id.mPassword)
    EditText mPassword;
    @BindView(R.id.mRePassword)
    EditText mRePassword;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.TextinputTamanBaca)
    TextInputLayout TextinputTamanBaca;
    RegistPresenter presenter;
    SweetAlertDialog sweetAlertDialog;
    String username, role, nidnDosenPembimbing;
    private LinkedHashMap<String, String> listdosen;
    private LinkedHashMapAdapter<String, String> adapterDosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        presenter = new RegistPresenter(this);

        this.initViews();
//        presenter.getListDosen();
    }

    public void initViews() {
        role = "kosong" ;
        mSubmit.setOnClickListener(view -> onRegist());
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Loading ...");
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                  @Override
                                                  public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                      switch (checkedId) {
                                                          case R.id.radioDonatur:
                                                              role = "Donatur";
                                                              TextinputTamanBaca.setHint("Nama Lengkap");
                                                              break;
                                                          case R.id.radioTamanBaca:
                                                              role = "TamanBaca";
                                                              TextinputTamanBaca.setHint("Nama Taman Baca");
                                                              break;

                                                      }

                                                  }
                                              }
        );
    }

    @Override
    public void onRegistSuccess() {
        SweetDialogs.commonSuccessWithIntent(this, "Silahkan Login !", string -> this.goToLogin());
    }

    @Override
    public void onRegistFailed(String rm) {
        SweetDialogs.commonError(this, rm, false);
    }


    @Override
    public void onRegist() {
        if (mNama.getText().toString().equals("")) {
            TopSnakbar.showWarning(this, "Nama Tidak boleh kosong !");
        } else if (mEmail.getText().toString().equals(""))
            TopSnakbar.showWarning(this, "email Tidak boleh kosong !");
        else if (mTlp.getText().toString().equals(""))
            TopSnakbar.showWarning(this, "nomor telepon Tidak boleh kosong !");
        else if (mAlamat.getText().toString().equals(""))
            TopSnakbar.showWarning(this, "alamat Tidak boleh kosong !");
        else if (mPassword.getText().toString().equals(""))
            TopSnakbar.showWarning(this, "password Tidak boleh kosong !");
        else if (mRePassword.getText().toString().equals(""))
            TopSnakbar.showWarning(this, "harap ulangi password anda !");
        else if (role.equals("kosong"))
            TopSnakbar.showWarning(this, "harap memilih Taman Baca atau donatur  !");
        else {
            if (mPassword.getText().toString().equals(mRePassword.getText().toString())) {
                Users model = new Users();
                model.setNama(mNama.getText().toString());
                model.setNoTelpon(mTlp.getText().toString());
                model.setEmail(mEmail.getText().toString());
                model.setPassword(mPassword.getText().toString());
                model.setAlamat(mAlamat.getText().toString());
                model.setRole(role);
                presenter.signup(model);
            }else{
                TopSnakbar.showWarning(this,"password yang anda masukkan tidak sama");
            }
        }
    }

    @Override
    public void onNetworkError(String cause) {
        Log.e("errornya", cause);
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
    public void goToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
