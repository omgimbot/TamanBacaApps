package omgimbot.app.sidangapps.features.auth.login.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersResponse {

    @SerializedName("rc")
    private String mRc;

    @SerializedName("result")
    private List<Users> mResult;

    @SerializedName("message")
    private String mRm;

    @SerializedName("status")
    private Boolean mStatus;

    public String getRc() {return mRc; }

    public void setRc(String rc) { mRc = rc; }

    public List<Users> getmResult() {
        return mResult;
    }

    public void setmResult(List<Users> mResult) {
        this.mResult = mResult;
    }

    public String getRm() { return mRm; }

    public void setRm(String rm) { mRm = rm; }
    public Boolean getSuccess() { return mStatus; }
    public void  setSuccess(Boolean success) { mStatus = success; }

}
