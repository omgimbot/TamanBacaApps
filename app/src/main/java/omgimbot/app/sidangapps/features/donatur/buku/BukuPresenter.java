package omgimbot.app.sidangapps.features.donatur.buku;

import omgimbot.app.sidangapps.features.taman_baca.buku.model.Respon;
import omgimbot.app.sidangapps.network.NetworkService;
import omgimbot.app.sidangapps.network.RestService;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BukuPresenter {
    final IBukuView view;
    public final Retrofit restService;

    public BukuPresenter(IBukuView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    public void showBuku() {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).showBukuDonatur()
                .enqueue(new Callback<Respon>() {
                    @Override
                    public void onResponse(retrofit2.Call<Respon> call, Response<Respon> response) {
                        view.hideLoadingIndicator();
                        view.onDataReady(response.body().getResult());
                    }

                    @Override
                    public void onFailure(retrofit2.Call<Respon> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }
}
