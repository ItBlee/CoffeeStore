package GUI;

import DTO.TaiKhoanDTO;
import Mapper.TaiKhoanMapper;
import Utils.FileHandler;

import java.io.IOException;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<TaiKhoanDTO> list;
        /*for (int i = 0; i < 10; i++) {
            TaiKhoanDTO dto = new TaiKhoanDTO();
            dto.setMaTK(i);
            dto.setTenDangNhap("test" + i);
            dto.setMatKhau("test" + i);
            dto.setNguoiTao(1);
            dto.setChucVu("guest");
            list.add(dto);
        }*/
        try {
            list = FileHandler.importExcel("export/test.xls", new TaiKhoanMapper());
            for (TaiKhoanDTO a:list) {
                System.out.println(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
