package com.istvanbalint.eletbiztositas;

public class Csomag {
     int ft;
     String leiras;
     String nev;
    public Csomag(){}
    public Csomag(int ft, String leiras, String nev) {
        this.ft = ft;
        this.leiras = leiras;
        this.nev = nev;
    }

    public String getFt() {
        return ""+this.ft;
    }

    public void setFt(int ft) {
        this.ft = ft;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }
}
