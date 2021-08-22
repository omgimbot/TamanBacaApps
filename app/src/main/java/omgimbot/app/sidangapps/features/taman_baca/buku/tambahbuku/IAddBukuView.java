package omgimbot.app.sidangapps.features.taman_baca.buku.tambahbuku;

public interface IAddBukuView {
    void onSuccess();

    void onFailed(String rm);

    void initView();

    void onInputBuku();

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void goToBuku();
}
