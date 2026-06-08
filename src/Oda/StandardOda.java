package Oda;

public class StandardOda extends Oda{
    private static final double TABAN_UCRET=900.0;
    private boolean kahvaltiDahil;
    public StandardOda(int odaNo,int kat , int kapasite,boolean kahvaltiDahil){
        super(odaNo,kat,kapasite);
        this.kahvaltiDahil=kahvaltiDahil;
    }

    @Override
    public String getTip() {
        return "Standart"+(kahvaltiDahil ? "Kahvaltı Dahil":"Kahvaltı Dahil Değil");
    }

    @Override
    public double gecelikUcret() {
        return kahvaltiDahil?(TABAN_UCRET+150.0):TABAN_UCRET;
    }


}
