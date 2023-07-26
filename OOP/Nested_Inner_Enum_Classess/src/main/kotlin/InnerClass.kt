class InnerClass {
    var a = 1
    inner class ClassCon{
        fun Ham(){
            println(a) // truy xuat duoc
            println("Day la ham cua class con")
        }
    }

    // ham nam trong NestedClass
    fun Ham(){
        println(a) // truy xuat duoc
    }
}