class NestedClass {
    var a = 1
    class ClassCon{
        fun Ham(){
//            println(a) // truy xuat bi loi, khong truy xuat duoc
            println("Day la ham cua class con")
        }
    }

    // ham nam trong NestedClass
    fun Ham(){
        println(a) // truy xuat duoc
    }
}