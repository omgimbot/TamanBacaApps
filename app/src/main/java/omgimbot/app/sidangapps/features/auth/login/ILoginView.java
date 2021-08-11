package omgimbot.app.sidangapps.features.auth.login;

import omgimbot.app.sidangapps.features.auth.login.model.LoginResponse;

public interface ILoginView {
    void initViews();

    void onSigninSuccess(LoginResponse response);

    void onSigninFailed(String rm);

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();
}
