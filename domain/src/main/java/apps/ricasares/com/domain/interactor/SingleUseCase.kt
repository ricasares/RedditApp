package apps.ricasares.com.domain.interactor

import apps.ricasares.com.domain.schedulers.ObserveOn
import apps.ricasares.com.domain.schedulers.SubscribeOn
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by rush on 11/20/17.
 */
abstract class SingleUseCase<T, Params> constructor(
        private val subscribeOn: SubscribeOn,
        private val observeOn: ObserveOn) {

    private val disposables: CompositeDisposable = CompositeDisposable()

    protected abstract fun buildUseCaseObservable(params: Params? = null): Single<T>

    open fun <S> execute(observer: S, params: Params? = null) where S : SingleObserver<T>, S: Disposable {
        val observable: Single<T> = buildUseCaseObservable(params)
                .subscribeOn(subscribeOn.scheduler())
                .observeOn(observeOn.scheduler())
        addDisposable(observable.subscribeWith(observer))
    }

    private fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}