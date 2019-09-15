package iteso.mx.mvp.activities.main

import iteso.mx.mvp.beans.User

interface MainContract {
    interface View {
        fun showProgress()
        fun showUser(user: User)
        fun showNoUser()
    }

    interface Presenter {
        fun fetchUser(objectId: String)
    }
}
