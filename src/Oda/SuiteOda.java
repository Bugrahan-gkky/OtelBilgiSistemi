package Oda;

public class SuiteOda extends Oda{
    public static final double TABAN_UCRET=1800.0;
    private boolean jakuzi;
    private int salonMetrekare;

    public SuiteOda(int odaNo,int kat,int kapasite,boolean jakuzi,int salonMetrekare){
        super(odaNo,kat,kapasite);
        this.jakuzi=jakuzi;
        this.salonMetrekare=salonMetrekare;
    }

    @Override
    public String getTip() {
        return "Suite"+(jakuzi?"Jakuzi var":"Jakuzi yok");
    }

    @Override
    public double gecelikUcret() {
        double ek = (salonMetrekare>25)? 300.0:0.0;
        if(jakuzi)ek+=400;
        return TABAN_UCRET+ek;
    }
}
