package omgimbot.app.sidangapps.features.donasi;

import omgimbot.app.sidangapps.features.donatur.add_donasi.IDonasiView;
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
//    public void deleteBuku(String id) {
//        view.showLoadingIndicator();
//        restService.create(NetworkService.class).deleteBuku(id)
//                .enqueue(new Callback<ResponDonatur>() {
//                    @Override
//                    public void onResponse(retrofit2.Call<ResponDonatur> call, Response<ResponDonatur> response) {
//                        view.hideLoadingIndicator();
//                        Log.d("deletednya" , "sukses") ;
//
//                        view.onDeleteSuccess();
//                    }
//
//                    @Override
//                    public void onFailure(retrofit2.Call<ResponDonatur> call, Throwable t) {
//                        view.hideLoadingIndicator();
//                        view.onNetworkError(t.getLocalizedMessage());
//                    }
//                });
//    }
//
//
//    @NonNull
//    private RequestBody createPartFromString(String descriptionString) {
//        return RequestBody.create(
//                MultipartBody.FORM, descriptionString);
//    }
}
