package omgimbot.app.sidangapps.features.donatur.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Donasi implements Serializable {

    @SerializedName("_id")
    private String id;

    @SerializedName("idBuku")
    private String idBuku;

    @SerializedName("pengiriman")
    private String pengiriman;

    @SerializedName("idUser")
    private String idUser;

    @SerializedName("jumlah")
    private int jumlah;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(String idBuku) {
        this.idBuku = idBuku;
    }

    public String getPengiriman() {
        return pengiriman;
    }

    public void setPengiriman(String pengiriman) {
        this.pengiriman = pengiriman;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
