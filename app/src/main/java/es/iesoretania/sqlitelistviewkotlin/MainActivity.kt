package es.iesoretania.sqlitelistviewkotlin

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun Registrar(view: View?) {
        val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
        val BaseDeDatos = admin.writableDatabase

        val codigo = et_codigo.text.toString()
        val producto = et_producto.text.toString()
        val precio = et_precio.text.toString()

        if (!codigo.isEmpty() && !producto.isEmpty() && !precio.isEmpty()) {
            val registro = ContentValues()

            registro.put("codigo", codigo)
            registro.put("producto", producto)
            registro.put("precio", precio)

            BaseDeDatos.insert("articulos", null, registro)

            BaseDeDatos.close()

            et_codigo.setText("")
            et_producto.setText("")
            et_precio.setText("")

            Toast.makeText(this, "Producto guardado correctamente", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Debes introducir todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    fun Buscar(view: View?) {
        val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
        val BaseDeDatos = admin.writableDatabase

        val codigo = et_codigo.text.toString()

        if (!codigo.isEmpty()) {
            val fila = BaseDeDatos.rawQuery(
                "select producto, precio from articulos where codigo =$codigo",
                null
            )
            if (fila.moveToFirst()) {
                et_producto.setText(fila.getString(0))
                et_precio.setText(fila.getString(1))
                BaseDeDatos.close()
            } else {
                Toast.makeText(this, "No existe el producto", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Debes introducir el código del producto", Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun Modificar(view: View?) {
        val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
        val BaseDeDatos = admin.writableDatabase

        val codigo = et_codigo.text.toString()
        val producto = et_producto.text.toString()
        val precio = et_precio.text.toString()

        if (!codigo.isEmpty() && !producto.isEmpty() && !precio.isEmpty()) {
            val registro = ContentValues()

            registro.put("codigo", codigo)
            registro.put("producto", producto)
            registro.put("precio", precio)

            val cantidad = BaseDeDatos.update("articulos", registro, "codigo=$codigo", null)
            BaseDeDatos.close()

            et_codigo.setText("")
            et_producto.setText("")
            et_precio.setText("")

            if (cantidad == 1) {
                Toast.makeText(this, "Registro modificado correctamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No existe el producto", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Debes introducir todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    fun Eliminar(view: View?) {
        val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
        val BaseDeDatos = admin.writableDatabase

        val codigo = et_codigo.text.toString()

        if (!codigo.isEmpty()) {
            val cantidad = BaseDeDatos.delete("articulos", "codigo=$codigo", null)
            BaseDeDatos.close()

            et_codigo.setText("")
            et_producto.setText("")
            et_precio.setText("")
            if (cantidad == 1) {
                Toast.makeText(this, "Registro eliminado correctamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No existe el producto", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Debes introducir todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    fun Listar(view: View?) {
        val intent = Intent(this, ListaActivity::class.java)
        startActivity(intent)
    }
}
