package omgimbot.app.sidangapps.network;

import java.util.Map;

import omgimbot.app.sidangapps.Utils.CommonRespon;
import omgimbot.app.sidangapps.features.auth.login.model.LoginResponse;
import omgimbot.app.sidangapps.features.auth.login.model.Users;
import omgimbot.app.sidangapps.features.auth.login.model.UsersResponse;
import omgimbot.app.sidangapps.features.donatur.model.Donasi;
import omgimbot.app.sidangapps.features.donatur.model.ResponDonatur;
import omgimbot.app.sidangapps.features.pengaduan.model.Pengaduan;
import omgimbot.app.sidangapps.features.pengaduan.model.PengaduanRespon;
import omgimbot.app.sidangapps.features.taman_baca.buku.model.Buku;
import omgimbot.app.sidangapps.features.taman_baca.buku.model.Respon;
import omgimbot.app.sidangapps.features.taman_baca.model.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NetworkService {

    @FormUrlEncoded
    @POST("users/signin")
    Call<LoginResponse> signin(@FieldMap Map<String, Object> params);

    @POST("users/signup")
    Call<CommonRespon> signup(@Body Users registModel);

    @POST("donasi/create")
    Call<ResponDonatur> donasi(@Body Donasi model);

    @GET("donasi/listDonasiDonatur/{id}")
    Call<ResponDonatur> listDonasiDonatur(@Path("id") String id);

    @GET("donasi/listDonasiTamanBaca/{id}")
    Call<ResponDonatur> listDonasiTamanBaca(@Path("id") String id);

    @POST("buku/create")
    Call<Respon> createBuku(@Body Buku model);

    @POST("pengaduan/create")
    Call<PengaduanRespon> createPengaduan(@Body Pengaduan model);

    @PUT("buku/update/{id}")
    Call<Respon> updateBuku(@Path("id") String id,@Body Buku model);

    @PUT("donasi/update/{id}")
    Call<ResponDonatur> terima(@Path("id") String id,@Body Donasi model);

    @GET("bukutamanbaca/{id}")
    Call<Respon> showBuku(@Path("id") String id);

    @GET("buku")
    Call<Respon> showBukuDonatur();

    @GET("tamanBaca")
    Call<UsersResponse> showTamanBaca();

    @GET("pengaduan/{id}")
    Call<PengaduanRespon> showPengaduan(@Path("id") String id);

    @DELETE("buku/delete/{id}")
    Call<Respon> deleteBuku(@Path("id") String id);

    @FormUrlEncoded
    @POST("tolak")
    Call<CommonRespon> tolak(@FieldMap Map<String, Object> data);

    @GET("perpus/getallperpus")
    Call<Response> showPerpus();

}
