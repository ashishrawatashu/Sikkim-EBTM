package in.nic.snt.starbus.ebtm.utils;

public class MySingleton {
        private static MySingleton mySingleton;
        public static MySingleton getInstance() {
            if (mySingleton == null)
                mySingleton = new MySingleton();
            return mySingleton;
        }

        public MySingleton() {

        }

        public String IMEI = "48112765";
        public String OFFICE_ID = "1011140000";
        public String routeLastUpdate = "";
        public String routeStationLastUpdate = "";
        public String fareStationLastUpdate = "";
        public String concessionLastUpdate = "";

        public boolean checkRouteLastUpdate = false;
        public boolean checkRouteStationLastUpdate = false;
        public boolean checkFareStationLastUpdate = false;
        public boolean checkConcessionLastUpdate = false;


        public String WAYBILLNO = "0711220003";
        public String operator = "UTC03750 test@123";
        public String con = "UTC00623 test@123";



        public String EBTTMCODE = "042299";

}
