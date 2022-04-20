package com.app.practicaltatvsoft.base

import androidx.lifecycle.ViewModel
import com.app.practicaltatvsoft.network.ApiService
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    @Inject
    lateinit var provideApiService: ApiService
}