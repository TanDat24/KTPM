package sgu.fit.factory;

public class TestMain {
    // Small non-JavaFX main to test factory behavior without using `new` in this class
    public static void main(String[] args) {
        // Optionally choose type via system property: -Ddonvi.loai=hanhChinh
        DonVi donVi = DonViProvider.layDonVi();
        NhanSu ns = donVi.taoNhanSu();
        System.out.println(ns.getLoai() + " - " + ns.lamViec());
    }
}

