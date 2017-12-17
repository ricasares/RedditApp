package apps.ricasares.com.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by rush on 11/17/17.
 */
@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttp() : OkHttpClient {
        val okHttpBuilder: OkHttpClient.Builder = OkHttpClient().newBuilder()
        return okHttpBuilder.build()
    }

    @Provides
    @Singleton
    fun provideGson() : Gson {
        val gsonBuilder: GsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideRxJava2CallAdapterFactory(@Named("rx_adapter_scheduler") scheduler: Scheduler) : RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.createWithScheduler(scheduler)
    }

    @Provides
    @Singleton
    @Named("reddit_retrofit")
    fun provideRedditRetrofit(@Named("reddit_endpoint") redditEndpoint: String, okHttpClient: OkHttpClient,
                              gson: Gson, rxJavaCallAdapterFactory: RxJava2CallAdapterFactory) : Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .baseUrl(redditEndpoint)
                .build()
    }
}