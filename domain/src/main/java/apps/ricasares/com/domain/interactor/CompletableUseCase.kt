package apps.ricasares.com.domain.interactor

import apps.ricasares.com.domain.schedulers.ObserveOn
import apps.ricasares.com.domain.schedulers.SubscribeOn
import io.reactivex.Completable
import io.reactivex.disposables.Disposables

/**
 * Created by ricardo casarez on 3/22/18.
 */
abstract class CompletableUseCase <in Params> (
        private val subscribeOn: SubscribeOn,
        private val observeOn: ObserveOn) {

    private val disposable = Disposables.empty()

    protected abstract fun buildUseCaseObservable(params: Params): Completable

    fun execute(params: Params) : Completable {
        return buildUseCaseObservable(params)
                .subscribeOn(subscribeOn.scheduler())
                .observeOn(observeOn.scheduler())
    }

    fun dispose() {
        if (!disposable.isDisposed) disposable.dispose()
    }
}