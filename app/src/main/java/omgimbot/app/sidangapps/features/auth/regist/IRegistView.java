package omgimbot.app.sidangapps.features.auth.regist;

public interface IRegistView {

    void onRegistSuccess();

    void onRegistFailed(String rm);

    void onRegist();

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void goToLogin();

}
