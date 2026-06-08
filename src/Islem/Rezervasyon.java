package Islem;

import Oda.Oda;
import Person.Musteri;

public class Rezervasyon {
    private static int sonRezNo=1000;
    private int rezervasyonNo;
    private Musteri musteri;
    private Oda oda;
    private int geceSayisi;

    private boolean checkInYapildi;
    private boolean checkOutYapildi;

    private OdemeBilgisi odeme;

    public Rezervasyon(Musteri musteri,Oda oda,int geceSayisi,String odemeYontemi){
        this.rezervasyonNo=++sonRezNo;
        this.musteri=musteri;
        this.oda=oda;
        this.geceSayisi=geceSayisi;
        this.checkInYapildi=false;
        this.checkOutYapildi=false;

        this.odeme=new OdemeBilgisi(odemeYontemi);
    }

    //Getter
    public int getRezervasyonNo(){return rezervasyonNo;}
    public Musteri getMusteri(){return musteri;}
    public Oda getOda(){return oda;}
    public int getGeceSayisi(){return geceSayisi;}

    public boolean isCheckInYapildi() {
        return checkInYapildi;
    }

    public boolean isCheckOutYapildi() {
        return checkOutYapildi;
    }
    public double ToplamTutar(){
        return oda.gecelikUcret()*geceSayisi;
    }

    public void checkIn(){
        if(checkInYapildi){
            System.out.println("⚠ Zaten check-in yapılmış.");
            return;
        }
        if(!oda.isMusait()){
            System.out.println("⚠ Oda dolu görünüyor. Check-in yapılamadı.");
            return;
        }
        checkInYapildi=true;
        oda.setMusait(false);
        System.out.println("✓ Check-in tamamlandı. Oda #" + oda.getOdaNo());
    }
    public void checkOut(){
        if(!checkInYapildi){
            System.out.println("⚠ Check-in yapılmadan check-out yapılamaz.");
            return;
        }
        if(checkOutYapildi){
            System.out.println("⚠ Zaten check-out yapılmış.");
            return;
        }
        checkOutYapildi = true;
        oda.setMusait(true);
        System.out.println("✓ Check-out tamamlandı. Oda #" + oda.getOdaNo());
    }
    public void faturaGoster(){
        System.out.println("\n========= FATURA =========");
        System.out.println("Rezervasyon No: " + rezervasyonNo);
        System.out.println("Müşteri       : " + musteri.getAdSoyad());
        System.out.println("Oda           : #" + oda.getOdaNo() + " (" + oda.getTip() + ")");
        System.out.println("Gece          : " + geceSayisi);
        System.out.println("Gecelik Ücret : " + oda.gecelikUcret());
        System.out.println("Toplam Tutar  : " + ToplamTutar());
        System.out.println("Ödeme         : " + odeme.odemeYontemi + " | Durum: " + odeme.getDurum());
        System.out.println("==========================\n");
    }
}
class OdemeBilgisi{
    public String odemeYontemi;
    private boolean odendi;

    OdemeBilgisi(String odemeYontemi){
        this.odemeYontemi=odemeYontemi;
        this.odendi=false;
    }

    public void odemeAl(){odendi=true;}

    public String getDurum(){
        return odendi?"Ödendi":"Beklemede";
    }
}