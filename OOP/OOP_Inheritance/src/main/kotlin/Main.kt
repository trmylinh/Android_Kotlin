fun main(args: Array<String>) {
    var nv1 = NVParttime("Linh", "NV1", "HaNoi")
    var nv2 = NVHanhChinh("Thai", "NV2", "VinhPhuc")

    println("Luong nhan vien 1 parttime ${nv1.TinhLuong()}")
    println("Luong nhan vien 2 hanh chinh ${nv2.TinhLuong()}")
}