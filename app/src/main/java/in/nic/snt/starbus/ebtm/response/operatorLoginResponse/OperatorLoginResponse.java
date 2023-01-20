
package in.nic.snt.starbus.ebtm.response.operatorLoginResponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import in.nic.snt.starbus.ebtm.roomDataBase.entities.OperatorLoginResultModel;

public class OperatorLoginResponse {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("Result")
    @Expose
    private List<OperatorLoginResultModel> operatorLoginResultModel = null;
    @SerializedName("msg")
    @Expose
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<OperatorLoginResultModel> getResult() {
        return operatorLoginResultModel;
    }

    public void setResult(List<OperatorLoginResultModel> operatorLoginResultModel) {
        this.operatorLoginResultModel = operatorLoginResultModel;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
