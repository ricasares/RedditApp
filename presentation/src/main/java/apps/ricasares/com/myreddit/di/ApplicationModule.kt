package apps.ricasares.com.myreddit.di

import android.app.Application
import android.content.Context
import apps.ricasares.com.data.cache.db.RedditDb
import apps.ricasares.com.data.di.DataModule
import apps.ricasares.com.domain.schedulers.ObserveOn
import apps.ricasares.com.domain.schedulers.SubscribeOn
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by ricardo casarez on 3/26/18.
 */
@Module(includes = [ DataModule::class ])
class ApplicationModule (private val mApplication: Application){

    @Provides @Singleton
    fun providesApplication() = mApplication

    @Provides @Singleton
    fun providesObserveOn() : ObserveOn {
        return object : ObserveOn {
            override fun scheduler(): Scheduler {
                return AndroidSchedulers.mainThread()
            }
        }
    }

    @Provides @Singleton
    fun providesSubscribeOn() : SubscribeOn {
        return object : SubscribeOn {
            override fun scheduler(): Scheduler {
                return Schedulers.newThread()
            }
        }
    }

    @Provides @Singleton
    fun providesContext() : Context{
        return mApplication
    }
}