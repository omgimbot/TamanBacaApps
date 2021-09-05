package omgimbot.app.sidangapps.features.pengaduan.list_pengaduan;

import android.app.Activity;

import java.util.List;

import omgimbot.app.sidangapps.features.pengaduan.model.Pengaduan;

public interface IPengaduanView {
    void initView();

    void clearLightStatusBar(Activity activity);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(List<Pengaduan> result);

    void onNetworkError(String cause);


    void goToDashboardTamanBaca();

    void goToDashboardDonatur();

    void goToAddPengaduan();

    void refresh();

}
