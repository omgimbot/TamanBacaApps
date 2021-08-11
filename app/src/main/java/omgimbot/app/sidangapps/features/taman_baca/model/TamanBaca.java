package omgimbot.app.sidangapps.features.taman_baca.model;

import com.google.gson.annotations.SerializedName;

public class TamanBaca {

    @SerializedName("idUser")
    private String idUser;

    @SerializedName("namaPerpus")
    private String namaPerpus;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("logoPerpus")
    private String logoPerpus;

    @SerializedName("created_at")
    private String created_at;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNamaPerpus() {
        return namaPerpus;
    }

    public void setNamaPerpus(String namaPerpus) {
        this.namaPerpus = namaPerpus;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getLogoPerpus() {
        return logoPerpus;
    }

    public void setLogoPerpus(String logoPerpus) {
        this.logoPerpus = logoPerpus;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
