package apps.ricasares.com.data.di

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import network.RedditApi
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by rush on 11/17/17.
 */
@Module(includes = arrayOf(NetworkModule::class))
class DataModule {
    @Provides
    fun provideRedditApi(@Named("reddit_retrofit") retrofit: Retrofit) : RedditApi {
        return retrofit.create(RedditApi::class.java)
    }

    @Provides
    @Singleton
    @Named("reddit_endpoint")
    fun providesRedditEndpoint() : String {
        return "http:\\www.reddit.com"
    }

    @Provides
    @Singleton
    @Named("rx_adapter_scheduler")
    fun provideScheduler() : Scheduler {
        return Schedulers.io()
    }
}