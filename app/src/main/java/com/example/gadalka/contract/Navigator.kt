package com.example.gadalka.contract

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.example.gadalka.Options

typealias ResultListener<T> = (T) -> Unit

fun Fragment.navigator(): Navigator = requireActivity() as Navigator

interface Navigator {

    fun showBaseScreen(options: Options)

    fun showBoredScreen()

    fun showJokeScreen()

    fun goBack()

    fun goToMenu()

    fun <T: Parcelable> publishResult(result: T)

    fun <T: Parcelable> listenResult(
        clazz: Class<T>,
        owner: LifecycleOwner,
        listener: ResultListener<T>
    )
}