package kohn.rx.votesmart;

import com.google.inject.AbstractModule;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class VoteSmartModule extends AbstractModule {
    @Override
    protected void configure() {
        super.configure();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.votesmart.org")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//takes calladapterfactory and changes into retrofit
                .build();

        VoteSmartService service = retrofit.create(VoteSmartService.class);

        bind(VoteSmartService.class).toInstance(service);
    }
}