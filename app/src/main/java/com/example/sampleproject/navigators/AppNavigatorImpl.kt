package com.example.sampleproject.navigators

import androidx.fragment.app.FragmentActivity
import com.example.sampleproject.R
import com.example.sampleproject.fragments.ClickCounterFragment
import com.example.sampleproject.fragments.LogFragment
import javax.inject.Inject


class AppNavigatorImpl @Inject constructor(private val activity: FragmentActivity) : AppNavigator {
    override fun navigateTo(screen: Screens) {
        val fragment = when (screen) {
            Screens.CLICK_COUNTER -> ClickCounterFragment()
            Screens.LOGS -> LogFragment()
        }

        activity.supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
            .addToBackStack(fragment::class.java.canonicalName).commit()
    }
}