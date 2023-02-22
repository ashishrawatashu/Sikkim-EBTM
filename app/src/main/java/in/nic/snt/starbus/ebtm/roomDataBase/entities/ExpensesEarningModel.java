
package in.nic.snt.starbus.ebtm.roomDataBase.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "expenses_earning")
public class ExpensesEarningModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @SerializedName("ID")
    @Expose
    private Integer id;

    @ColumnInfo(name = "name")
    @SerializedName("NAME")
    @Expose
    private String name;

    @ColumnInfo(name = "type")
    @SerializedName("TYPE")
    @Expose
    private String type;

    @ColumnInfo(name = "status")
    @SerializedName("STATUS")
    @Expose
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
