package apps.ricasares.com.data.di

import dagger.Component
import network.RedditApi
import javax.inject.Singleton

/**
 * Created by rush on 11/17/17.
 */
@Singleton
@Component(modules = arrayOf(DataModule::class, NetworkModule::class))
interface DataComponent {
    fun redditApi() : RedditApi
}