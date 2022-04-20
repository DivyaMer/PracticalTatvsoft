package com.app.practicaltatvsoft.ui.userlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.practicaltatvsoft.base.BaseViewModel
import com.app.practicaltatvsoft.model.UserResponse
import com.app.practicaltatvsoft.network.APIConstant
import com.app.practicaltatvsoft.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class UserListViewModel @Inject constructor(private val repository: UserRepository) : BaseViewModel() {

    val userResponseObservable: MutableLiveData<UserResponse> = MutableLiveData()

    fun getMainList(offset: Int = 1, limit: Int = 10) {
        var response: Response<UserResponse>? = null
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                response = repository.getUserList(offset, limit)
            }
            withContext(Dispatchers.Main) {
                response?.run {
                    if (isSuccessful && code() == APIConstant.STATUS_SUCCESS)
                        userResponseObservable.postValue(this.body())
                }
            }
        }
    }
}