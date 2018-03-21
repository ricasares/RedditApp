package apps.ricasares.com.mvp

import java.lang.ref.WeakReference

/**
 * Created by rush on 3/21/18.
 */
abstract class Presenter<in V : View, in S : ViewState> {
    private var mView: WeakReference<View>? = null
    private var mState: S? = null

    fun attachView(view: V, state: S) {
        mView = WeakReference(view)
        mState = state
    }

    fun attachView(view: V) {
        mView = WeakReference(view)
    }

    fun deatachView() {
        mView = null
        mState = null
    }

    fun isViewAttached() : Boolean {
        return (mView != null && mView?.get() != null)
    }
}