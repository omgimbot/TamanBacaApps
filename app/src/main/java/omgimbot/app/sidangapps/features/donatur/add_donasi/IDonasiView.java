package omgimbot.app.sidangapps.features.donatur.add_donasi;

import android.app.Activity;

import java.util.List;

import omgimbot.app.sidangapps.features.donatur.model.Donasi;
import omgimbot.app.sidangapps.features.taman_baca.buku.model.Buku;

public interface IDonasiView {
    void initView();

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onCreateDonasi();

    void onNetworkError(String cause);

    void goToDashboard();

    void onCreateDonasiSuccess();
}
