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
import android.widget.Spinner;
import android.widget.Toast;

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

public class RegisterActivity extends AppCompatActivity implements IRegistView{
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


    RegistPresenter presenter;
    SweetAlertDialog sweetAlertDialog;
    String username , role , nidnDosenPembimbing;
    private LinkedHashMap<String, String> listdosen;
    private LinkedHashMapAdapter<String, String> adapterDosen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        presenter  = new RegistPresenter(this);

        this.initViews();
//        presenter.getListDosen();
    }

    public void initViews(){


        mSubmit.setOnClickListener(view->onRegist());

        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Loading ...");
    }

    @Override
    public void onRegistSuccess(){
        SweetDialogs.commonSuccessWithIntent(this , "Silahkan Login !",string -> this.goToLogin());
    }

    @Override
    public void onRegistFailed(String rm){
        SweetDialogs.commonError(this,rm , false);
    }





    @Override
    public void onRegist(){
        Users model = new Users();
        model.setNama(mNama.getText().toString());
        model.setNoTelpon(mTlp.getText().toString());
        model.setEmail(mEmail.getText().toString());
        model.setPassword(mPassword.getText().toString());
        model.setAlamat(mAlamat.getText().toString());
        presenter.signup(model);
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
