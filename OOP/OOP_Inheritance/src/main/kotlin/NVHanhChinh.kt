class NVHanhChinh : NhanVien {

    constructor(ten: String, cccd: String, que: String) : super(ten, cccd, que)
    override fun TinhLuong(): Double {
       return luongCoBan
    }
}