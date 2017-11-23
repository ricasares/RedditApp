package apps.ricasares.com.domain.schedulers

import io.reactivex.Scheduler

/**
 * Created by rush on 11/20/17.
 */
interface ObserveOn {
    fun scheduler() : Scheduler
}