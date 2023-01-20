package in.nic.snt.starbus.ebtm.roomDataBase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;

import in.nic.snt.starbus.ebtm.databinding.ActivityRoomBinding;

public class RoomActivity extends AppCompatActivity {

    private ActivityRoomBinding activityRoomBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRoomBinding = ActivityRoomBinding.inflate(getLayoutInflater());
        View view = activityRoomBinding.getRoot();
        setContentView(view);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "user_db").allowMainThreadQueries().build();
//        UserDao userDao = db.userDao();
//        Boolean check = userDao.is_exist(666);
//        if (check == false) {
//            Log.e("ERROR","99");
//            userDao.insertRecord(new User(666, "Ashish", "Rawat"));
//        } else {
//            Log.e("ERROR","109");
//            userDao.insertRecord(new User(11111, "Ashish", "Rawat"));
//
//        }
//
//        List<User> userList = userDao.getAllUser();
//        for (User user : userList){
//            Log.e("USER_LIST",user.getFirstName()+"====>"+user.getFirstName()+"====>"+user.getUid());
//        }


    }


}