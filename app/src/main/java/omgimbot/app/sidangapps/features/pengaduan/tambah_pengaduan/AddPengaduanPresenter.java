package omgimbot.app.sidangapps.features.pengaduan.tambah_pengaduan;

import android.util.Log;

import com.google.gson.Gson;

import omgimbot.app.sidangapps.features.pengaduan.model.Pengaduan;
import omgimbot.app.sidangapps.features.taman_baca.buku.model.Buku;
import omgimbot.app.sidangapps.features.pengaduan.model.PengaduanRespon;
import omgimbot.app.sidangapps.features.taman_baca.buku.tambahbuku.IAddBukuView;
import omgimbot.app.sidangapps.network.NetworkService;
import omgimbot.app.sidangapps.network.RestService;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddPengaduanPresenter {
    final IAddPengaduanView view;
    public final Retrofit restService;

    public AddPengaduanPresenter(IAddPengaduanView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }


    void inputPengaduan(Pengaduan model) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).createPengaduan(model)
                .enqueue(new Callback<PengaduanRespon>() {
                    @Override
                    public void onResponse(retrofit2.Call<PengaduanRespon> call, Response<PengaduanRespon> response) {
                        view.hideLoadingIndicator();
                        Log.d("resnya", new Gson().toJson(response.body()));
                        if (response.body().getSuccess()) {

                            view.onSuccess();
                        } else {
                            view.onFailed(response.body().getmRm());
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<PengaduanRespon> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }



}
