package com.example.ejercicio_tema_8.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio_tema_8.R
import com.example.ejercicio_tema_8.domain.ComunidadAutonoma


class ComunidadAutonomaAdapter(private val comunidadLista:List<ComunidadAutonoma>, private val onClickListener: (ComunidadAutonoma) -> Unit): RecyclerView.Adapter<ComunidadAutonomaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComunidadAutonomaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ComunidadAutonomaViewHolder(
            layoutInflater.inflate(
                R.layout.list_item_comunidad_autonoma,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return comunidadLista.size
    }

    override fun onBindViewHolder(holder: ComunidadAutonomaViewHolder, position: Int) {
        val item = comunidadLista[position]
        holder.render(item, onClickListener)
    }
}