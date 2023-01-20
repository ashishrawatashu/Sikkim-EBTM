
package in.nic.snt.starbus.ebtm.response.getConcessionResponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetConcessionResponse {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("Concessions")
    @Expose
    private List<Concession> concessions = null;
    @SerializedName("msg")
    @Expose
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Concession> getConcessions() {
        return concessions;
    }

    public void setConcessions(List<Concession> concessions) {
        this.concessions = concessions;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
