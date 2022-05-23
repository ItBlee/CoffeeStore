package BUS.Interfaces;

import BUS.Interfaces.common.ICrudBUS;
import BUS.Interfaces.common.ISearchableBUS;
import DTO.PhanQuyenDTO;

public interface IPhanQuyenBUS extends ISearchableBUS<PhanQuyenDTO>, ICrudBUS<PhanQuyenDTO> {
    PhanQuyenDTO findByTenPhanQuyen(String tenPQ);
}
