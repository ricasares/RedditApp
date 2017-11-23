package apps.ricasares.com.domain.interactor

import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

/**
 * Created by rush on 11/21/17.
 */
open class BaseSingleObserver<T> : SingleObserver<T> {
    override fun onError(e: Throwable?) {}

    override fun onSuccess(value: T) {}

    override fun onSubscribe(d: Disposable?) {}

}