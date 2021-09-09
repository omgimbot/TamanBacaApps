package omgimbot.app.sidangapps.features.donasi;

import omgimbot.app.sidangapps.features.donatur.model.Donasi;
import omgimbot.app.sidangapps.features.donatur.model.ResponDonatur;
import omgimbot.app.sidangapps.network.NetworkService;
import omgimbot.app.sidangapps.network.RestService;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RiwayatDonasiPresenter {
    final IRiwayatDonasiView view;
    public final Retrofit restService;

    public RiwayatDonasiPresenter(IRiwayatDonasiView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    public void listDonasiDonatur(String idUser) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).listDonasiDonatur(idUser)
                .enqueue(new Callback<ResponDonatur>() {
                    @Override
                    public void onResponse(retrofit2.Call<ResponDonatur> call, Response<ResponDonatur> response) {
                        view.hideLoadingIndicator();
                        view.onDataReady(response.body().getResult());
                    }

                    @Override
                    public void onFailure(retrofit2.Call<ResponDonatur> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    public void listDonasiTamanBaca(String idUser) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).listDonasiTamanBaca(idUser)
                .enqueue(new Callback<ResponDonatur>() {
                    @Override
                    public void onResponse(retrofit2.Call<ResponDonatur> call, Response<ResponDonatur> response) {
                        view.hideLoadingIndicator();
                        view.onDataReady(response.body().getResult());
                    }

                    @Override
                    public void onFailure(retrofit2.Call<ResponDonatur> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    public void onTerima(String id ,Donasi model) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).terima(id,model)
                .enqueue(new Callback<ResponDonatur>() {
                    @Override
                    public void onResponse(retrofit2.Call<ResponDonatur> call, Response<ResponDonatur> response) {
                        view.hideLoadingIndicator();
                        view.onSuccess();
                    }

                    @Override
                    public void onFailure(retrofit2.Call<ResponDonatur> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }
}
