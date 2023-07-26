import java.util.Date

class SinhVien {
    private var namsinh : Date?

    public var NamSinh:Date?

        get(){return namsinh}
        set(value) {namsinh= value}

    constructor(namsinh:Date?){
        this.namsinh = namsinh
    }
}