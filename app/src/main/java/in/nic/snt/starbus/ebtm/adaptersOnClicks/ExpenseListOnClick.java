package in.nic.snt.starbus.ebtm.adaptersOnClicks;

import in.nic.snt.starbus.ebtm.roomDataBase.entities.ExpensesEarningModel;

public interface ExpenseListOnClick {


    public void selectSaveExpense(int position, ExpensesEarningModel expenseModel, String amount, String remark);

}
