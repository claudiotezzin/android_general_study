package com.tezzin.dagger2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tezzin.dagger2.utils.ClassIntro
import com.tezzin.dagger2.data.DataManager
import javax.inject.Inject
import com.tezzin.dagger2.data.model.User
import com.tezzin.dagger2.di.component.ActivityComponent
import com.tezzin.dagger2.di.component.DaggerActivityComponent
import kotlinx.android.synthetic.main.activity_main.*


@ClassIntro(
        author = "Claudio Tezzin",
        date = "04/10/2017",
        currentRevision = 1
)
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var dataManager: DataManager

    private val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent
                .builder()
                .applicationComponent(Dagger2Application.get(this).getComponent())
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityComponent.inject(this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        createUser()
        getUser()
        dataManager.saveAccessToken("TESTING12346789qwertyuiop")

        val token = dataManager.getAccessToken()
        access_token.text = token
    }

    private fun createUser() {
        try {
            dataManager.createUser(User(name = "Claudio Tezzin", address = "1473, My Street Name, Brazil"))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun getUser() {
        try {
            val user = dataManager.getUser(1L)
            user_info.text = user.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
