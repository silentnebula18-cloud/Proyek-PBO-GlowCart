/**
 * Nama File: Promo.java
 * Deskripsi: atribut dan metode dalam kelas Promo
 * Tanggal Buat: 23 Mei 2026
 * Kelas: C
 * Anggota: Annis Fakhiroh Akbar (24060124130110)   
            Binar Ridha Wiritanaya (24060124140143)  
            Christianna Olivia J. M. (24060124140168)
            Dian Aulya Dewiyani (24060124130059)
 */

import java.time.LocalDate;

public class Promo {
    /*************ATRIBUT********************/
    private String promoCode;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private double discountPct;
    private int quota;
    private double minPurchase;


    /*****************METHOD********************/
    //============Konstruktor=============
    //Konstruktor tanpa Parameter
    public Promo(){

    }

    //Konstruktor dengan Parameter
    public Promo(String promoCode, String description, LocalDate startDate, LocalDate endDate, double discountPct, int quota, double minPurchase){
        this.promoCode = promoCode;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPct = discountPct;
        this.quota = quota;
        this.minPurchase = minPurchase;
    }

    //================SELEKTOR==============
    //Mengembalikan kode promo
    public String getPromoCode(){
        return promoCode;
    }

    //Mengembalikan deskripsi promo
    public String getDescription(){
        return description;
    }

    //Mengembalikan tanggal mulai promo
    public LocalDate getStartDate(){
        return startDate;
    }

    //Mengembalikan tanggal akhir promo
    public LocalDate getEndDate(){
        return endDate;
    }

    //Mengembalikan nilai diskon
    public double getDiscountPct(){
        return discountPct;
    }

    //Mengembalikan sisa kuota promo
    public int getQuota(){
        return quota;
    }

    //Mengembalikan minimum pembelian
    public double getMinPurchase(){
        return minPurchase;
    }

    //==================MUTATOR=================
    //Mengubah deskripsi promo
    public void setDescription(String description){
        this.description = description;
    }

    //Mengubah tanggal mulai promo
    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    //Mengubah tanggal akhir promo
    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }

    //Mengubah nilai diskon
    public void setDiscount(double discountPct){
        this.discountPct = discountPct;
    }

    //Mengubah kuota promo
    public void setQuota(int quota){
        this.quota = quota;
    }

    //Mengubah minimum pembelian
    public void setMinPurchase(double minPurchase){
        this.minPurchase = minPurchase;
    }

    //==================Lainnya=================
    //Mengecek apakah promo yang digunakan masih valid
    public boolean isPromoValid() {
        LocalDate today = LocalDate.now();
        if (today.isBefore(startDate) || today.isAfter(endDate)) {
            return false;
        }
        if (quota <= 0) {
            return false;
        }
        return true;
    }

    //Menampilkan informasi Promo
    public void cetakSnk(){
        System.out.println("Kode Promo : " + promoCode);
        System.out.println("Deskripsi  : " + description);
        System.out.println("Berlaku    : " + startDate + " s/d " + endDate);
        System.out.println("Diskon     : " + (discountPct * 100) + "%");
        System.out.println("Sisa Kuota : " + quota);
        System.out.println("Min Belanja: " + minPurchase);
    }
} //end of class Promo