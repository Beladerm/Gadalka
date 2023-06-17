package com.example.gadalka

import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.example.gadalka.contract.CustomAction
import com.example.gadalka.contract.HasCustomAction
import com.example.gadalka.contract.HasCustomTitle
import com.example.gadalka.contract.Navigator
import com.example.gadalka.contract.ResultListener
import com.example.gadalka.databinding.ActivityMainBinding
import com.example.gadalka.fragments.BaseFragment
import com.example.gadalka.fragments.BoredFragment
import com.example.gadalka.fragments.JokeFragment
import com.example.gadalka.fragments.MenuFragment


class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding


    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.fragment_container)!!

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentCreated(fm, f, savedInstanceState)
            updateUI()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivityMainBinding
            .inflate(layoutInflater)
            .also { setContentView(it.root) }

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, MenuFragment())
                .commit()
        }

        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)


    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        onBackPressed()
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun updateUI() {
        val fragment = currentFragment

        if (fragment is HasCustomTitle) {
            binding.toolbar.title = getString(fragment.getTitle())
        } else {
            binding.toolbar.title = getString(R.string.fragment_navigation_example)
        }

        if (supportFragmentManager.backStackEntryCount > 0) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
        }

        if (fragment is HasCustomAction) {
            createCustomToolbarAction(fragment.getCustomAction())
        } else {
            binding.toolbar.menu.clear()
        }

    }

    private fun createCustomToolbarAction(action: CustomAction) {
        binding.toolbar.menu.clear()

        val iconDrawable = DrawableCompat.wrap(ContextCompat.getDrawable(this, action.iconRes)!!)
        iconDrawable.setTint(Color.WHITE)

        val menuItem = binding.toolbar.menu.add(action.textRes)
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menuItem.icon = iconDrawable
        menuItem.setOnMenuItemClickListener {
            action.onCustomAction.run()
            return@setOnMenuItemClickListener true
        }
    }

    override fun showBaseScreen(options: Options) {
    launchFragment(BaseFragment())
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out)
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }

    override fun showBoredScreen(options: Options) {
        launchFragment(BoredFragment.newInstance(options))
    }

    override fun showJokeScreen(options: Options) {
        launchFragment(JokeFragment.newInstance(options))
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun goToMenu() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun <T : Parcelable> publishResult(result: T) {
        supportFragmentManager.setFragmentResult(result.javaClass.name, bundleOf(KEY_RESULT to result))
    }

    override fun <T : Parcelable> listenResult(
        clazz: Class<T>,
        owner: LifecycleOwner,
        listener: ResultListener<T>
    ) {
        supportFragmentManager.setFragmentResultListener(clazz.name, owner) { _, bundle ->
            listener.invoke(bundle.getParcelable(KEY_RESULT)!!)
        }
    }

    companion object {
        const val KEY_RESULT = "KEY_RESULT"
    }

}
