package apps.ricasares.com.myreddit.di

import android.app.Application
import apps.ricasares.com.myreddit.MainActivity
import apps.ricasares.com.myreddit.fragments.ListingFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by rush on 3/26/18.
 */
@Singleton
@Component(modules = [ ApplicationModule::class ])
interface ApplicationComponent {
    fun inject(app: Application)
    fun inject(mainActivity: MainActivity)
    fun inject(listingFragment: ListingFragment)
}