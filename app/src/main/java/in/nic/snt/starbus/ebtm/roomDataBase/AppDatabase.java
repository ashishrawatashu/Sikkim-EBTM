package in.nic.snt.starbus.ebtm.roomDataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import in.nic.snt.starbus.ebtm.roomDataBase.entities.ConcessionsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.ConductorDriverTiModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.CurrentTripsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.CurrentUserLoginModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.DataLastDateUpdationDateModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.MachineCurrentStatusModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.MachineStatusModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.OnBoardingStatusModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.OperatorLoginResultModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.ParametersModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.RouteFareModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.RoutesModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.RoutesStationModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.TripsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.WaybillModel;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.ConcessionDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.ConductorDriverTIDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.CurrentTripsDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.CurrentUserLoginStatusDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.DataUpdationLastDateDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.MachineCurrentStatusDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.MachineStatusDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.OnBoardingStatusDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.OperatorLoginDetailsDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.RouteFareDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.RoutesDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.RoutesStationDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.TripsDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.WayBillDao;

@Database(entities = {
        ConcessionsModel.class,
        MachineCurrentStatusModel.class,
        MachineStatusModel.class,
        ParametersModel.class,
        RouteFareModel.class,
        RoutesModel.class,
        RoutesStationModel.class,
        DataLastDateUpdationDateModel.class,
        ConductorDriverTiModel.class,
        TripsModel.class,
        WaybillModel.class,
        OperatorLoginResultModel.class,
        CurrentTripsModel.class,
        CurrentUserLoginModel.class,
        OnBoardingStatusModel.class,

},
        version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MachineStatusDao machineStatusModel();

    public abstract DataUpdationLastDateDao dataLastDateUpdationDateModel();

    public abstract RoutesDao routesDao();

    public abstract ConcessionDao concessionDao();

    public abstract RouteFareDao routeFareDao();

    public abstract RoutesStationDao routesStationDao();

    public abstract ConductorDriverTIDao conductorDriverTIDao();

    public abstract TripsDao tripsDao();

    public abstract WayBillDao wayBillDao();

    public abstract OperatorLoginDetailsDao operatorLoginDetailsDao();

    public abstract MachineCurrentStatusDao machineCurrentStatusDao();

    public abstract CurrentTripsDao currentTripsDao();

    public abstract CurrentUserLoginStatusDao currentUserLoginStatusDao();

    public abstract OnBoardingStatusDao onBoardingStatusDao();



}