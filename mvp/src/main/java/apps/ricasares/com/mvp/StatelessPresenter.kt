package apps.ricasares.com.mvp

import java.lang.ref.WeakReference

/**
 * Created by rush on 3/29/18.
 */
abstract class StatelessPresenter<V : View> {
    private var mView: WeakReference<V>? = null

    val view: V?
        get() = mView?.get()

    fun attachView(view: V) {
        mView = WeakReference(view)
    }

    open fun deatachView() {
        mView?.clear()
        mView = null
    }

    fun isViewAttached() : Boolean {
        return (mView != null && mView!!.get() != null)
    }
}