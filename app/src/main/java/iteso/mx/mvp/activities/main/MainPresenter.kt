package iteso.mx.mvp.activities.main

import iteso.mx.mvp.beans.User
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    override fun fetchUser(objectId: String) {
        view.showProgress()
        doAsync {
            Thread.sleep(1000)
            val random = Random()
            val randomInt = random.nextInt(20)
            if (randomInt < 11) {
                uiThread {
                    view.showUser(User("Erick", 23))
                }
            } else {
                uiThread {
                    view.showNoUser()
                }
            }
        }
    }
}
