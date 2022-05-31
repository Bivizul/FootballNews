package com.bivizul.footballnews.presentation.results

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.bivizul.footballnews.databinding.ItemResultBinding
import com.bivizul.footballnews.domain.models.Result
import com.bivizul.footballnews.utils.Constants

class ResultAdapter() : ListAdapter<Result, ResultViewHolder>(ResultDiffCallback) {

    // рисуем элемент по которому создается ViewHolder
    // сколько элементов будет,столько раз и нарисуется
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ResultViewHolder {
        val binding = ItemResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ResultViewHolder(binding)
    }

    // подключаем к элементу текст и т.д.
    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            with(item) {
                if (emblem.isNotEmpty()) {
                    imgEmblem.load(emblem)
                } else {
                    imgEmblem.load(Constants.DEFAULT_IMAGE)
                }
                tvNameTeam.text = name_team
                tvSymbol.text = symbol
                tvType.text = type
                tvDate.text = date
                tvScore.text = score
            }
        }
    }
}