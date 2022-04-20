package com.app.practicaltatvsoft.ui.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.practicaltatvsoft.R
import com.app.practicaltatvsoft.base.BaseViewHolder
import com.app.practicaltatvsoft.databinding.ItemUserBinding
import com.app.practicaltatvsoft.ktx.loadImage
import com.app.practicaltatvsoft.model.UserModel
import com.app.practicaltatvsoft.userlist.ImageAdapter

class UserAdapter(
    val userList: MutableList<UserModel.User>,
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) = holder.onBind(position)

    override fun getItemCount() = userList.size

    fun addUsers(userList: MutableList<UserModel.User>){
        this.userList.addAll(userList)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(binding: ItemUserBinding) : BaseViewHolder(binding.root) {

        private val mBinding: ItemUserBinding = binding

        override fun onBind(position: Int) {
            val user = userList[position]
            mBinding.apply {
                sivProfile.loadImage(user.image ?: "", R.drawable.ic_person)
                val images = user.items ?: listOf()
                if (!images.isNullOrEmpty()) {
                    aivCoverPhoto.loadImage(images[0])
                    val imageMutableList = images.toMutableList()
                    if (images.size % 2 != 0) imageMutableList.removeAt(0)
                    val adapter = ImageAdapter(imageMutableList)
                    rvImages.adapter = adapter
                }
                userModel = userList[position]
            }
        }
    }
}