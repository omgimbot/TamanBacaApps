package omgimbot.app.sidangapps.features.donatur.donasi_lain;

import omgimbot.app.sidangapps.features.auth.login.model.UsersResponse;
import omgimbot.app.sidangapps.network.NetworkService;
import omgimbot.app.sidangapps.network.RestService;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DonasiLainPresenter {
    final IDonasiLainView view;
    public final Retrofit restService;

    public DonasiLainPresenter(IDonasiLainView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    public void showTamanBaca() {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).showTamanBaca()
                .enqueue(new Callback<UsersResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<UsersResponse> call, Response<UsersResponse> response) {
                        view.hideLoadingIndicator();
                        view.onDataReady(response.body().getmResult());
                    }

                    @Override
                    public void onFailure(retrofit2.Call<UsersResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }
}
