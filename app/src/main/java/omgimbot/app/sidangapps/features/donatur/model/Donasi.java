package omgimbot.app.sidangapps.features.donatur.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Donasi implements Serializable {

    @SerializedName("_id")
    private String id;

    @SerializedName("judul")
    private String judul;

    @SerializedName("kategori")
    private String kategori;

    @SerializedName("tamanBaca")
    private String tamanBaca;

    @SerializedName("pengiriman")
    private String pengiriman;

    @SerializedName("idUser")
    private String idUser;

    @SerializedName("idTamanBaca")
    private String idTamanBaca;

    @SerializedName("jumlah")
    private int jumlah;

    @SerializedName("status")
    private String status;

    @SerializedName("created_at")
    private String created_at;

    public String getIdTamanBaca() {
        return idTamanBaca;
    }

    public void setIdTamanBaca(String idTamanBaca) {
        this.idTamanBaca = idTamanBaca;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
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

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getTamanBaca() {
        return tamanBaca;
    }

    public void setTamanBaca(String tamanBaca) {
        this.tamanBaca = tamanBaca;
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
