package com.example.oop_kotlin

// khai bao class binh thuong
class Student(nameS: String, addressS: String, yearS: Int){
    private var name : String = nameS
    private var address : String = addressS
    private var year : Int = yearS

//    constructor(nameS: String, addressS: String, yearS: Int) {
//        this.name = nameS
//        this.address = addressS
//        this.year = yearS
//    }

    fun setName(nameS: String){
        this.name = nameS
    }

    fun getName() : String {
        return this.name
    }

    fun setAddress(addressS: String){
        this.address = addressS
    }

    fun getAddress() : String {
        return this.address
    }

    fun setYear(yearS: Int){
        this.year = yearS
    }

    fun getYear() : Int {
        return this.year
    }

}

// Constuctor
//class SinhVien(var hoTen: String, var diaChi: String, var namSinh: Int)
data class SinhVien(var hoTen: String, var diaChi: String, var namSinh: Int)