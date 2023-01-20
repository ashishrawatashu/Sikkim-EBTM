package in.nic.snt.starbus.ebtm.roomDataBase.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RoutesModel {

    @PrimaryKey(autoGenerate = false)
    public int routeId;

    @ColumnInfo(name = "routeName")
    public String routeName;

    public RoutesModel(int routeId, String routeName) {
        this.routeId = routeId;
        this.routeName = routeName;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }
}
