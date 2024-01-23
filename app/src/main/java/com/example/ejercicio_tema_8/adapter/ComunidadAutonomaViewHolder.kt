package com.example.ejercicio_tema_8.adapter

import android.view.ContextMenu
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio_tema_8.domain.ComunidadAutonoma
import com.example.ejercicio_tema_8.databinding.ListItemComunidadAutonomaBinding
import com.squareup.picasso.Picasso

class ComunidadAutonomaViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {
    val binding = ListItemComunidadAutonomaBinding.bind(view)
    private lateinit var comunidad: ComunidadAutonoma
    fun render(item: ComunidadAutonoma, onClickListener: (ComunidadAutonoma) -> Unit) {
        binding.tvNombreComunidad.text = item.nombre
        Picasso.get().load(item.imagen).fit().into(binding.ivBandera)
        itemView.setOnClickListener {
            onClickListener(item)
        }
        comunidad = item
        itemView.setOnCreateContextMenuListener(this)

    }

    override fun onCreateContextMenu( //Al dejar pulsado sobre una Comunidad Autonoma
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu!!.setHeaderTitle(comunidad.nombre)
        menu.add(this.adapterPosition, 0, 0, "Eliminar")
        menu.add(this.adapterPosition, 1, 1, "Editar")
        menu.add(this.adapterPosition, 2, 2, "Hacer foto")
        menu.add(this.adapterPosition, 3, 3, "Ver foto")
    }
}
