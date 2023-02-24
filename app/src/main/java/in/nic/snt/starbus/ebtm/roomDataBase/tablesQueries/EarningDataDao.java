package in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import in.nic.snt.starbus.ebtm.adapters.EarningAdapter;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.EarningDataModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.ExpenseDataModel;

@Dao
public interface EarningDataDao {

    @Insert
    void insertRecord(EarningDataModel earningDataModel);



}
