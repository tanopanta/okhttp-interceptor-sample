package sample;

import java.io.IOException;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class UserAgentInterceptor implements Interceptor {
    private static String appVersion;
    public UserAgentInterceptor() {
        Config conf = ConfigFactory.load();
        appVersion = conf.getString("app.version");
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request withHeaderRequest = request.newBuilder()
            .addHeader("User-Agent", "myapp/" + appVersion)
            .build();

        Response response = chain.proceed(withHeaderRequest);
        return response;
    }
}
