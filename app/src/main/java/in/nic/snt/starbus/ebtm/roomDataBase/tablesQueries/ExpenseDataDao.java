package in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import in.nic.snt.starbus.ebtm.roomDataBase.entities.ExpenseDataModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.ExpensesEarningModel;

@Dao
public interface ExpenseDataDao {

    @Insert
    void insertRecord(ExpenseDataModel expenseDataModel);



    @Query("DELETE FROM expenses_earning")
    void deleteSaveExpensesData();
}

