package apps.ricasares.com.domain.interactor

import apps.ricasares.com.domain.schedulers.ObserveOn
import apps.ricasares.com.domain.schedulers.SubscribeOn
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

/**
 * Created by ricardo casarez on 11/21/17.
 */
abstract class ObservableUseCase<T, in Params> (
        private val subscribeOn: SubscribeOn,
        private val observeOn: ObserveOn) {

    private val disposables: CompositeDisposable = CompositeDisposable()

    protected abstract fun buildUseCaseObservable(params: Params? = null) : Observable<T>

    open fun execute(observer: DisposableObserver<T>, params: Params? = null) {
        addDisposable(buildUseCaseObservable(params)
                .subscribeOn(subscribeOn.scheduler())
                .observeOn(observeOn.scheduler())
                .subscribeWith(observer))
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}