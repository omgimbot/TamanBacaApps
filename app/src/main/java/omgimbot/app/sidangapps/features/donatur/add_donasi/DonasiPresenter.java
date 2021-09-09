package omgimbot.app.sidangapps.features.donatur.add_donasi;

import omgimbot.app.sidangapps.features.donatur.model.Donasi;
import omgimbot.app.sidangapps.features.donatur.model.ResponDonatur;
import omgimbot.app.sidangapps.network.NetworkService;
import omgimbot.app.sidangapps.network.RestService;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DonasiPresenter {
    final IDonasiView view;
    public final Retrofit restService;

    public DonasiPresenter(IDonasiView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    public void CreateDonasi(Donasi model) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).donasi(model)
                .enqueue(new Callback<ResponDonatur>() {
                    @Override
                    public void onResponse(retrofit2.Call<ResponDonatur> call, Response<ResponDonatur> response) {
                        view.hideLoadingIndicator();
                        view.onCreateDonasiSuccess();
                    }

                    @Override
                    public void onFailure(retrofit2.Call<ResponDonatur> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }
}
