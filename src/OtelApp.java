import Islem.Rezervasyon;
import Oda.*;
import Person.*;
import java.util.ArrayList;
import java.util.Scanner;

//bu niye hata veriyor
public class OtelApp {
    public static ArrayList<Oda> odalar = new ArrayList<>();
    public static ArrayList<Musteri> musteriler = new ArrayList<>();
    public static ArrayList<Rezervasyon> rezervasyonlar = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        ornekVerilerYukle();
        hosgeldinEkrani();

        boolean calisiyorMu=true;
        while (calisiyorMu){
            anaMenuGoster();
            int secim = secimAl();
            switch (secim){
                case 1:
                    odaListesi();
                    break;
                case 2:
                    musteriEkle();
                    break;
                case 3:
                    musteriListesi();
                    break;
                case 4:
                    rezervasyonOlustur();
                    break;
                case 5:
                    checkInIslemi();
                    break;
                case 6:
                    checkOutIslemi();
                    break;
                case 7:
                    faturaGoster();
                    break;
                case 0:{
                    calisiyorMu=false;cikis();
                }
                default:
                    System.out.println("⚠ Geçersiz seçim.");
            }
        }
    }
    static void hosgeldinEkrani() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║      " + Oda.OTEL_ADI + "      ║");
        System.out.println("║            Otel Yönetim Sistemi (CLI)         ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();
    }
    static void anaMenuGoster() {
        System.out.println("\n===== ANA MENÜ =====");
        System.out.println("1) Oda Listesi");
        System.out.println("2) Müşteri Ekle");
        System.out.println("3) Müşteri Listesi");
        System.out.println("4) Rezervasyon Oluştur");
        System.out.println("5) Check-in Yap");
        System.out.println("6) Check-out Yap");
        System.out.println("7) Fatura Göster");
        System.out.println("0) Çıkış");
        System.out.print("Seçiminiz: ");
    }
    // 1 — ODA LİSTESİ
    static void odaListesi() {
        System.out.println("\n🏨 ODA LİSTESİ (" + odalar.size() + " kayıt)");
        System.out.println("Toplam oda: " + Oda.getToplamOda());

        for (Oda o : odalar) {
            o.bilgiGoster();
        }
    }
    static void musteriEkle() {
        System.out.println("\n👤 MÜŞTERİ EKLE");
        System.out.print("Ad Soyad: ");
        String ad = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Telefon: ");
        String tel = scanner.nextLine();

        Musteri m = new Musteri(ad, email, tel);
        musteriler.add(m);
        System.out.println("✓ Müşteri eklendi: #" + m.getMusteriId());
    }
    static void musteriListesi() {
        System.out.println("\n👥 MÜŞTERİLER (" + musteriler.size() + " kayıt)");
        if (musteriler.isEmpty()) {
            System.out.println("Henüz müşteri yok.");
            return;
        }
        for (Musteri m : musteriler) m.bilgiGoster();
    }
    static void rezervasyonOlustur() {
        System.out.println("\n📝 REZERVASYON OLUŞTUR");

        if (musteriler.isEmpty()) {
            System.out.println("⚠ Önce müşteri eklemelisiniz.");
            return;
        }

        // Müşteri seç
        musteriListesi();
        System.out.print("Müşteri ID girin: ");
        int mid = secimAl();

        Musteri secilen = null;
        for (Musteri m : musteriler) {
            if (m.getMusteriId() == mid) { secilen = m; break; }
        }
        if (secilen == null) {
            System.out.println("⚠ Müşteri bulunamadı.");
            return;
        }

        // Uygun oda seç
        ArrayList<Oda> uygunOdalar = new ArrayList<>();
        for (Oda o : odalar) {
            if (o.isMusait()) uygunOdalar.add(o);
        }

        if (uygunOdalar.isEmpty()) {
            System.out.println("⚠ Müsait oda yok.");
            return;
        }

        System.out.println("Müsait odalar:");
        for (int i = 0; i < uygunOdalar.size(); i++) {
            System.out.println("[" + (i + 1) + "] Oda #" + uygunOdalar.get(i).getOdaNo()
                    + " | " + uygunOdalar.get(i).getTip()
                    + " | ₺" + uygunOdalar.get(i).gecelikUcret());
        }

        System.out.print("Oda seç (1-" + uygunOdalar.size() + "): ");
        int odaIdx = secimAl() - 1;
        if (odaIdx < 0 || odaIdx >= uygunOdalar.size()) {
            System.out.println("⚠ Geçersiz oda seçimi.");
            return;
        }
        Oda oda = uygunOdalar.get(odaIdx);

        System.out.print("Kaç gece kalınacak?: ");
        int gece = secimAl();
        if (gece <= 0) {
            System.out.println("⚠ Gece sayısı 1 veya daha büyük olmalı.");
            return;
        }

        System.out.print("Ödeme yöntemi (Kart/Nakit/Havale): ");
        String odemeYontemi = scanner.nextLine();

        Rezervasyon r = new Rezervasyon(secilen, oda, gece, odemeYontemi);
        rezervasyonlar.add(r);

        System.out.println("✓ Rezervasyon oluşturuldu. Rez No: " + r.getRezervasyonNo());
        System.out.println("Not: Check-in için menüden check-in seçin.");
    }
    // 5 — CHECK-IN
    static void checkInIslemi() {
        System.out.println("\n🟢 CHECK-IN");
        Rezervasyon r = rezervasyonSec("Check-in yapılacak rezervasyon no: ");
        if (r == null) return;
        r.checkIn();
    }

    // 6 — CHECK-OUT
    static void checkOutIslemi() {
        System.out.println("\n🔴 CHECK-OUT");
        Rezervasyon r = rezervasyonSec("Check-out yapılacak rezervasyon no: ");
        if (r == null) return;
        r.checkOut();
    }

    // 7 — FATURA
    static void faturaGoster() {
        System.out.println("\n💳 FATURA");
        Rezervasyon r = rezervasyonSec("Faturası gösterilecek rezervasyon no: ");
        if (r == null) return;
        r.faturaGoster();
    }
    static Rezervasyon rezervasyonSec(String prompt) {
        if (rezervasyonlar.isEmpty()) {
            System.out.println("⚠ Rezervasyon yok.");
            return null;
        }

        System.out.println("Mevcut rezervasyonlar:");
        for (Rezervasyon r : rezervasyonlar) {
            System.out.println("- Rez No: " + r.getRezervasyonNo()
                    + " | Oda #" + r.getOda().getOdaNo()
                    + " | " + (r.isCheckInYapildi() ? "Check-in" : "Bekliyor")
                    + (r.isCheckOutYapildi() ? " / Check-out" : ""));
        }

        System.out.print(prompt);
        int rezNo = secimAl();
        for (Rezervasyon r : rezervasyonlar) {
            if (r.getRezervasyonNo() == rezNo) return r;
        }

        System.out.println("⚠ Rezervasyon bulunamadı.");
        return null;
    }

    static int secimAl() {
        try { return Integer.parseInt(scanner.nextLine().trim()); }
        catch (Exception e) { return -1; }
    }

    static void cikis() {
        System.out.println("\nGüle güle! " + Oda.OTEL_ADI);
    }

    static void ornekVerilerYukle() {
        // Odalar (çok biçimlilik: Oda listesine farklı alt tipler ekleniyor)
        odalar.add(new StandardOda(101, 1, 2, true));
        odalar.add(new StandardOda(102, 1, 3, false));
        odalar.add(new SuiteOda(201, 2, 2, true, 30));
        odalar.add(new SuiteOda(202, 2, 4, false, 24));

        // Örnek müşteriler
        musteriler.add(new Musteri("Ferhat Uçar", "fucar@firat.edu.tr", "+90 5xx xxx xx xx"));
        musteriler.add(new Musteri("Ayşe Demir", "ayse@mail.com", "0532 000 00 00"));
        musteriler.add(new Musteri("Mehmet Kaya", "gecersizemail", "ABC")); // uyarı üretir
    }

}