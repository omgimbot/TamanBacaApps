package omgimbot.app.sidangapps.features.taman_baca;

import androidx.annotation.NonNull;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import omgimbot.app.sidangapps.network.NetworkService;
import omgimbot.app.sidangapps.network.RestService;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TamanBacaPresenter {
    final ITamanBacaView view;
    public final Retrofit restService;

    public TamanBacaPresenter(ITamanBacaView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    public void showPerpus() {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).showPerpus()
                .enqueue(new Callback<omgimbot.app.sidangapps.features.taman_baca.model.Response>() {
                    @Override
                    public void onResponse(retrofit2.Call<omgimbot.app.sidangapps.features.taman_baca.model.Response> call, Response<omgimbot.app.sidangapps.features.taman_baca.model.Response> response) {
                        view.hideLoadingIndicator();
                        view.onDataReady(response.body().getResult());
                    }

                    @Override
                    public void onFailure(retrofit2.Call<omgimbot.app.sidangapps.features.taman_baca.model.Response> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }


    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }
}
