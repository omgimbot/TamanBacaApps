package omgimbot.app.sidangapps.features.auth.login.model;

import com.google.gson.annotations.SerializedName;

public class Listdosen {
    @SerializedName("nama")
    private String nama;
    @SerializedName("nidn")
    private String username;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
