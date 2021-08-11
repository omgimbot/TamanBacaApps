package omgimbot.app.sidangapps.features.auth.regist;

import java.util.List;

import omgimbot.app.sidangapps.features.auth.login.model.Users;

public interface IRegistView {
    void onRegistSuccess();

    void onRegistFailed(String rm);

    void onRegist();

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void goToLogin();
}
