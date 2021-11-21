package com.alfikri.rizky.gamedatabasemviapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alfikri.rizky.gamedatabasemviapp.R
import com.alfikri.rizky.gamedatabasemviapp.data.model.GameInfoModel
import com.alfikri.rizky.gamedatabasemviapp.databinding.ItemGameBinding
import com.alfikri.rizky.gamedatabasemviapp.ui.GamesAdapter.GameViewHolder
import com.bumptech.glide.Glide
import kotlin.math.floor

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GamesAdapter, v 0.1 11/9/2021 8:41 PM by Rizky Alfikri Rachmat
 */
class GamesAdapter : ListAdapter<GameInfoModel, GameViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    class GameViewHolder(private val binding: ItemGameBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(gameInfoModel: GameInfoModel) {
            binding.run {
                tvGameName.text = gameInfoModel.name
                tvGameRelease.text = gameInfoModel.released
                val rate = gameInfoModel.rating?.let { floor(it) }
                rbGameRate.rating = rate?.toFloat() ?: 0F
                tvGameRate.text = StringBuilder("$rate/5")
                Glide.with(binding.root)
                    .load(gameInfoModel.backgroundImage)
                    .placeholder(R.color.white)
                    .into(ivGamePoster)
            }
        }
    }

    companion object {

        val DIFF_UTIL = object : DiffUtil.ItemCallback<GameInfoModel>() {
            override fun areItemsTheSame(oldItem: GameInfoModel, newItem: GameInfoModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: GameInfoModel,
                newItem: GameInfoModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}