package omgimbot.app.sidangapps.features.taman_baca.buku.listbuku;

import android.util.Log;

import androidx.annotation.NonNull;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import omgimbot.app.sidangapps.features.taman_baca.ITamanBacaView;
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

    public void showBuku(String idUser) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).showBuku(idUser)
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

    public void deleteBuku(String id) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).deleteBuku(id)
                .enqueue(new Callback<Respon>() {
                    @Override
                    public void onResponse(retrofit2.Call<Respon> call, Response<Respon> response) {
                        view.hideLoadingIndicator();
                        Log.d("deletednya" , "sukses") ;

                        view.onDeleteSuccess();
                    }

                    @Override
                    public void onFailure(retrofit2.Call<Respon> call, Throwable t) {
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
