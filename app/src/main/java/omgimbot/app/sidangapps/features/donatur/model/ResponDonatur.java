package omgimbot.app.sidangapps.features.donatur.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import omgimbot.app.sidangapps.features.taman_baca.buku.model.Buku;

public class ResponDonatur {

    @SerializedName("rc")
    private String mRc;

    @SerializedName("message")
    private String mRm;

    @SerializedName("status")
    private Boolean mStatus;

    @SerializedName("result")
    private List<Donasi> result;


    public String getmRc() {
        return mRc;
    }

    public void setmRc(String mRc) {
        this.mRc = mRc;
    }

    public String getmRm() {
        return mRm;
    }

    public void setmRm(String mRm) {
        this.mRm = mRm;
    }

    public Boolean getSuccess() {
        return mStatus;
    }

    public void setSuccess(Boolean mStatus) {
        this.mStatus = mStatus;
    }

    public Boolean getmStatus() {
        return mStatus;
    }

    public void setmStatus(Boolean mStatus) {
        this.mStatus = mStatus;
    }

    public List<Donasi> getResult() {
        return result;
    }

    public void setResult(List<Donasi> result) {
        this.result = result;
    }
}
