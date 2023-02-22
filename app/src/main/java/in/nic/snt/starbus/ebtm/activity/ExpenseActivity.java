package in.nic.snt.starbus.ebtm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.nic.snt.starbus.ebtm.R;
import in.nic.snt.starbus.ebtm.adapters.ExpanseAdapter;
import in.nic.snt.starbus.ebtm.adaptersOnClicks.ExpenseListOnClick;
import in.nic.snt.starbus.ebtm.databinding.ActivityExpenseBinding;
import in.nic.snt.starbus.ebtm.roomDataBase.AppDatabase;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.ExpensesEarningModel;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.ExpensesEarningsDao;

public class ExpenseActivity extends AppCompatActivity implements ExpenseListOnClick {

    ActivityExpenseBinding expenseBinding;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        expenseBinding = ActivityExpenseBinding.inflate(getLayoutInflater());
        View view = expenseBinding.getRoot();
        setContentView(view);

        expenseBinding.imgDownArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v = (expenseBinding.llExpandDetails.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
                TransitionManager.beginDelayedTransition(expenseBinding.card1, new AutoTransition());
                expenseBinding.llExpandDetails.setVisibility(v);
                expenseBinding.imgDownArrow.setRotation(expenseBinding.imgDownArrow.getRotation() + 180);
            }
        });

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, getString(R.string.Sikkim_local_database_name)).allowMainThreadQueries().build();
        ExpensesEarningsDao expensesEarningsDao = db.expensesEarningsDao();

        List<ExpensesEarningModel> expenseDataList = new ArrayList<>();
        expenseDataList = expensesEarningsDao.getExpensesEarning();
        if(!expenseDataList.isEmpty()) {
            LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
            expenseBinding.listViewExpense.setLayoutManager(linearLayoutManager);
            ExpanseAdapter expanseAdapter = new ExpanseAdapter(getApplicationContext(), expenseDataList, this);
            expenseBinding.listViewExpense.setAdapter(expanseAdapter);
        }
        else
        {
            Toast.makeText(ExpenseActivity.this,"No Data",Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    public void selectSaveExpense(int position, ExpensesEarningModel expenseModel, String  amount, String remark) {
        Toast.makeText(getApplicationContext(),"selectSaveExpense",Toast.LENGTH_LONG).show();
    }

}