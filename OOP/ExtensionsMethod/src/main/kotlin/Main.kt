import java.util.Calendar

// viet them ham Cong() cho class Int
fun Int.Cong(a: Int) : Int{
    return this + a
}

fun Int.CheckSNT(): Boolean{
    var count = 0
    for(i in 1..this){
        if(this %i == 0){
            count++
        }
    }
    return count==2
}

// chen ham tinh tuoi vao class sinh vien
fun SinhVien.TinhTuoi(): Int{
    var now = Calendar.getInstance()
    var namHientai = now.get(Calendar.YEAR)

    //set nam sinh vao bien time
    // gán lại thời gian  hiện tại bằng thời gian cụ thể ở cái năm this.NamSinh
    now.time = this.NamSinh
    println("now.time "+now.time)

    var namSinh = now.get(Calendar.YEAR)
    return namHientai - namSinh
}
fun main(args: Array<String>) {
    // this -> so 7
    // a -> so 5
    var x = 7.Cong(5)
    println(x)

    var result = 3.CheckSNT()
    println("So nguyen to: ${result}")


    var ns = Calendar.getInstance()
    //set nam sinh
    ns.set(Calendar.YEAR,2002)
    var sv1 = SinhVien(ns.time)
    var age = sv1.TinhTuoi()
    println("Tuoi: ${age}")
}