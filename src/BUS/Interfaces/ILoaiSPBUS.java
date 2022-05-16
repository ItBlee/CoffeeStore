package BUS.Interfaces;

import BUS.Interfaces.common.ICrudBUS;
import BUS.Interfaces.common.ISearchableBUS;
import DTO.LoaiSPDTO;

import java.util.ArrayList;

public interface ILoaiSPBUS extends ISearchableBUS<LoaiSPDTO>, ICrudBUS<LoaiSPDTO> {
    ArrayList<LoaiSPDTO> findByTenLoai(String tenLoai);
}
