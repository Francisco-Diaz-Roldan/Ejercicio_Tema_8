package com.example.ejercicio_tema_8.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.ejercicio_tema_8.domain.ComunidadAutonoma

class ComunidadAutonomaDiffUtil(
    private val oldList: List<ComunidadAutonoma>,
    private val newList: List<ComunidadAutonoma>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}