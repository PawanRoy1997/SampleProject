package com.example.sampleproject.navigators


enum class Screens {
    CLICK_COUNTER,
    LOGS
}

interface AppNavigator {
    fun navigateTo(screen: Screens)
}