package sgu.fit.factory;

public class NhanVienHanhChinh implements NhanSu {
    private final String ten = "Nhân viên hành chính";

    @Override
    public String lamViec() {
        return "Quản lý giấy tờ, hỗ trợ hành chính";
    }

    @Override
    public String getLoai() {
        return ten;
    }
}

