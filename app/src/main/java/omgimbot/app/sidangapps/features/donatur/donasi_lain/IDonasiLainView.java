package omgimbot.app.sidangapps.features.donatur.donasi_lain;

import android.app.Activity;

import java.util.List;

import omgimbot.app.sidangapps.features.auth.login.model.Users;
import omgimbot.app.sidangapps.features.taman_baca.buku.model.Buku;

public interface IDonasiLainView {
    void initView();

    void clearLightStatusBar(Activity activity);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(List<Users> result);

    void onNetworkError(String cause);

    void goToDashboard();

    void refresh();

    void onDonasiSuccess();
}
