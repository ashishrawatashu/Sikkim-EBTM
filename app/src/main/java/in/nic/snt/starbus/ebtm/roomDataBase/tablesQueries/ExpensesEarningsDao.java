package in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import in.nic.snt.starbus.ebtm.roomDataBase.entities.ExpensesEarningModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.RoutesStationModel;

@Dao
public interface ExpensesEarningsDao {

    @Insert
    void insertRecord(ExpensesEarningModel expensesEarningModel);

    @Query("SELECT EXISTS(SELECT * FROM expenses_earning WHERE id= :id)")
    Boolean is_exist(int id);

    @Query("SELECT * FROM expenses_earning")
    List<ExpensesEarningModel> getExpensesEarning();

    @Query("SELECT * FROM expenses_earning WHERE id = :id")
    List<ExpensesEarningModel> getExpensesEarningId(int id);


    @Query("DELETE FROM expenses_earning")
    void deleteExpensesEarning();

}
