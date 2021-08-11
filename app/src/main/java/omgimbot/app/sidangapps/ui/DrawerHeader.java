package omgimbot.app.sidangapps.ui;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import omgimbot.app.sidangapps.App;
import omgimbot.app.sidangapps.Prefs;
import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.Utils.GsonHelper;
import omgimbot.app.sidangapps.features.auth.login.model.LoginResponse;


/**
 * Created by github.com/adip28 on 7/31/2018.
 */
@NonReusable
@Layout(R.layout.drawer_header)
public class DrawerHeader {
//    private LoginResponse mProfile;
    private Context mContext;
    String strId, strNik, strNotelp, strNama, strRole, strToken, strPotoPropil, namaPoktan, alamat, mt1, mt2, mt3, kecamatan, kabupaten, kota, provinsi;
    private LoginResponse mProfile;

    @View(R.id.profileImageView)
    private ImageView profileImage;

    @View(R.id.nameTxt)
    private TextView nameTxt;

    @View(R.id.emailTxt)
    private TextView emailTxt;

    public DrawerHeader(Context context) {
        mContext = context;

    }

    @Resolve
    private void onResolved() {
        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
//        String user_photo = (mProfile.getResult().getUser_photo().contains(" "))
//                ? mProfile.getResult().getUser_photo() : mProfile.getResult().getUser_photo();
        String nama = (mProfile.getResult().getNama().contains(" "))
                ? mProfile.getResult().getNama() : mProfile.getResult().getNama();
        String username = (mProfile.getResult().getEmail().contains(" "))
                ? mProfile.getResult().getEmail() : mProfile.getResult().getAlamat();
        nameTxt.setText(nama);
        emailTxt.setText(username);
//        if(!user_photo.equals(""))
//            Glide.with(mContext)
//                    .load(App.getApplication().getString(R.string.img_end_point))
//                    .into(profileImage);
//        else Glide.with(mContext)
//                .load(R.drawable.user)
//                .into(profileImage);


    }
}