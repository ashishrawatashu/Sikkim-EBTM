package in.nic.snt.starbus.ebtm.adaptersOnClicks;

import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

import in.nic.snt.starbus.ebtm.databinding.TicketBookingStationListItemBinding;
import in.nic.snt.starbus.ebtm.models.StationsModel;

public interface StationOnClickInDashBoard {

    public void selectToStation(int position, List<StationsModel> stationsModelList);
    public void selectFromStation(int position, List<StationsModel> stationsModelList);

}
