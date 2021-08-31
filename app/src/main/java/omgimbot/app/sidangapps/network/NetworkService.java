package omgimbot.app.sidangapps.network;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import omgimbot.app.sidangapps.Utils.CommonRespon;
import omgimbot.app.sidangapps.features.auth.login.model.Listdosen;
import omgimbot.app.sidangapps.features.auth.login.model.LoginResponse;
import omgimbot.app.sidangapps.features.auth.login.model.Users;
import omgimbot.app.sidangapps.features.donatur.model.Donasi;
import omgimbot.app.sidangapps.features.donatur.model.ResponDonatur;
import omgimbot.app.sidangapps.features.taman_baca.buku.model.Buku;
import omgimbot.app.sidangapps.features.taman_baca.buku.model.Respon;
import omgimbot.app.sidangapps.features.taman_baca.model.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface NetworkService {

    @FormUrlEncoded
    @POST("users/signin")
    Call<LoginResponse> signin(@FieldMap Map<String, Object> params);

    @POST("users/signup")
    Call<CommonRespon> signup(@Body Users registModel);

    @POST("donasi/create")
    Call<ResponDonatur> donasi(@Body Donasi model);

    @POST("buku/create")
    Call<Respon> createBuku(@Body Buku model);

    @PUT("buku/update/{id}")
    Call<Respon> updateBuku(@Path("id") String id,@Body Buku model);

    @GET("buku")
    Call<Respon> showBuku();

    @DELETE("buku/delete/{id}")
    Call<Respon> deleteBuku(@Path("id") String id);

    @FormUrlEncoded
    @POST("tolak")
    Call<CommonRespon> tolak(@FieldMap Map<String, Object> data);
//
//    @FormUrlEncoded
//    @POST("transaksi")
//    Call<CommonResponse> checkout(@FieldMap Map<String, Object> data);
//
    @GET("perpus/getallperpus")
    Call<Response> showPerpus();
//
//    @GET("cart/{nik}")
//    Call<Cart> getCart(@Path("nik") String nik);
//
//    @GET("profile/{nik}")
//    Call<ProfileResponse> getProfilePetani(@Path("nik") String nik);
//
//    @GET("saldo/{nik}")
//    Call<Saldo> getSaldo(@Path("nik") String nik);
//
//    @POST("transaksirutpetani")
//    Call<CommonRespon> createRut(@Body Result rut);
//
//    @FormUrlEncoded
//    @PUT("petaniedit/{nik}")
//    Call<LoginResponse> updateProfile(@Path("nik") String nik, @FieldMap Map<String, Object> data);
//
//    @FormUrlEncoded
//    @POST("rutpetani/{nik}")
//    Call<com.app.app4g.features.rut.model.RutResponse> getRut(@Path("nik") String nik, @FieldMap Map<String, Object> data);
//
//    @GET("kabupaten/{id}")
//    Call<AreaResponse> getKab(@Path("id") String id);
//
//    @GET("kecamatan/{id}")
//    Call<AreaResponse> getKec(@Path("id") String id);
//
//    @GET("desa/{id}")
//    Call<AreaResponse> getDesa(@Path("id") String id);
//
//    @GET("subsektor")
//    Call<AreaResponse> getSubsektor();
//
//    @GET("komoditasbysub/{idSub}")
//    Call<AreaResponse> getKomoditas(@Path("idSub") String idSub);
//
//    @GET("distinctkomoditas/{name}")
//    Call<DistincKomoditas> getDistincKomoditas(@Path("name") String name);
//
//    @GET("cekversion/{id}")
//    Call<CommonRespon> cekVersion(@Path("id") String id);
//
//    @GET("getalokasipetani/{nik}")
//    Call<AlokasiResponse> getAlokasi(@Path("nik") String nik);
//
//    @GET("getautp/{nik}")
//    Call<AutpResponse> getAutp(@Path("nik") String nik);
//
//    @GET("getbantuanpetani/{nik}")
//    Call<BantuanResponse> getBantuan(@Path("nik") String nik);
//
//    @GET("transaksipetanibynik/{nik}")
//    Call<TransaksiResponse> getTransaksi(@Path("nik") String nik);
//
//    @POST("users/signupandcreate")
//    Call<CommonRespon> daftarPetani(@Body RegistModel registModel);
//
//    @PUT("petani/updateaset/{nik}")
//    Call<LoginResponse> updateAset(@Path("nik") String nik, @Body AsetPetani model);
//
//    @FormUrlEncoded
//    @PUT("petani/createasset/{id}/{nik}")
//    Call<LoginResponse> createMt(@Path("id") String id, @Path("nik") String nik, @FieldMap Map<String, Object> data);
//
//    @PUT("hapusasetpetani/{idasset}/{nik}")
//    Call<LoginResponse> deleteAset(@Path("idasset") String idasset, @Path("nik") String nik);
//
//    @FormUrlEncoded
//    @POST("getkiosbyarea")
//    Call<KiosResponse> getKiosByArea(@FieldMap Map<String, Object> data);
//
//    @FormUrlEncoded
//    @PUT("updatepassword/{nik}")
//    Call<LoginResponse> onResetPassword(@Path("nik") String nik, @FieldMap Map<String, Object> data);
}
