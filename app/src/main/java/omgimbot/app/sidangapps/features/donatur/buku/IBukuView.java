package omgimbot.app.sidangapps.features.donatur.buku;

import android.app.Activity;

import java.util.List;

import omgimbot.app.sidangapps.features.taman_baca.buku.model.Buku;

public interface IBukuView {
    void initView();

    void clearLightStatusBar(Activity activity);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(List<Buku> result);

    void onNetworkError(String cause);

    void goToDashboard();

    void refresh();

    void onDonasiSuccess();
}
