package Oda;

public abstract class Oda {
    public static final String OTEL_ADI = "Fırat Üniversitesi Uygulama Oteli";
    //encapsulation
    private static int toplamOda = 0;
    private int odaNo;
    private int kat;
    private boolean musait;
    protected int kapasite;

    public Oda(int odaNo,int kat,int kapasite){
        this.odaNo=odaNo;
        this.kat=kat;
        this.kapasite=kapasite;
        this.musait=true;
        toplamOda++;
    }


    //Getter
    public int getOdaNo(){return odaNo;}
    public int getKat(){return kat;}
    public int getKapasite(){return kapasite;}
    public boolean isMusait(){return musait;}
    public static int getToplamOda(){return toplamOda;}

    public void setMusait(boolean musait){
        this.musait=musait;
    }

    public abstract String getTip();
    public abstract double gecelikUcret();

    public void bilgiGoster(){
        System.out.println("---------------------------------------");
        System.out.println("Otel      : " + OTEL_ADI);
        System.out.println("Oda No    : " + odaNo);
        System.out.println("Kat       : " + kat);
        System.out.println("Tip       : " + getTip());
        System.out.println("Kapasite  : " + kapasite);
        System.out.println("Durum     : " + (musait ? "✓ Müsait" : "✗ Dolu"));
        System.out.println("Gecelik ₺ : " + gecelikUcret());
    }
}
