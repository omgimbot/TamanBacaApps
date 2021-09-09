package omgimbot.app.sidangapps.features.taman_baca.buku.tambahbuku;

import android.util.Log;

import com.google.gson.Gson;

import omgimbot.app.sidangapps.features.pengaduan.tambah_pengaduan.IAddPengaduanView;
import omgimbot.app.sidangapps.features.taman_baca.buku.model.Respon;
import omgimbot.app.sidangapps.features.auth.login.model.Users;
import omgimbot.app.sidangapps.features.auth.regist.IRegistView;
import omgimbot.app.sidangapps.features.taman_baca.buku.model.Buku;
import omgimbot.app.sidangapps.network.NetworkService;
import omgimbot.app.sidangapps.network.RestService;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddBukuPresenter {
    final IAddBukuView view;
    public final Retrofit restService;

    public AddBukuPresenter(IAddBukuView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    void inputBuku(Buku model) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).createBuku(model)
                .enqueue(new Callback<Respon>() {
                    @Override
                    public void onResponse(retrofit2.Call<Respon> call, Response<Respon> response) {
                        view.hideLoadingIndicator();
                        Log.d("resnya", new Gson().toJson(response.body()));
                        if (response.body().getSuccess()) {

                            view.onSuccess();
                        } else {
                            view.onFailed(response.body().getmRm());
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<Respon> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    void editBuku(String id , Buku model) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).updateBuku(id, model)
                .enqueue(new Callback<Respon>() {
                    @Override
                    public void onResponse(retrofit2.Call<Respon> call, Response<Respon> response) {
                        view.hideLoadingIndicator();
                        Log.d("resnya", new Gson().toJson(response.body()));
                        if (response.body().getSuccess()) {
                            view.onSuccess();
                        } else {
                            view.onFailed(response.body().getmRm());
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<Respon> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

}
