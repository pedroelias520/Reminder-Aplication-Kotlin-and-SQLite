package DBhelper

import Model.LembreteClass
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

 class DBhelper(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VER){

     companion object {
         private val DATABASE_VER = 1
         private val DATABASE_NAME = "LEMB.db"

         //Table
         private val TABLE_NAME= "Lembretes"
         private val COL_ID = "Id"
         private val COL_NOME="Nome"
         private val COL_HORA="Hora"
         private val COL_DESCRICAO="Descricao"
         private val COL_IMPORTANCIA="Importancia"
     }


     override fun onCreate(db: SQLiteDatabase?) {
         val CREATE_TABLE_QUERY = ("CREATE TABLE"+TABLE_NAME+"("+COL_ID+"INT PRIMARY KEY AUTO_INCREMENT,"+COL_NOME+" TEXT ,"+COL_HORA+" TEXT ,"+COL_DESCRICAO+" TEXT ,"+COL_IMPORTANCIA+" TEXT)")
         db!!.execSQL(CREATE_TABLE_QUERY)
     }

     override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
         db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
         onCreate(db!!)
     }

     val allReminds:MutableList<LembreteClass>
        get() {
            val lstReminds = ArrayList<LembreteClass>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val db:SQLiteDatabase = this.writableDatabase
            val cursor = db.rawQuery(selectQuery,null)
            if(cursor.moveToFirst())
            {
                do{
                    val id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                    var nome = cursor.getString(cursor.getColumnIndex(COL_NOME))
                    var hora = cursor.getString(cursor.getColumnIndex(COL_HORA))
                    var descricao = cursor.getString(cursor.getColumnIndex(COL_DESCRICAO))
                    var importancia = cursor.getString(cursor.getColumnIndex(COL_IMPORTANCIA))
                    var lembrete = LembreteClass(id,nome,hora,descricao,importancia)
                    allReminds.add(lembrete)
                }while (cursor.moveToNext())
            }
            db.close()
            return allReminds
        }

     fun addReminder(lembreteClass: LembreteClass)
     {
         val db = this.writableDatabase
         val values = ContentValues()
         values.put(COL_ID,lembreteClass.id)
         values.put(COL_NOME,lembreteClass.nome)
         values.put(COL_HORA,lembreteClass.hora)
         values.put(COL_DESCRICAO,lembreteClass.descricao)
         values.put(COL_IMPORTANCIA,lembreteClass.importancia)

         db.insert(TABLE_NAME,null,values)
         db.close()
     }
     fun updateReminder(lembreteClass: LembreteClass)
     {
         val db = this.writableDatabase
         val values = ContentValues()
         values.put(COL_ID,lembreteClass.id)
         values.put(COL_NOME,lembreteClass.nome)
         values.put(COL_HORA,lembreteClass.hora)
         values.put(COL_DESCRICAO,lembreteClass.descricao)
         values.put(COL_IMPORTANCIA,lembreteClass.importancia)

         db.update(TABLE_NAME,values,"$COL_ID=?", arrayOf(lembreteClass.id.toString()))
         db.close()
     }
     fun deleteReminder(lembreteClass: LembreteClass)
     {
         val db = this.writableDatabase
         db.delete(TABLE_NAME,"$COL_ID=?")
     }

 }

