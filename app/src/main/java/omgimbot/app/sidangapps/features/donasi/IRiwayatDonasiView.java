package omgimbot.app.sidangapps.features.donasi;

import android.app.Activity;

import java.util.List;

import omgimbot.app.sidangapps.features.donatur.model.Donasi;

public interface IRiwayatDonasiView {

    void initView();

    void clearLightStatusBar(Activity activity);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(List<Donasi> result);

    void onNetworkError(String cause);

    void goToDashboardDonatur();
    void goToDashboardTamanBaca();

    void refresh();

    void onSuccess();
}
