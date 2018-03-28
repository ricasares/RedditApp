package apps.ricasares.com.myreddit

import android.app.Application
import apps.ricasares.com.myreddit.di.ApplicationComponent
import apps.ricasares.com.myreddit.di.ApplicationModule
import apps.ricasares.com.myreddit.di.DaggerApplicationComponent

/**
 * Created by ricardo casarez on 3/26/18.
 */
class RedditApplication : Application() {

    private val mApplicationComponent: ApplicationComponent by lazy {
        apps.ricasares.com.myreddit.di.DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        mApplicationComponent.inject(this)
    }

    fun getApplicationComponent() : ApplicationComponent = mApplicationComponent
}