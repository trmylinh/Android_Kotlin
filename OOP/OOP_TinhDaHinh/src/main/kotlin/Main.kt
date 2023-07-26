fun main(args: Array<String>) {
    var nv1 : NhanSu = PhoPhong()
    println("Luong nhan vien nv1 - pho phong ${nv1.TinhLuong(20)}")

    // tinh da hinh
    // luc lam truong phong - luc lam pho phong
    nv1 = TruongPhong()
    println("Luong nhan vien nv1 - truong phong ${nv1.TinhLuong(20)}")



    var dt1 : TinhToanInterface = PhepCong()
    dt1.Tinh(4,7) // phep tinh cong 4+7

    dt1 = PhepTru()
    dt1.Tinh(7,4) // phep tinh tru 7-4



    // data classess
    data class TestData(var ten: String, var tuoi: Int)
    var data1 = TestData("Linh", 21)
    //cac phuong thuc co san
    var data2 = data1.copy()
    //copy nhung muon sua du lieu
    var data3 = data1.copy("My Linh")
    println("Data 2: "+ data2)
    println("Data 3: "+ data3)

    // anh xa den doi so cua data class - componentN
    println("ComponentN: " +data2.component1())
    println("ComponentN: " +data2.component2())

}