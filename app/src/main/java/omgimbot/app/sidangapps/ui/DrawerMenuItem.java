package omgimbot.app.sidangapps.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import omgimbot.app.sidangapps.About;
import omgimbot.app.sidangapps.App;
import omgimbot.app.sidangapps.Profile;
import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.features.auth.login.LoginActivity;

/**
 * Created by github.com/adip28 on 7/31/2018.
 */
@Layout(R.layout.drawer_item)
public class DrawerMenuItem {

    public static final int DRAWER_MENU_ITEM_PROFILE = 1;
    public static final int DRAWER_MENU_ITEM_ABOUT = 3;
    public static final int DRAWER_MENU_ITEM_LOGOUT = 6;

    private int mMenuPosition;
    private Context mContext;
    private DrawerCallBack mCallBack;

    @View(R.id.itemNameTxt)
    private TextView itemNameTxt;

    @View(R.id.itemIcon)
    private ImageView itemIcon;

    public DrawerMenuItem(Context context, int menuPosition) {
        mContext = context;
        mMenuPosition = menuPosition;
    }

    @Resolve

    private void onResolved() {
        switch (mMenuPosition){
            case DRAWER_MENU_ITEM_PROFILE:
                itemNameTxt.setText("Profile");
                itemIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_research));
                break;
            case DRAWER_MENU_ITEM_ABOUT:
                itemNameTxt.setText("Tentang Aplikasi");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_info));
                break;
            case DRAWER_MENU_ITEM_LOGOUT:
                itemIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_sad));
                itemNameTxt.setText("Keluar");
                break;
        }
    }

    @Click(R.id.mainView)
    private void onMenuItemClick(){

        switch (mMenuPosition){
            case DRAWER_MENU_ITEM_PROFILE:
                goToProfile();
                if(mCallBack != null)mCallBack.onProfileMenuSelected();
                break;
            case DRAWER_MENU_ITEM_ABOUT:
                goToAbout();
                if(mCallBack != null)mCallBack.onRequestMenuSelected();
                break;
            case DRAWER_MENU_ITEM_LOGOUT:
                SweetDialogs.commonLogout((Activity) mContext, "SIGNOUT",
                        "Apakah Anda yakin akan signout dari aplikasi?",
                        string ->logout());
                if(mCallBack != null)mCallBack.onLogoutMenuSelected();
                break;
        }
    }

    public void setDrawerCallBack(DrawerCallBack callBack) {
        mCallBack = callBack;
    }

    public interface DrawerCallBack{
        void onProfileMenuSelected();
        void onRequestMenuSelected();
        void onGroupsMenuSelected();
        void onMessagesMenuSelected();
        void onNotificationsMenuSelected();
        void onResetPasswordMenuSelected();
        void onTermsMenuSelected();
        void onLogoutMenuSelected();
    }

    public void logout(){
        App.getPref().clear();
        mContext.startActivity(new Intent(mContext, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        ((Activity)mContext).finish();
        Toast.makeText(mContext, "Berhasil keluar...", Toast.LENGTH_LONG).show();
    }

    public void goToProfile(){
        mContext.startActivity(new Intent(mContext, Profile.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        ((Activity)mContext).finish();
    }

    public void goToAbout(){
        mContext.startActivity(new Intent(mContext, About.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        ((Activity)mContext).finish();
    }


}