fun main(args: Array<String>) {
    // goi den Ham() cua
    // doi tuong ClassCon
    // nam trong trong lop NestedClass
    NestedClass.ClassCon().Ham()

    // goi den Ham() nam trong class NestedClass
    NestedClass().Ham()

    println("--------------------------------")
    // khi dung inner class thi InnerClass() phai la doi tuong
    InnerClass().ClassCon().Ham()

    // goi Ham() nam trong class InnerClass
    InnerClass().Ham()


    println("--------------------------------")
    // so thu tu bao nhieu trong class
    println("Vi tri ${EnumClass.Java.ordinal}") //vi tri 0
    println("Vi tri ${EnumClass.TypeScript.ordinal}") // vi tri 3
    println("Name : ${EnumClass.TypeScript.name}")
    println("toString: ${EnumClass.TypeScript}")

    // values() trả về 1 mảng danh sách các phần tử trong class
    var ds = EnumClass.values()
    ds.forEach { println(it) }

    println("--------------------------------")
    // khoi tao doi tuong thuoc class EnumClass
    var a : EnumClass = EnumClass.TypeScript
    when (a) {
        EnumClass.Java -> println("Ngon ngu Java")
        EnumClass.Kotlin -> println("Ngon ngu Kotlin")
        EnumClass.JavaScript -> println("Ngon ngu JavaScript")
        EnumClass.TypeScript -> println("Ngon ngu TypeScript")
    }

    println(EnumClass.Java.age)





}