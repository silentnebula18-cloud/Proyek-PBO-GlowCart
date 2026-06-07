/**
 * Nama File: MVS.java
 * Deskripsi: file utama untuk menjalankan aplikasi
 *            terdiri dari kelas CR_Model, CR_View, CR_Controller, MVS
 * Tanggal Buat: 06 Mei 2026
 * Kelas: C
 * Anggota: Annis Fakhiroh Akbar (24060124130110)   
            Binar Ridha Wiritanaya (24060124140143)  
            Christianna Olivia J. M. (24060124140168)
            Dian Aulya Dewiyani (24060124130059)
 */
import java.util.*;

class CR_Model{
    static MySQLService service = new MySQLService();

    boolean isUserExist(String us, String pw){
        return service.isUserExist(us, pw);
    }

    void makeAccAdmin(String email, String username, String password, String noHp){
        service.S_makeAccAdmin(email, username, password, noHp);
    }

    Admin grabAdmin(String username, String password){
        return service.S_grabAdmin(username, password);
    }

    void makeAccMember(String email, String username, String password, String noHp){
        service.S_makeAccMember(email, username, password, noHp);
    }

    Member grabMember(String username, String password){
        return service.S_grabMember(username, password);
    }

    ArrayList<Product> grabAllProd(){
        return service.S_grabAllProd();
    }

    void updateProduct(Product p) {
        service.S_updateProduct(p);
    }

    ArrayList<Promo> grabAllPromo(){
        return service.S_grabAllPromo();
    }

    void updatePromo(Promo p){
        service.S_updatePromo(p);
    }

    String addPesanan(String memberID, String sendAddress, String packingNumber, 
                      String courier, double shippingFee, String promoCode){
        return service.S_addPesanan(memberID, sendAddress, packingNumber, courier, shippingFee, promoCode);
    }

    void addDetailPesanan(String orderID, int quantity, String productID){
        service.S_addDetailPesanan(orderID, quantity, productID);
    }

    void addReview(String memberID, String productID, String review, double rating){
        service.S_addReview(memberID, productID, review, rating);
    }

    void closeConnection(){
        service.closeConnection();
    }
}

class CR_View{
    Scanner ss = new Scanner(System.in);

    Integer getChoice(){
        System.out.print("Masukkan pilihan : ");
        return Integer.parseInt(ss.nextLine());
    }

    String getString(){ 
        return ss.nextLine();
    }

    int getInt(){ 
        return Integer.parseInt(ss.nextLine());
    }

    double getDouble(){ 
        return Double.parseDouble(ss.nextLine());
    }

    void PageCloseApp(){
        System.out.println();
        System.out.println("=============================================================");
        System.out.println(" Good Bye! Jangan lupa balik lagi yaa! ");
        System.out.println("=============================================================");
    }

    void PageLogin(){
        System.out.println();
        System.out.println("=============================================================");
        System.out.println(" Selamat datang di GlowCart! Login dulu, yuk!");
        System.out.println("=============================================================");
    }

    void PageMakeAcc(){
        System.out.println();
        System.out.println("=============================================================");
        System.out.println(" Akun belum ada nih :(.. Mau daftar dulu? ");
        System.out.println("=============================================================");
        System.out.println("(-1). Keluar aplikasi");
        System.out.println("1. Daftar sebagai Admin");
        System.out.println("2. Daftar sebagai Member");
        System.out.println("=============================================================");
    }

    String getEmail(){
        System.out.print("Masukkan email : ");
        return ss.nextLine();
    }

    String getNoHp(){
        System.out.print("Masukkan nomor telepon : ");
        return ss.nextLine();
    }

    String getUsername(){
        System.out.print("Masukkan username : ");
        return ss.nextLine();
    }

    String getPassword(){
        System.out.print("Masukkan password : ");
        return ss.nextLine();
    }

    void ShowAllProd(ArrayList<Product> allP){
        System.out.println("=============================================================");
        for(Product p : allP){
            System.out.println("- " + p.getProductID() + " " + p.getNamaProduct() + " " + p.getHarga());
        }
        System.out.println("=============================================================");
        System.out.println();
    }

    void A1(){
        System.out.println("=============================================================");
        System.out.println("Selanjutnya, mau ngapain?");
        System.out.println("=============================================================");
        System.out.println("0. Kembali ke halaman utama");
        System.out.println("11. Menambah product baru");
        System.out.println("22. Mengubah product");
        System.out.println("=============================================================");
    }

    Product A1P(){
        System.out.println();
        System.out.print("Masukan Nama Product: "); String namaProduct = getString();
        System.out.print("Masukan Nama Brand: "); String brand = getString(); 
        System.out.print("Masukan size: "); double size = getDouble();
        System.out.print("Masukan stok: "); int stok = getInt();
        System.out.print("Masukan harga: "); double harga = getDouble();
        System.out.print("Masukan kode BPOM: "); String BPOMcode = getString();
        System.out.print("Masukan ingredients: "); String ingredients = getString();
        System.out.print("Masukan deskripsi: "); String deskripsi = getString();
        System.out.print("Masukan cara pakai: "); String caraPakai = getString();

        Product p = null;
        try {
            p = new Product("", namaProduct, brand, size, stok, harga, BPOMcode, ingredients, deskripsi, caraPakai);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return p;
    }
    
    void HomeAdmin(String us){
        System.out.println();
        System.out.println("=============================================================");
        System.out.println(" Selamat datang di GlowCart, " + us + "! Mau ngapain hari ini?");
        System.out.println("=============================================================");
        System.out.println("(-1). Keluar aplikasi");
        System.out.println("1. Lihat Produk");
        System.out.println("2. Lihat Promo");
        System.out.println("=============================================================");
    }

    void HomeMember(String us){
        System.out.println();
        System.out.println("=============================================================");
        System.out.println(" Selamat datang di GlowCart, " + us + "! Mau ngapain hari ini?");
        System.out.println("=============================================================");
        System.out.println("(-1). Keluar aplikasi");
        System.out.println("1. Lihat Produk");
        System.out.println("2. Lihat Pesanan");
        System.out.println("=============================================================");
    }

    void ShowAllPromo(ArrayList<Promo> allP){
        System.out.println("=============================================================");
        for(Promo p : allP){
            System.out.println("- " + p.getPromoCode() + " | " + p.getDescription() + " | diskon " + (p.getDiscountPct()*100) + "%");
        }
        System.out.println("=============================================================");
        System.out.println();
    }

    void A2(){
        System.out.println("=============================================================");
        System.out.println("Selanjutnya, mau ngapain?");
        System.out.println("=============================================================");
        System.out.println("0. Kembali ke halaman utama");
        System.out.println("11. Menambah promo baru");
        System.out.println("22. Mengubah promo");
        System.out.println("=============================================================");
    }

    Promo A21(){
        System.out.println();
        System.out.print("Masukan Deskripsi Promo: "); String description = getString();
        System.out.print("Masukan Start Date (yyyy-MM-dd): "); String startDate = getString();
        System.out.print("Masukan End Date (yyyy-MM-dd): "); String endDate = getString();
        System.out.print("Masukan Discount (contoh 0.15 untuk 15%): "); double discountPct = getDouble();
        System.out.print("Masukan Quota: "); int quota = getInt();
        System.out.print("Masukan Minimum Pembelian: "); double minPurchase = getDouble();

        return new Promo(
            "",
            description,
            java.time.LocalDate.parse(startDate),
            java.time.LocalDate.parse(endDate),
            discountPct,
            quota,
            minPurchase
        );
    }

    String A21Desc(){
        System.out.println();
        System.out.println("Masukan Deskripsi Promo: "); String description = getString();
        return description;
    }

    double A21DiscPct(){
        System.out.println();
        System.out.println("Masukan Discount (contoh 0.15 untuk 15%): "); double discountPct = getDouble();
        return discountPct;
    }

    void ShowAllPesanan(ArrayList<Pesanan> allPs){
    System.out.println("=============================================================");
    for(Pesanan p : allPs){
        System.out.println("- " + p.getOrderID() + " | " + p.getOrderDate() + " | " + p.getCourier());
        }
    System.out.println("=============================================================");
    }

    void B1(){
        System.out.println("=============================================================");
        System.out.println("Pilih menu:");
        System.out.println("=============================================================");
        System.out.println("0. Kembali ke halaman utama");
        System.out.println("11. Lihat detail produk");
        System.out.println("12. Buat pesanan");
        System.out.println("=============================================================");
    }

    void B2(){
        System.out.println("=============================================================");
        System.out.println("Setelah pesan, mau bikin review?");
        System.out.println("=============================================================");
        System.out.println("0. Tidak");
        System.out.println("123. Ya, buat review");
        System.out.println("=============================================================");
    }
}

class CR_Controller{
    CR_View V;
    CR_Model M;

    CR_Controller(){
        V = new CR_View();
        M = new CR_Model();
        
        // Halaman Login
        V.PageLogin();
        String us = V.getUsername();
        String pw = V.getPassword();

        // Membuat akun jika akun tidak ada
        if(M.isUserExist(us, pw) == false){
            V.PageMakeAcc();
            Integer C_MakeAcc = V.getChoice();
            if(C_MakeAcc != -1){
                String email = V.getEmail();
                String username = V.getUsername();
                String password = V.getPassword();
                String noHp = V.getNoHp();

                if (C_MakeAcc == 1){
                    // membuat akun sebagai Admin
                    M.makeAccAdmin(email, username, password, noHp);
                }
                else if (C_MakeAcc == 2){
                    // membuat akun sebagai Member
                    M.makeAccMember(email, username, password, noHp);
                }
            }else{
                V.PageCloseApp();
                M.closeConnection();
            }
        }
        // Akun sudah ada
        else if(M.isUserExist(us, pw) == true){
            User currUser;

            if(M.grabMember(us, pw) == null){
                // Akun Admin
                currUser = M.grabAdmin(us, pw);
                V.HomeAdmin(us);
                Integer C_Add = V.getChoice();
                
                while(C_Add != -1){
                    if(C_Add == 1){
                        ArrayList<Product> allP = M.grabAllProd();
                        V.ShowAllProd(allP);
                        V.A1();
                        C_Add = V.getChoice();
                        
                        // Add product
                        if(C_Add == 11){
                            Product p = V.A1P();
                            if(p != null){
                                Admin adm = (Admin) currUser;
                                adm.addProduct(p);
                            }
                            C_Add = 0; // balik ke home admin

                        // Update product
                        } else if(C_Add == 22){
                            System.out.print("Masukkan productID yang ingin diubah: ");
                            String targetID = V.getString();

                            Product target = null;
                            for(Product pr : allP){
                                if(pr.getProductID().equals(targetID)){
                                    target = pr;
                                    break;
                                }
                            }

                            if(target == null){
                                System.out.println("Produk tidak ditemukan.");
                            } else {
                                Product pBaru = V.A1P();
                                if(pBaru != null){
                                    Admin adm = (Admin) currUser;
                                    adm.setProduct(target, pBaru);
                                    M.updateProduct(target);
                                }
                            }
                            C_Add = 0; // balik ke home admin
                        }

                    } else if (C_Add == 2){
                        ArrayList<Promo> allPromo = M.grabAllPromo();
                        V.ShowAllPromo(allPromo);
                        V.A2();
                        C_Add = V.getChoice();

                        // Add promo
                        if(C_Add == 11){
                            Promo p = V.A21();
                            Admin adm = (Admin) currUser;
                            adm.addPromo(p);
                            C_Add = 0;

                        // Update promo
                        } else if(C_Add == 22){
                            System.out.print("Masukkan promoCode yang ingin diubah: ");
                            String targetCode = V.getString();

                            Promo target = null;
                            for(Promo pr : allPromo){
                                if(pr.getPromoCode().equals(targetCode)){
                                    target = pr;
                                    break;
                                }
                            }

                            if(target == null){
                                System.out.println("Promo tidak ditemukan.");
                            } else {
                                String descNew = V.A21Desc();
                                double discpctNew = V.A21DiscPct();
                                Admin adm = (Admin) currUser;
                                adm.setPromo(target, descNew, discpctNew);
                                M.updatePromo(target);
                            }
                            C_Add = 0;
                        }

                    } else {
                        C_Add = 0;
                    }

                    // setelah selesai aksi, tampilkan lagi home admin
                    if(C_Add == 0){
                        V.HomeAdmin(us);
                        C_Add = V.getChoice();
                    }
                }

                if(C_Add == -1){
                    V.PageCloseApp();
                    M.closeConnection();
                }
            }else{
                // Akun Member
                currUser = M.grabMember(us, pw);
                Member currMember = (Member) currUser;
                V.HomeMember(us);
                Integer C_Add = V.getChoice();

                while(C_Add != -1){
                    if(C_Add == 1){
                        // Lihat semua produk
                        ArrayList<Product> allP = M.grabAllProd();
                        V.ShowAllProd(allP);
                        V.B1();
                        C_Add = V.getChoice();

                        // Lihat detail produk
                        if(C_Add == 11){
                            System.out.print("Masukkan productID: ");
                            String pid = V.getString();
                            for(Product pr : allP){
                                if(pr.getProductID().equals(pid)){
                                    pr.printInfo();
                                    break;
                                }
                            }
                            C_Add = 0;

                        // Buat pesanan
                        } else if(C_Add == 12){
                            System.out.print("Masukkan alamat pengiriman: ");
                            String address = V.getString();
                            System.out.print("Masukkan packing number (PKGxxxx): ");
                            String packNum = V.getString();
                            System.out.print("Masukkan courier: ");
                            String courier = V.getString();
                            System.out.print("Masukkan shipping fee: ");
                            double shipFee = V.getDouble();
                            System.out.print("Masukkan promoCode (kosongkan jika tidak ada): ");
                            String promoCode = V.getString();
                            if(promoCode.isEmpty()) promoCode = null;

                            // Buat pesanan di DB, dapat orderID
                            String orderID = M.addPesanan(currMember.getMemberID(), address, packNum, courier, shipFee, promoCode);

                            if(orderID != null){
                                // Input produk-produk yang dipesan
                                String lanjut = "ya";
                                ArrayList<String> productIDsDipesan = new ArrayList<>();
                                while(lanjut.equalsIgnoreCase("ya")){
                                    System.out.print("Masukkan productID: ");
                                    String pid = V.getString();
                                    System.out.print("Masukkan quantity: ");
                                    int qty = V.getInt();
                                    M.addDetailPesanan(orderID, qty, pid);
                                    productIDsDipesan.add(pid);

                                    System.out.print("Tambah produk lagi? (ya/tidak): ");
                                    lanjut = V.getString();
                                }

                                System.out.println("Pesanan " + orderID + " berhasil dibuat!");

                                // Tawarin review
                                V.B2();
                                Integer C_Review = V.getChoice();
                                if(C_Review == 123){
                                    for(String pid : productIDsDipesan){
                                        System.out.println("Review untuk produk " + pid);
                                        System.out.print("Masukkan komentar: ");
                                        String komentar = V.getString();
                                        System.out.print("Masukkan rating (0-5): ");
                                        double rating = V.getDouble();
                                        M.addReview(currMember.getMemberID(), pid, komentar, rating);
                                    }
                                }
                            }
                            C_Add = 0;
                        }

                    } else if(C_Add == 2){
                        // Lihat pesanan sendiri
                        ArrayList<Pesanan> allPs = currMember.getPesananList();
                        V.ShowAllPesanan(allPs);
                        C_Add = 0;

                    } else {
                        C_Add = 0;
                    }

                    if(C_Add == 0){
                        V.HomeMember(us);
                        C_Add = V.getChoice();
                    }
                }

                if(C_Add == -1){
                    V.PageCloseApp();
                    M.closeConnection();
                }
            }
        }
    }
}

public class MVS {
    public static void main(String[] args) {
        //kamus
        CR_Controller C;
        
        //algoritma
        try {
            C = new CR_Controller();
        } catch (Exception e) {
            System.out.println("error "+e.toString());
        }
    }
}