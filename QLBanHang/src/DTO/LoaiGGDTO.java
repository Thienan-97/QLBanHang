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
public class LoaiGGDTO {
    private String MALOAIGG;
    private int PHANTRAMGG;

    public LoaiGGDTO(String MALOAIGG, int PHANTRAMGG) {
        this.MALOAIGG = MALOAIGG;
        this.PHANTRAMGG = PHANTRAMGG;
    }

    public String getMALOAIGG() {
        return MALOAIGG;
    }

    public void setMALOAIGG(String MALOAIGG) {
        this.MALOAIGG = MALOAIGG;
    }

    public int getPHANTRAMGG() {
        return PHANTRAMGG;
    }

    public void setPHANTRAMGG(int PHANTRAMGG) {
        this.PHANTRAMGG = PHANTRAMGG;
    }
    
    
}
