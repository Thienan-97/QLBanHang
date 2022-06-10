/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author thienan
 */
public class KhuyenMaiDTO {
    private String MALOAIGG, MASP, DOTGG, BATDAU, KETTHUC;

    public KhuyenMaiDTO(String MALOAIGG, String MASP, String DOTGG, String BATDAU, String KETTHUC) {
        this.MALOAIGG = MALOAIGG;
        this.MASP = MASP;
        this.DOTGG = DOTGG;
        this.BATDAU = BATDAU;
        this.KETTHUC = KETTHUC;
    }

    public String getMALOAIGG() {
        return MALOAIGG;
    }

    public void setMALOAIGG(String MALOAIGG) {
        this.MALOAIGG = MALOAIGG;
    }

    public String getMASP() {
        return MASP;
    }

    public void setMASP(String MASP) {
        this.MASP = MASP;
    }

    public String getDOTGG() {
        return DOTGG;
    }

    public void setDOTGG(String DOTGG) {
        this.DOTGG = DOTGG;
    }

    public String getBATDAU() {
        return BATDAU;
    }

    public void setBATDAU(String BATDAU) {
        this.BATDAU = BATDAU;
    }

    public String getKETTHUC() {
        return KETTHUC;
    }

    public void setKETTHUC(String KETTHUC) {
        this.KETTHUC = KETTHUC;
    }
    
    
}
