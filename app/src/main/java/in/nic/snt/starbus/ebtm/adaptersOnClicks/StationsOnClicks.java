package in.nic.snt.starbus.ebtm.adaptersOnClicks;

import in.nic.snt.starbus.ebtm.models.StationsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.RoutesModel;

public interface StationsOnClicks {
    public void selectToStation(int position, StationsModel stationsModel);
    public void selectFromStation(int position, StationsModel stationsModel);
}
