package in.nic.snt.starbus.ebtm.adaptersOnClicks;

import android.location.Location;

import in.nic.snt.starbus.ebtm.roomDataBase.entities.ExpensesEarningModel;

public interface ExpenseListOnClick {


    public void selectCard(int position, ExpensesEarningModel expenseModel);

}
