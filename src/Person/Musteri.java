package Person;
import Oda.Oda;
import Person.Musteri;
public class Musteri {
    private static int sonMusteriId=0;
    private int musteriId;
    private String adSoyad;
    private String mail;
    private String telefon;

    public Musteri(String adSoyad,String mail,String telefon){
        this.musteriId=++sonMusteriId;
        this.adSoyad=adSoyad;
        setEmail(mail);
        setTelefon(telefon);
    }
    public int getMusteriId(){return musteriId;}
    public String getAdSoyad(){return adSoyad;}
    public String getMail(){return mail;}
    public String getTelefon(){return telefon;}

    public void setEmail(String mail){
        if(mail != null&&mail.contains("@")&&mail.contains(".")){
            this.mail=mail;
        }else{
            System.out.println("⚠ Geçersiz e-posta. 'bilinmiyor' atandı.");
            this.mail = "bilinmiyor";
        }
    }

    public void setTelefon(String telefon){
        if(telefon != null&&telefon.matches("[0-9+]{8,20}")){
            this.telefon=telefon;
        }else{
            System.out.println("⚠ Geçersiz telefon. 'bilinmiyor' atandı.");
            this.telefon = "bilinmiyor";
        }
    }

    public void bilgiGoster(){
        System.out.println("Müşteri #" + musteriId + " — " + adSoyad
                + " | Email: " + mail
                + " | Tel: " + telefon);
    }
}
