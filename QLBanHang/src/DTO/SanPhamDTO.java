/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Shadow
 */
public class SanPhamDTO {
    private String maSP,tenSP,dvt,maLoai,maNsx,img;
    private int sl,gia;
    public SanPhamDTO()
    {
        
    }
    public SanPhamDTO(String maSP, String tenSP, String dvt, String maLoai, String maNsx, String img, int sl, int gia) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.dvt = dvt;
        this.maLoai = maLoai;
        this.maNsx = maNsx;
        this.img = img;
        this.sl = sl;
        this.gia = gia;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getDvt() {
        return dvt;
    }

    public void setDvt(String dvt) {
        this.dvt = dvt;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getMaNsx() {
        return maNsx;
    }

    public void setMaNsx(String maNsx) {
        this.maNsx = maNsx;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}
