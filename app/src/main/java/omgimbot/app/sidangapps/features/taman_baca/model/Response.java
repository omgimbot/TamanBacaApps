package omgimbot.app.sidangapps.features.taman_baca.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("rc")
    private String mRc;

    @SerializedName("message")
    private String mRm;

    @SerializedName("status")
    private Boolean mStatus;

    @SerializedName("result")
    private List<TamanBaca> result;


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

    public List<TamanBaca> getResult() {
        return result;
    }

    public void setResult(List<TamanBaca> result) {
        this.result = result;
    }
}
