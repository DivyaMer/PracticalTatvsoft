package com.app.practicaltatvsoft.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.practicaltatvsoft.base.BaseViewHolder
import com.app.practicaltatvsoft.databinding.ItemImageBinding
import com.app.practicaltatvsoft.ktx.loadImage

class ImageAdapter(
    val images: List<String>,
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ImageViewHolder(
        ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) = holder.onBind(position)

    override fun getItemCount() = images.size

    inner class ImageViewHolder(binding: ItemImageBinding) : BaseViewHolder(binding.root) {

        private val mBinding: ItemImageBinding = binding

        override fun onBind(position: Int) {
            mBinding.aivImage.loadImage(images[position])
        }
    }
}