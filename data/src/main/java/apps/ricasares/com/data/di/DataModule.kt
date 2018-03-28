package apps.ricasares.com.data.di

import apps.ricasares.com.data.cache.ListingCache
import apps.ricasares.com.data.cache.ListingDbCache
import apps.ricasares.com.data.cache.ListingMemoryCache
import apps.ricasares.com.data.entity.mapper.ListingMapper
import apps.ricasares.com.data.repository.ListingDataStoreFactory
import apps.ricasares.com.data.repository.ListingDiskDataStore
import apps.ricasares.com.data.repository.ListingRepositoryImp
import apps.ricasares.com.domain.repository.ListingRepository
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
@Module(includes = [ NetworkModule::class ])
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

    @Provides @Singleton @Named("disk_cache")
    fun providesDiskCache() : ListingCache = ListingDbCache()

    @Provides @Singleton @Named("memory_cache")
    fun providesMemoryCache() : ListingCache = ListingMemoryCache()

    @Provides @Singleton
    fun providesListingDataStoreFactory(
            @Named("disk_cache") diskCache: ListingCache,
            @Named("memory_cache") memoryCache: ListingCache, redditApi: RedditApi) : ListingDataStoreFactory {
        return ListingDataStoreFactory(diskCache, memoryCache, redditApi)
    }

    @Provides @Singleton
    fun provideListingRepository(listingDataStoreFactory: ListingDataStoreFactory, listingMapper: ListingMapper) : ListingRepository {
        return ListingRepositoryImp(listingDataStoreFactory, listingMapper)
    }

    @Provides @Singleton
    fun provideListingMapper() : ListingMapper = ListingMapper()
}