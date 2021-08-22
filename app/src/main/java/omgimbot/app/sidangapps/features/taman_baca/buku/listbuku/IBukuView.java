package omgimbot.app.sidangapps.features.taman_baca.buku.listbuku;

import android.app.Activity;

import java.util.List;

import omgimbot.app.sidangapps.features.taman_baca.buku.model.Buku;
import omgimbot.app.sidangapps.features.taman_baca.model.TamanBaca;

public interface IBukuView {
    void initView();

    void clearLightStatusBar(Activity activity);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(List<Buku> result);

    void onNetworkError(String cause);

    void goToDashboard();

    void goToAddBuku();

    void refresh();

    void onDeleteSuccess();
}
