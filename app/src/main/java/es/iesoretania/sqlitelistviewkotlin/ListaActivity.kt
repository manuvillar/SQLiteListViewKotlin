package es.iesoretania.sqlitelistviewkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_lista.*
import java.util.*

class ListaActivity : AppCompatActivity() {
    var milistaarticulos: List<Articulo>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
        val BaseDeDatos = admin.writableDatabase

        val cursor = BaseDeDatos.rawQuery("select * from articulos", null)
        var codigo: String?
        var producto: String?
        var precio: String?
        milistaarticulos = ArrayList()
        if (cursor.moveToFirst()) {
            do {
                codigo = cursor.getString(0)
                producto = cursor.getString(1)
                precio = cursor.getString(2)
                (milistaarticulos as ArrayList<Articulo>).add(Articulo(codigo, producto, precio))
            } while (cursor.moveToNext())
        }

        val miadaptador =
            MiAdaptadorArticulo(this, R.layout.articulos_item,
                milistaarticulos as ArrayList<Articulo>
            )

        lv_lista.setAdapter(miadaptador)
    }
}
