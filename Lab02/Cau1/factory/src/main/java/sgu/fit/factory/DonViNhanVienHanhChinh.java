package sgu.fit.factory;

public class DonViNhanVienHanhChinh extends DonVi {
    @Override
    public NhanSu taoNhanSu() {
        return new NhanVienHanhChinh();
    }
}

