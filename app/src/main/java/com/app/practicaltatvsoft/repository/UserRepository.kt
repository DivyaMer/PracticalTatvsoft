package com.app.practicaltatvsoft.repository

import com.app.practicaltatvsoft.model.UserResponse
import com.app.practicaltatvsoft.network.ApiService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val service: ApiService) {

    suspend fun getUserList(offset: Int = 10, limit: Int = 10): Response<UserResponse> {
        return service.getUserList(offset, limit)
    }

}