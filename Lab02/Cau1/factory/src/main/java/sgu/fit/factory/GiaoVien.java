package sgu.fit.factory;

public class GiaoVien implements NhanSu {
    private final String ten = "Giáo viên";

    @Override
    public String lamViec() {
        return "Dạy học và chuẩn bị bài giảng";
    }

    @Override
    public String getLoai() {
        return ten;
    }
}

