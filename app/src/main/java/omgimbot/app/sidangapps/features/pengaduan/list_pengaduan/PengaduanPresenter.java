package omgimbot.app.sidangapps.features.pengaduan.list_pengaduan;

import omgimbot.app.sidangapps.features.pengaduan.model.PengaduanRespon;
import omgimbot.app.sidangapps.network.NetworkService;
import omgimbot.app.sidangapps.network.RestService;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PengaduanPresenter {
    final IPengaduanView view;
    public final Retrofit restService;

    public PengaduanPresenter(IPengaduanView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    public void showPengaduan(String id) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).showPengaduan(id)
                .enqueue(new Callback<PengaduanRespon>() {
                    @Override
                    public void onResponse(retrofit2.Call<PengaduanRespon> call, Response<PengaduanRespon> response) {
                        view.hideLoadingIndicator();
                        view.onDataReady(response.body().getResult());
                    }

                    @Override
                    public void onFailure(retrofit2.Call<PengaduanRespon> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

}
