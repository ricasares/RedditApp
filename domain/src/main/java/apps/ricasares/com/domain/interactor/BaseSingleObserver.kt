package apps.ricasares.com.domain.interactor

import io.reactivex.observers.DisposableSingleObserver

/**
 * Created by ricardo casarez on 11/21/17.
 */
open class BaseSingleObserver<T> : DisposableSingleObserver<T>() {
    override fun onError(e: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess(value: T) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}