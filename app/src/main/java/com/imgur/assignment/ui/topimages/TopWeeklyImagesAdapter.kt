package com.imgur.assignment.ui.topimages

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.imgur.assignment.R
import com.imgur.assignment.data.model.Data
import com.imgur.assignment.databinding.TopWeeklyImagesListItemBinding
import java.text.SimpleDateFormat
import java.util.*

class TopWeeklyImagesAdapter(private val imagesList: ArrayList<Data>) :
    RecyclerView.Adapter<TopWeeklyImagesAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: TopWeeklyImagesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(imageItem: Data) {

            // Set Marquee effect for grid view
//            binding.textViewDateTime.isSelected = true
//            binding.textViewNoImages.isSelected = true

            binding.textViewTitle.text = "Title: ${imageItem.title}"
            binding.textViewDateTime.text = "Date: ${convertTimestampToDateTime(imageItem.datetime?.toLong() ?: 0)}"
            binding.textViewNoImages.text = "Img Count: ${imageItem.imagesCount.toString()}"


            // Check image url is null or not
            val imageUrl = imageItem.images.getOrNull(0)?.link ?: R.mipmap.no_data

            Glide.with(binding.imageViewBanner.context)
                .load(imageUrl)
                .into(binding.imageViewBanner)

            itemView.setOnClickListener {

            }
        }


        // Convert Time Stamp to Date
        private fun convertTimestampToDateTime(timestamp: Long): String {
            val dateFormat = SimpleDateFormat("dd/MM/yy hh:mm a", Locale.getDefault())
            val date = Date(timestamp * 1000) // Convert seconds to milliseconds
            return dateFormat.format(date)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(
        TopWeeklyImagesListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int = imagesList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(imagesList[position])

    fun addData(list: List<Data>) {
        imagesList.clear()
        imagesList.addAll(list)
    }

}