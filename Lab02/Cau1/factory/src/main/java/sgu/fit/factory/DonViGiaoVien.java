package sgu.fit.factory;

public class DonViGiaoVien extends DonVi {
    @Override
    public NhanSu taoNhanSu() {
        // factory creates a GiaoVien instance
        return new GiaoVien();
    }
}

