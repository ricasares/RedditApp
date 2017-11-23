package apps.ricasares.com.domain.schedulers

import io.reactivex.Scheduler

/**
 * Created by rush on 11/21/17.
 */
interface SubscribeOn {
    fun scheduler() : Scheduler
}