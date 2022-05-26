package BUS.Interfaces;

import BUS.Interfaces.common.ICrudBUS;
import BUS.Interfaces.common.ISearchableBUS;
import DTO.LoaiSPDTO;

public interface ILoaiSPBUS extends ISearchableBUS<LoaiSPDTO>, ICrudBUS<LoaiSPDTO> {
    LoaiSPDTO findByTenLoai(String tenLoai);
}
