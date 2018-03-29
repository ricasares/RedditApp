package apps.ricasares.com.mvp

/**
 * Created by ricardo casarez on 3/21/18.
 */
abstract class Presenter<V : View, S : ViewState> : StatelessPresenter<V>() {
    protected var mState: S? = null

    fun attachView(view: V, state: S) {
        super.attachView(view)
        mState = state
    }

    override fun deatachView() {
        super.deatachView()
        mState = null
    }
}