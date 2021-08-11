package omgimbot.app.sidangapps.features.taman_baca;

import android.app.Activity;

import java.util.List;
import omgimbot.app.sidangapps.features.taman_baca.model.TamanBaca;

public interface ITamanBacaView {
    void initView();

    void clearLightStatusBar(Activity activity);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(List<TamanBaca> result);

    void onNetworkError(String cause);

    void goToDashboard();
}
