package com.darien.week6vp.Model

data class Student (
    val nama: String,
    val prodi: String,
    val angkatan: Int,
    val nim: String,
){
    val initial: String
        get() = nama.split(" ")
            .take(2).joinToString(""){it.first().uppercase()}
}