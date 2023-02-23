package in.nic.snt.starbus.ebtm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.nic.snt.starbus.ebtm.R;
import in.nic.snt.starbus.ebtm.adapters.ExpanseAdapter;
import in.nic.snt.starbus.ebtm.databinding.ActivityEarningBinding;
import in.nic.snt.starbus.ebtm.databinding.ActivityExpenseBinding;
import in.nic.snt.starbus.ebtm.roomDataBase.AppDatabase;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.ExpensesEarningModel;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.ExpensesEarningsDao;

public class EarningActivity extends AppCompatActivity {

    ActivityEarningBinding earningBinding;
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        earningBinding = ActivityEarningBinding.inflate(getLayoutInflater());
        View view = earningBinding.getRoot();
        setContentView(view);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, getString(R.string.Sikkim_local_database_name)).allowMainThreadQueries().build();
        ExpensesEarningsDao expensesEarningsDao = db.expensesEarningsDao();

        List<ExpensesEarningModel> expenseDataList = new ArrayList<>();
        expenseDataList = expensesEarningsDao.getExpensesEarning();
        if(!expenseDataList.isEmpty()) {

            LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
            earningBinding.listViewExpense.setLayoutManager(linearLayoutManager);
            //ExpanseAdapter expanseAdapter = new ExpanseAdapter(getApplicationContext(), expenseDataList, this);
            //earningBinding.listViewExpense.setAdapter(expanseAdapter);

        }
        else
        {
            Toast.makeText(EarningActivity.this,"No Data",Toast.LENGTH_SHORT).show();
        }

    }


    }
