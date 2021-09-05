package omgimbot.app.sidangapps.features.pengaduan.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pengaduan implements Serializable {

    @SerializedName("_id")
    private String id;

    @SerializedName("judul")
    private String judul;
    @SerializedName("idUser")
    private String idUser;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("status")
    private String status;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
