package com.example.sozlukuygulamasi

import android.annotation.SuppressLint

class Kelimelerdao {

    @SuppressLint("Range")
    fun tumKelimeler(vt:VeriTabaniYardimcisi):ArrayList<Kelimeler>{
        val db = vt.writableDatabase
        val cursor = db.rawQuery("select * from kelimeler",null)
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
}