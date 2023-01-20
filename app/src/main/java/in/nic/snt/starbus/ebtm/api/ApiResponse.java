package in.nic.snt.starbus.ebtm.api;

import retrofit2.Response;

public interface ApiResponse<T>{

    public void onResponse(Response<T> response, String key);
    public void onError(Throwable t, String key);

}