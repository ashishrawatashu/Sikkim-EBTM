package in.nic.snt.starbus.ebtm.adaptersOnClicks;

import in.nic.snt.starbus.ebtm.roomDataBase.entities.RoutesModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.TripsModel;

public interface TripsListOnClick {
    public void selectTrip(int position, TripsModel tripsModel);
}
