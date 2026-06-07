/**
 * Nama File: Product.java
 * Deskripsi: atribut dan metode dalam kelas Product
 * Tanggal Buat: 23 Mei 2026
 * Kelas: C
 * Anggota: Annis Fakhiroh Akbar (24060124130110)   
            Binar Ridha Wiritanaya (24060124140143)  
            Christianna Olivia J. M. (24060124140168)
            Dian Aulya Dewiyani (24060124130059)
 */

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Product implements AppService {
    /************ATRIBUT****************/
    private String productID;
    private String namaProduct;
    private String brand;
    private double size;
    private int stok;
    private double harga;
    private String BPOMcode;
    private String ingredients;
    private String deskripsi;
    private String caraPakai;
    private ArrayList<Review> reviewList;

    /**********METHOD*******************/
    //KONSTRUKTOR
    //Konstruktor untuk membuat Product tanpa parameter
    public Product() {
        this.reviewList = new ArrayList<>();
    }

    //konstruktor untuk membuat Product dengan nilai productID, namaProduct, brand, 
    // size, stok, harga, BPOMcode, ingredients, deskripsi, caraPakai tertentu
    public Product(String productID, String namaProduct, String brand, double size, 
                    int stok, double harga, String BPOMcode, String ingredients, 
                    String deskripsi, String caraPakai) throws Exception {
        if (size < 0) {
            throw new Exception("Gagal membuat produk: Size tidak boleh kurang dari 0!");
        }
        if (stok < 0) {
            throw new Exception("Gagal membuat produk: Stok tidak boleh kurang dari 0!");
        }
        if (harga < 0) {
            throw new Exception("Gagal membuat produk: Harga tidak boleh kurang dari 0!");
        }
        this.productID = productID;
        this.namaProduct = namaProduct;
        this.brand = brand;
        this.size = size;
        this.stok = stok;
        this.harga = harga; 
        this.BPOMcode = BPOMcode;
        this.ingredients = ingredients;
        this.deskripsi = deskripsi;
        this.caraPakai = caraPakai;
        this.reviewList = new ArrayList<>();
    }

    //SELEKTOR
    //mengembalikan productID dari Product
    public String getProductID() {
        return productID;
    }

    //mengembalikan namaProduct dari Product
    public String getNamaProduct() {
        return namaProduct;
    }

    //mengembalikan brand dari Product
    public String getBrand() {
        return brand;
    }

    //mengembalikan size dari Product
    public double getSize() {
        return size;
    }

    //mengembalikan stok dari Product
    public int getStok() {
        return stok;
    }

    //mengembalikan harga dari Product
    public double getHarga() {
        return harga;
    }

    //mengembalikan BPOMcode dari Product
    public String getBPOMcode() {
        return BPOMcode;
    }

    //mengembalikan ingredients dari Product
    public String getIngredients() {
        return ingredients;
    }

    //mengembalikan deskripsi dari Product
    public String getDeskripsi() {
        return deskripsi;
    }

    //mengembalikan caraPakai dari Product
    public String getCaraPakai() {
        return caraPakai;
    }

    //mengembalikan list review dari Product
    public ArrayList<Review> getReviewList() {
        return reviewList;
    }

    //MUTATOR
    //mengeset namaProduct dengan nilai baru nama produk
    public void setNamaProduct(String namaProduct) {
        this.namaProduct = namaProduct;
    }

    //mengeset brand dengan nilai baru brand
    public void setBrand(String brand) {
        this.brand = brand;
    }

    //mengeset size dengan nilai baru size
    public void setSize(double size) throws Exception {
        if (size < 0) {
            throw new Exception("Gagal mengubah size: Size tidak boleh kurang dari 0!");
        }
        this.size = size;
    }

    //mengeset stok dengan nilai baru stok
    public void setStok(int stok) throws Exception {
        if (stok < 0) {
            throw new Exception("Gagal mengubah stok: Stok tidak boleh kurang dari 0!");
        }
        this.stok = stok;
    }

    //mengeset harga dengan nilai baru harga
    public void setHarga(double harga) throws Exception {
        if (harga < 0) {
            throw new Exception("Gagal mengubah harga: Harga tidak boleh kurang dari 0!");
        }
        this.harga = harga;
    }

    //mengeset ingredients dengan nilai baru ingredients
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    //mengeset deskripsi dengan nilai baru deskripsi
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    //mengeset caraPakai dengan nilai baru cara pakai
    public void setCaraPakai(String caraPakai) {
        this.caraPakai = caraPakai;
    }

    //mengeset reviewList dengan nilai baru list review
    public void setReviewList(ArrayList<Review> reviewList) {
        this.reviewList = reviewList;
    }

    //METHOD LAINNYA
    // mengembalikan banyaknya elemen yang ada di list reviewList
    public int getReviewNumber() {
        return reviewList.size();
    }

    //mengembalikan True jika stok produk sudah habis atau sama dengan 0
    public boolean isHabis() {
        return getStok() <= 0;
    }

    //menampilkan status ketersediaan stok produk
    public void printHabis() {
        if (isHabis()) {
            System.out.println("Stok dengan ID Product: " + productID + " sudah habis.");
        } else {
            System.out.println("Stok dengan ID Product: " + productID + " masih tersedia sebanyak " + stok + " pcs.");
        }
    }

    // mengembalikan rata-rata rating dari sebuah produk
    public double getAvgRating() {
        double jumlahRating = 0;
        if (reviewList.size() == 0) {
            return 0.0; 
        }
        for (int i = 0; i < reviewList.size(); i++) {
            jumlahRating += reviewList.get(i).getRating();
        }
        return jumlahRating/reviewList.size();
    }

    //menampilkan semua atribut yang dimiliki Product
    @Override
    public void printInfo() {
        System.out.println("ID Produk: " + productID);
        System.out.println("Nama Produk: " + namaProduct);
        System.out.println("Brand: " + brand);
        System.out.println("Size: " + size);
        System.out.println("Stok: " + stok);
        System.out.println("Harga: " + new DecimalFormat("'Rp '###,###.00").format(harga));
        System.out.println("BPOM: " + BPOMcode);
        System.out.println("Ingredients: " + ingredients);
        System.out.println("Deskripsi: " + deskripsi);
        System.out.println("Cara Pakai: " + caraPakai);
        System.out.println("Daftar review: ");
        if (reviewList.size() == 0) {
            System.out.println("Belum ada ulasan untuk produk ini.");
        } else {
            for (int i = 0; i < this.reviewList.size(); i++) {
            reviewList.get(i).cetakReview();
            }
        }
    }

    //mengembalikan True jika ditemukan sesuai kata kunci key
    @Override
    public boolean search(String key) {
        if (key != null && namaProduct != null) {
            return namaProduct.equalsIgnoreCase(key);
        }
        return false;
    }

    //menambahkan sebuah review ke atribut RewiewList
    public void addReview(Review newReview) {
        this.reviewList.add(newReview);
    }
} //end class Product