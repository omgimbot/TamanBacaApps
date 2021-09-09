package omgimbot.app.sidangapps.features.donatur.add_donasi;

public interface IDonasiView {
    void initView();

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onCreateDonasi();

    void onNetworkError(String cause);

    void goToDashboard();

    void onCreateDonasiSuccess();
}
