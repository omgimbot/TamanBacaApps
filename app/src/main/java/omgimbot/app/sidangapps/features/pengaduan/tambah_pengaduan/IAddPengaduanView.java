package omgimbot.app.sidangapps.features.pengaduan.tambah_pengaduan;

public interface IAddPengaduanView {
    void onSuccess();

    void onFailed(String rm);

    void initView();

    void onInputPengaduan();

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void goToPengaduan();
}
