package apps.ricasares.com.domain.interactor

import apps.ricasares.com.domain.schedulers.ObserveOn
import apps.ricasares.com.domain.schedulers.SubscribeOn
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver

/**
 * Created by ricardo casarez on 11/20/17.
 */
abstract class SingleUseCase<T, in Params> (
        private val subscribeOn: SubscribeOn,
        private val observeOn: ObserveOn) {

    private val disposable: CompositeDisposable = CompositeDisposable()

    protected abstract fun buildUseCaseObservable(params: Params? = null): Single<T>

    open fun execute(observer: DisposableSingleObserver<T>, params: Params? = null) {
        addDisposable(buildUseCaseObservable(params)
                .subscribeOn(subscribeOn.scheduler())
                .observeOn(observeOn.scheduler())
                .subscribeWith(observer))
    }

    fun dispose() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }

    protected fun addDisposable(disposable: Disposable) {
        this.disposable.add(disposable)
    }
}