package apps.ricasares.com.mvp

import java.lang.ref.WeakReference

/**
 * Created by ricardo on 3/21/18.
 */
open class Presenter<V : View, S : ViewState> {
    private var mView: WeakReference<V>? = null
    protected var mState: S? = null

    val view: V?
        get() = mView?.get()

    fun attachView(view: V, state: S) {
        mView = WeakReference(view)
        mState = state
    }

    fun attachView(view: V) {
        mView = WeakReference(view)
    }

    fun deatachView() {
        mView?.clear()
        mView = null
        mState = null
    }

    fun isViewAttached() : Boolean {
        return (mView != null && mView!!.get() != null)
    }
}