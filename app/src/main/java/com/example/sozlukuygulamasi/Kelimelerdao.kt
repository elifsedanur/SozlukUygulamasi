package com.example.sozlukuygulamasi

import android.annotation.SuppressLint
import android.content.ContentValues

class Kelimelerdao {

    @SuppressLint("Range")
    fun tumKelimeler(vt:VeriTabaniYardimcisi):ArrayList<Kelimeler>{
        val db = vt.writableDatabase
        val cursor = db.rawQuery("SELECT * from kelimeler order by ingilizce",null)
        val kelimeler = ArrayList<Kelimeler>()

        while (cursor.moveToNext()){
            val kelime = Kelimeler(cursor.getInt(cursor.getColumnIndex("kelime_id")),
                cursor.getString(cursor.getColumnIndex("ingilizce")),
                cursor.getString(cursor.getColumnIndex("turkce")))
            kelimeler.add(kelime)
        }
        return kelimeler
    }
    @SuppressLint("Range")
    fun kelimeAra(vt:VeriTabaniYardimcisi, aranan:String):ArrayList<Kelimeler>{
        val db = vt.writableDatabase
        val cursor = db.rawQuery("select* from kelimeler where ingilizce like '%$aranan%'",null)
        val kelimeler = ArrayList<Kelimeler>()

        while (cursor.moveToNext()){
            val kelime = Kelimeler(cursor.getInt(cursor.getColumnIndex("kelime_id")),
                cursor.getString(cursor.getColumnIndex("ingilizce")),
                cursor.getString(cursor.getColumnIndex("turkce")))
            kelimeler.add(kelime)
        }
        return kelimeler
    }
    fun kelimeEkle(vt:VeriTabaniYardimcisi,ingilizce:String,turkce:String){
        val db = vt.writableDatabase
        val values = ContentValues()

        val cursor = db.rawQuery("SELECT * from kelimeler where ingilizce = '$ingilizce'",null)

        if(!cursor.moveToNext()){
            values.put("ingilizce",ingilizce)
            values.put("turkce",turkce)
            db.insertOrThrow("kelimeler",null,values)
        }
        db.close()
    }
    fun kelimeSil(vt:VeriTabaniYardimcisi,kelime_id:Int){
        val db = vt.writableDatabase
        db.delete("kelimeler","kelime_id=?", arrayOf(kelime_id.toString()))
        db.close()
    }
}