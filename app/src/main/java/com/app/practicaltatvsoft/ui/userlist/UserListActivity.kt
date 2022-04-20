package com.app.practicaltatvsoft.ui.userlist

import androidx.recyclerview.widget.LinearLayoutManager
import com.app.practicaltatvsoft.BR
import com.app.practicaltatvsoft.R
import com.app.practicaltatvsoft.base.BaseActivity
import com.app.practicaltatvsoft.databinding.ActivityUserListBinding
import com.app.practicaltatvsoft.ktx.Logger
import com.app.practicaltatvsoft.ktx.gone
import com.app.practicaltatvsoft.ktx.isNetworkConnected
import com.app.practicaltatvsoft.ktx.visible
import com.app.practicaltatvsoft.ui.widget.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListActivity : BaseActivity<ActivityUserListBinding, UserListViewModel>() {

    override val layoutId = R.layout.activity_user_list

    override val bindingVariable = BR.listViewModel

    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var offset = 0

    lateinit var adapter : UserAdapter

    override fun setUpObserver() {
        mViewModel.userResponseObservable.observe(this) {
            isLoading = false
            isLastPage = it.data.users.isNullOrEmpty()
            if (it.status) {
                binding.apply {
                    progressCircular.gone()
                    if (::adapter.isInitialized) {
                        adapter.addUsers(it.data.users?.toMutableList() ?: mutableListOf())
                    }
                }
            }

            Logger.d("UserResponse $it")
        }
    }

    override fun init() {
        binding.apply {
            adapter = UserAdapter(mutableListOf())
            rvUsers.adapter = adapter

            val layoutManager = rvUsers.layoutManager as LinearLayoutManager
            rvUsers.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
                override fun isLastPage(): Boolean {
                    return isLastPage
                }

                override fun isLoading(): Boolean {
                    return isLoading
                }

                override fun loadMoreItems() {
                    isLoading = true
                    offset++

                    callUserListApi(offset*10)
                }
            })
        }
        callUserListApi(offset)
    }

    private fun callUserListApi(offset : Int) {
        if (isNetworkConnected()) {
            binding.progressCircular.visible()
            mViewModel.getMainList(offset = offset)
        } else {
            showSnackbar("Internet is not connected...")
        }
    }
}