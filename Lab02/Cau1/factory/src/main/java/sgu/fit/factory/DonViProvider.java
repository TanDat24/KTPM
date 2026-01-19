package sgu.fit.factory;

/**
 * DonViProvider quyết định lớp DonVi nào sẽ được sử dụng để tạo NhanSu.
 * Thay đổi loại được tạo bằng cách thay đổi property hệ thống "donvi.loai"
 * Ví dụ: -Ddonvi.loai=hanhChinh để lấy DonViNhanVienHanhChinh
 */
public class DonViProvider {
    public static DonVi layDonVi() {
        String loai = System.getProperty("donvi.loai", "giaoVien");
        if ("hanhChinh".equalsIgnoreCase(loai)) {
            return new DonViNhanVienHanhChinh();
        }
        return new DonViGiaoVien();
    }
}

