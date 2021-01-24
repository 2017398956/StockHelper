package personal.nfl.stockhelper.utils.net;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nfl on 2017/9/21.
 */
public class CustomCallback<T> implements Callback<T> {

    public void success(T t) {
        System.out.println(t.toString());
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            success(response.body());
        }
        System.out.println(response.code());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }
}
