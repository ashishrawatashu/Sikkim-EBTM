
package in.nic.snt.starbus.ebtm.response.checkETMResponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckETMResponse {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("Result")
    @Expose
    private List<CheckETMResult> checkETMResult = null;
    @SerializedName("msg")
    @Expose
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<CheckETMResult> getResult() {
        return checkETMResult;
    }

    public void setResult(List<CheckETMResult> checkETMResult) {
        this.checkETMResult = checkETMResult;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
