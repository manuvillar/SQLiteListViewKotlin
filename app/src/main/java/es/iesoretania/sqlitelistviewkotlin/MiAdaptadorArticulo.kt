package es.iesoretania.sqlitelistviewkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MiAdaptadorArticulo(
    private val ctx: Context,
    private val layoutTemplate: Int,
    private val articulolist: List<Articulo>
) :
    ArrayAdapter<Any?>(ctx, layoutTemplate, articulolist) {

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val v = LayoutInflater.from(ctx).inflate(layoutTemplate, parent, false)
        val elementoActual = articulolist[position]
        val textviewproducto =
            v.findViewById<View>(R.id.textViewproducto) as TextView
        val textviewprecio =
            v.findViewById<View>(R.id.textViewprecio) as TextView
        textviewproducto.text = elementoActual.producto
        textviewprecio.text = elementoActual.precio
        return v
    }
}