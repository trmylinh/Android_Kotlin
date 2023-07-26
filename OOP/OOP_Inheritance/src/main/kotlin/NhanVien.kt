open abstract class NhanVien {
    // instance var
    protected var ten: String = ""
    protected var cccd : String = ""
    protected var que : String = ""

    val luongCoBan : Double = 3000.0

    // ham truu tuong
    public abstract fun TinhLuong() : Double

    constructor(ten: String, cccd: String, que: String) {
        this.ten = ten
        this.cccd = cccd
        this.que = que
    }

    constructor(ten: String, cccd: String) {
        this.ten = ten
        this.cccd = cccd
    }





}