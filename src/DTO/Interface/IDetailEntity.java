package DTO.Interface;

public interface IDetailEntity extends  IEntity{
    Integer getForeignID();
    void setForeignID(Integer foreignID);
    Integer getSoLuong();
    void setSoLuong(Integer soLuong);
    Integer getDonGia();
    void setDonGia(Integer donGia);
    Integer getThanhTien();
    void setThanhTien(Integer thanhTien);
}
