/**
 * Nama File: Pesanan.java
 * Deskripsi: atribut dan metode dalam kelas Pesanan
 * Tanggal Buat: 23 Mei 2026
 * Kelas: C
 * Anggota: Annis Fakhiroh Akbar (24060124130110)   
            Binar Ridha Wiritanaya (24060124140143)  
            Christianna Olivia J. M. (24060124140168)
            Dian Aulya Dewiyani (24060124130059)
 */

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Pesanan implements AppService{
    /*********************ATRIBUT*********************/
    private String orderID;
    private LocalDateTime orderDate;
    private String sendAddress;
    private String packingNumber;
    private String courier;
    private double shippingFee;
    private ArrayList<DetailPesanan> listDetailPesanan;
    private Promo promoUsed;

    /*********************METHOD*********************/
    /*------------------KONSTRUKTOR-----------------*/
    // konstruktor tanpa parameter
    public Pesanan(){
        this.listDetailPesanan = new ArrayList<>();
        this.promoUsed = new Promo();
    }

    // konstruktor dengan parameter
    public Pesanan(String orderID, LocalDateTime orderDate, String sendAddress, 
                   String packingNumber, String courier, double shippingFee, 
                   ArrayList<DetailPesanan> listDetailPesanan, Promo promoUsed){
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.sendAddress = sendAddress;
        this.packingNumber = packingNumber;
        this.courier = courier;
        this.shippingFee = shippingFee;
        this.listDetailPesanan = listDetailPesanan;
        this.promoUsed = promoUsed;
    } 

    /*------------------SELEKTOR-------------------*/
    // mengembalikan nilai atribut orderID
    public String getOrderID(){
        return this.orderID;
    }

    // mengembalikan nilai atribut orderDate
    public LocalDateTime getOrderDate(){
        return this.orderDate;
    }

    // mengembalikan nilai atribut sendAddress
    public String getSendAddress(){
        return this.sendAddress;
    }

    // mengembalikan nilai atribut packingNumber
    public String getPackingNumber(){
        return this.packingNumber;
    }

    // mengembalikan nilai atribut courier
    public String getCourier(){
        return this.courier;
    }

    // mengembalikan nilai atribut shippingFee
    public double getShippingFee(){
        return this.shippingFee;
    }

    // mengembalikan nilai atribut listDetailPesanan
    public ArrayList<DetailPesanan> getListDetailPesanan(){
        return this.listDetailPesanan;
    }

    // mengembalikan nilai atribut promoUsed
    public Promo getPromoUsed(){
        return this.promoUsed;
    }

    /*------------------MUTATOR-------------------*/
    // mengubah nilai atribut sendAddress
    public void setSendAddress(String sendAddress){
        this.sendAddress = sendAddress;
    }

    // mengubah nilai atribut packingNumber
    public void setPackingNumber(String packingNumber){
        this.packingNumber = packingNumber;
    }

    // mengubah nilai atribut courier
    public void setCourier(String courier){
        this.courier = courier;
    }

    // mengubah nilai atribut shippingFee
    public void setShippingFee(double shippingFee){
        this.shippingFee = shippingFee;
    }

    // mengubah nilai atribut listDetailPesanan
    public void setListDetailPesanan(ArrayList<DetailPesanan> listDetailPesanan){
        this.listDetailPesanan = listDetailPesanan;
    }

    // mengubah nilai atribut promoUsed
    public void setPromoUsed(Promo promoUsed){
        this.promoUsed = promoUsed;
    }
    
    /*------------------LAINNYA-------------------*/
    // mengembalikan total harga dari semua pesanan
    public double getTotal(){
        double total = 0;

        for(int i = 0; i < this.listDetailPesanan.size(); i++){
            total = total + (this.listDetailPesanan.get(i).getQuantity() * 
                             this.listDetailPesanan.get(i).getProduct().getHarga()
                            );
        }

        return total;
    }

    // mengembalikan biaya pembelian dengan memperhitungkan total bersih, promo, dan shippingFee
    public double getPayment(){
        if(this.promoUsed == null){
            return this.getTotal() + this.shippingFee;
        }
        else{
            if(promoUsed.isPromoValid()){
                int qNow = this.promoUsed.getQuota();
                this.promoUsed.setQuota(qNow- 1);
                assert this.getTotal() >= this.promoUsed.getMinPurchase(): "Tidak memenuhi minimum belanja";
                return this.getTotal() - this.getTotal()*this.promoUsed.getDiscountPct() + this.shippingFee;
            }
            else{
                this.promoUsed = null;
                return this.getTotal() + this.shippingFee;
            }
        }
    }

    // mencetak invoice untuk Pesanan ini
    public void cetakInvoice(){
        System.out.println("---------- Invoice ----------");
        System.out.println("OrderID        : " + this.orderID);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("OrderDate      : " + this.orderDate.format(formatter));
        System.out.println("Alamat Kirim   : " + this.sendAddress);
        System.out.println("Packing Number : " + this.packingNumber);
        System.out.println("Courier        : " + this.courier);
        System.out.println("Shipping Fee   : " + new DecimalFormat("'Rp '###,###.00").format(this.shippingFee));
        if (this.promoUsed == null) {
            System.out.println("PromoID        : Tidak ada promo yang digunakan");
        } else {
            System.out.println("PromoID        : " + this.promoUsed.getPromoCode());
        }
        System.out.println("------ Detail Pesanan ------");
        for(int i = 0; i < this.listDetailPesanan.size(); i++){
            System.out.println(this.listDetailPesanan.get(i).getQuantity() + " x " + 
                               this.listDetailPesanan.get(i).getProduct().getNamaProduct());
            System.out.println("---------------");
        }
        System.out.println("Total Harga : " + new DecimalFormat("'Rp '###,###.00").format(this.getTotal()));
        System.out.println("Grand Total : " + new DecimalFormat("'Rp '###,###.00").format(this.getPayment()));
    }

    // print nilai atribut-atribut Pesanan 
    @Override
    public void printInfo(){
        System.out.println("orderID        : " + this.orderID);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("orderDate      : " + this.orderDate.format(formatter));
        System.out.println("sendAddress    :" + this.sendAddress);
        System.out.println("packingNumber  :" + this.packingNumber);
        System.out.println("courier        : " + this.courier);
        System.out.println("shippingFee    : " + new DecimalFormat("'Rp '###,###.00").format(this.shippingFee));
        System.out.println("----- promoUsed -----"); 
        this.promoUsed.cetakSnk();
        System.out.println("----- listDetailPesanan -----"); 
        for(int i = 0; i < this.listDetailPesanan.size(); i++){
            this.listDetailPesanan.get(i).printDetail();
            System.out.println("------------------------");
        }
    }

    // mencari suatu pesanan dengan masukan string (orderID)
    @Override
    public boolean search(String key) {
        if (key != null && orderID != null) {
            return orderID.equalsIgnoreCase(key);
        }
        return false;
    }

} // end of Class Pesanan