
package in.nic.snt.starbus.ebtm.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.nic.snt.starbus.ebtm.roomDataBase.entities.ExpensesEarningModel;

public class GetExpensesEarnings {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("ExpensesEarnings")
    @Expose
    private List<ExpensesEarningModel> expensesEarnings;
    @SerializedName("msg")
    @Expose
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ExpensesEarningModel> getExpensesEarnings() {
        return expensesEarnings;
    }

    public void setExpensesEarnings(List<ExpensesEarningModel> expensesEarnings) {
        this.expensesEarnings = expensesEarnings;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
