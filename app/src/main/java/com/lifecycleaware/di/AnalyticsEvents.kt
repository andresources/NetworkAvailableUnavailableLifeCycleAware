package com.lifecycleaware.di

interface AnalyticsEvents {
    fun trackAppEvent(event: String)
}