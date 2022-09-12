package sample;

import java.io.IOException;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MyInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        String appVersion = appVersion();
        Request withHeaderRequest = request.newBuilder()
            .addHeader("User-Agent", appVersion)
            .build();

        Response response = chain.proceed(withHeaderRequest);
        return response;
    }

    public String appVersion() {
        Config conf = ConfigFactory.load();
        return conf.getString("app.version");
    }
}
