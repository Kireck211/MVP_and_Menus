package iteso.mx.mvp.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.ViewAnimator
import iteso.mx.mvp.R
import iteso.mx.mvp.beans.User
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity(), MainContract.View, PopupMenu.OnMenuItemClickListener {
    private lateinit var mName: TextView
    private lateinit var mSwitcher: ViewAnimator
    private lateinit var mPresenter: MainContract.Presenter
    private lateinit var mButton: Button

    companion object {
        const val SHOW_PROGRESS = 0
        const val SHOW_NO_USER = 1
        const val SHOW_USER = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mName = find(R.id.activity_main_tv_user)
        mSwitcher = find(R.id.activity_main_va_switcher)
        mButton = find(R.id.activity_main_btn_options)

        mPresenter = MainPresenter(this)
        mPresenter.fetchUser("asdfoij234")

        mButton.setOnClickListener {
            showPopupMenu()
        }
    }

    override fun showProgress() {
        mSwitcher.displayedChild = SHOW_PROGRESS
    }

    override fun showUser(user: User) {
        mName.text = user.name
        mSwitcher.displayedChild = SHOW_USER
    }

    override fun showNoUser() {
        mSwitcher.displayedChild = SHOW_NO_USER
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        decideActionWithMenuItemClicked(item.itemId)
        return true
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (item != null)
            decideActionWithMenuItemClicked(item.itemId)
        return true
    }

    private fun decideActionWithMenuItemClicked(itemId: Int) {
        when (itemId) {
            R.id.action_main_fetch_user -> {
                mPresenter.fetchUser("asdf234jl")
            }
            R.id.action_main_show_progress -> {
                showProgress()
            }
            R.id.action_main_show_no_user -> {
                showNoUser()
            }
            R.id.action_main_show_user -> {
                showUser(User("Erick", 23))
            }
        }
    }

    private fun showPopupMenu() {
        val popupMenu = PopupMenu(this, mButton)
        popupMenu.setOnMenuItemClickListener(this)
        popupMenu.inflate(R.menu.main_menu)
        popupMenu.show()
    }
}
