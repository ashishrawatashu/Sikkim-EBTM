package in.nic.snt.starbus.ebtm.api;


import java.util.Map;

import in.nic.snt.starbus.ebtm.response.GetExpensesEarnings;
import in.nic.snt.starbus.ebtm.response.checkETMResponse.CheckETMResponse;
import in.nic.snt.starbus.ebtm.response.getConcessionResponse.GetConcessionResponse;
import in.nic.snt.starbus.ebtm.response.getFareStationsResponse.GetFareStationsResponse;
import in.nic.snt.starbus.ebtm.response.getRouteResponse.GetRouteResponse;
import in.nic.snt.starbus.ebtm.response.getRouteStationsResponse.GetRouteStationsResponse;
import in.nic.snt.starbus.ebtm.response.getWayBillDetailsResponse.GetWaybillDetailsResponse;
import in.nic.snt.starbus.ebtm.response.operatorLoginResponse.OperatorLoginResponse;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @FormUrlEncoded
    @POST("checkETM")
    Call<CheckETMResponse> checkETM(@FieldMap Map<String, String> checkETMRequest);

    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @FormUrlEncoded
    @POST("operatorLogin")
    Call<OperatorLoginResponse> OperatorLogin(@FieldMap Map<String, String> operatorLoginRequest);

    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @FormUrlEncoded
    @POST("get_fareStations")
    Call<GetFareStationsResponse> getFareStations(@FieldMap Map<String, String> getFareStationsRequest);

    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @FormUrlEncoded
    @POST("get_routeStations")
    Call<GetRouteStationsResponse> getRouteStations(@FieldMap Map<String, String> getRouteStationsRequest);

    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @FormUrlEncoded
    @POST("get_route")
    Call<GetRouteResponse> getRoute(@FieldMap Map<String, String> getRouteRequest);

    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @FormUrlEncoded
    @POST("get_concession")
    Call<GetConcessionResponse> getConcession(@FieldMap Map<String, String> getConcessionRequest);

    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @FormUrlEncoded
    @POST("get_waybillDeatils")
    Call<GetWaybillDetailsResponse> getWaybillDetails(@FieldMap Map<String, String> getWaybillDetailsRequest);

    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @FormUrlEncoded
    @POST("get_expenses_earnings")
    Call<GetExpensesEarnings> getExpensesEarnings(@FieldMap Map<String, String> getWaybillDetailsRequest);

}


