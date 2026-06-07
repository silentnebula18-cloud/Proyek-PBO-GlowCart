/**
 * Nama File: MySQLService.java
 * Deskripsi: atribut dan metode dalam kelas MySQLService. 
 *            Kelas ini digunakan untuk mengirimkan perintah mySQL ke database
 * Tanggal Buat: 06 Mei 2026
 * Kelas: C
 * Anggota: Annis Fakhiroh Akbar (24060124130110)   
            Binar Ridha Wiritanaya (24060124140143)  
            Christianna Olivia J. M. (24060124140168)
            Dian Aulya Dewiyani (24060124130059)
 */

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MySQLService {
    // ================= ATRIBUT =================
    Connection koneksi = null;

    // ================= METODE =================
    // Constructor
    public MySQLService(){
        this.koneksi = MySQLUtil.getConnection();
    }

    // Menutup koneksi ke DB
    public void closeConnection(){
        MySQLUtil.closeConnection();
    }

    // mencari tahu apakah User ada di database atau tidak
    public boolean isUserExist(String us, String pw){
        String query = "SELECT * FROM users WHERE username ='" + us + "' AND  password ='" + pw + "'";
        try{
            Statement s = koneksi.createStatement();
            ResultSet rs = s.executeQuery(query);

            return rs.next();

        } catch(SQLException e){
            System.out.println("Gagal UserExist: " + e.getMessage());
        }
        return false;
    }

    // Memasukan data akun Admin baru
    public void S_makeAccAdmin(String email, String username, String password, String noHp){
        String query = "CALL makeAccAdmin('" + email + "', '" + username + "', '" + password + "', '" + noHp +"')";
        try{
            Statement s = koneksi.createStatement();
            s.executeUpdate(query);
            System.out.println("Akun Admin baru berhasil ditambahkan. Buka aplikasi lagi untuk melanjutkan.");
        } catch (SQLException e) {
            System.out.println("Gagal membuat akun baru: " + e.getMessage());
        }
    }

    // mengembalikan objek Admin
    public Admin S_grabAdmin(String i_username, String i_password){
        String query = "CALL grabAdmin('"+ i_username + "', '" + i_password +"')";
        try{
            Statement s = koneksi.createStatement();
            ResultSet rs = s.executeQuery(query);
            if (!rs.next()) {
                return null;
            }
    
            return new Admin(
                rs.getString("userID"),
                rs.getString("email"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("noHP"),
                rs.getString("adminID"),
                rs.getString("status")
            );
            
        } catch(SQLException e){
            System.out.println("Gagal mengambil data Admin: " + e.getMessage());
        }
        return null;
    }

    // Memasukan data akun Member baru
    public void S_makeAccMember(String email, String username, String password, String noHp){
        String query = "CALL makeAccMember('" + email + "', '" + username + "', '" + password + "', '" + noHp +"')";
        try{
            Statement s = koneksi.createStatement();
            s.executeUpdate(query);
            System.out.println("Akun Member baru berhasil ditambahkan. Buka aplikasi lagi untuk melanjutkan.");
        } catch (SQLException e) {
            System.out.println("Gagal membuat akun baru: " + e.getMessage());
        }
    }

    // mengembalikan objek Member
    public Member S_grabMember(String i_username, String i_password){
        String query = "CALL grabMember('"+ i_username + "', '" + i_password +"')";
        try{
            Statement s = koneksi.createStatement();
            ResultSet rs = s.executeQuery(query);
            if (!rs.next()) {
                return null;
            }

            String userID = rs.getString("userID");
            String email = rs.getString("email");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String noHP = rs.getString("noHP");
            String memberID = rs.getString("memberID");
            int point = rs.getInt("point");
            LocalDate tglJoin = rs.getDate("tglJoin").toLocalDate();
            Member out = new Member(userID, email, username, password, noHP, memberID, point, tglJoin);

            ArrayList<Review> reviewList = S_grabRvMbList(memberID);
            out.setReviewList(reviewList);
            
            ArrayList<Pesanan> pesananList = S_grabPsList(memberID);
            out.setPesananList(pesananList);

            return out;

        } catch(SQLException e){
            System.out.println("Gagal mengambil data Member: " + e.getMessage());
        }
        return null;
    }

    // mengembalikan reviewList dari sebuah Member
    public ArrayList<Review> S_grabRvMbList(String memberID){
        ArrayList<Review> reviewList = new ArrayList<>();
        String query = "CALL grabRvMbList('" + memberID + "')";
        try{
            Statement s = koneksi.createStatement();
            ResultSet rs = s.executeQuery(query);

            while(rs.next()){
                String reviewID = rs.getString("reviewID");
                LocalDate tglReview = rs.getDate("tglReview").toLocalDate();
                String review = rs.getString("review");
                double rating = rs.getDouble("rating");

                Review inp = null;

                try{
                    inp = new Review(reviewID, tglReview, review, rating);
                } catch (Exception a){
                    System.out.println(a.getMessage());
                }

                reviewList.add(inp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return reviewList;
    }

    // mengembalikan pesananList dari sebuah Member
    public ArrayList<Pesanan> S_grabPsList(String memberID){
        ArrayList<Pesanan> pesananList = new ArrayList<>();
        String query = "CALL grabPsList('" + memberID + "')";
        try{
            Statement s = koneksi.createStatement();
            ResultSet rs = s.executeQuery(query);

            while(rs.next()){
                String orderID = rs.getString("orderID");
                LocalDateTime orderDate = rs.getTimestamp("orderDate").toLocalDateTime();
                String sendAddress = rs.getString("sendAddress");
                String packingNumber = rs.getString("packingNumber");
                String courier = rs.getString("courier");
                double shippingFee = rs.getDouble("shippingFee");
                Promo promoUsed = S_grabPsPromo(orderID);
                ArrayList<DetailPesanan> listDP = S_grabPsDP(orderID);

                Pesanan p = new Pesanan(orderID, orderDate, sendAddress, packingNumber, 
                                        courier, shippingFee, listDP, promoUsed);
                pesananList.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pesananList;
    }

    // mengembalikan promo yang digunakan sebuah Pesanan
    public Promo S_grabPsPromo(String IorderID){
        String query = "CALL grabPsPromo('" + IorderID + "')";
        try{
            Statement s = koneksi.createStatement();
            ResultSet rs = s.executeQuery(query);

            if (!rs.next()) {
                return null;
            }

            return new Promo(
                rs.getString("promoCode"),
                rs.getString("description"),
                rs.getDate("startDate").toLocalDate(),
                rs.getDate("endDate").toLocalDate(),
                rs.getDouble("discountPct"),
                rs.getInt("quota"),
                rs.getDouble("minPurchase")
            );
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // mengembalikan listDetailPesanan dari Pesanan milik sebuah Member
    public ArrayList<DetailPesanan> S_grabPsDP(String IorderID){
        ArrayList<DetailPesanan> listDP = new ArrayList<>();
        String query = "CALL grabPsDP('" + IorderID + "')";
        try{
            Statement s = koneksi.createStatement();
            ResultSet rs = s.executeQuery(query);
            while(rs.next()){
                int quantity = rs.getInt("quantity");

                String productID = rs.getString("productID");
                String namaProduct = rs.getString("namaProduct");
                String brand = rs.getString("brand");
                double size = rs.getDouble("size");
                int stok = rs.getInt("stok");
                double harga = rs.getDouble("harga");
                String BPOMcode = rs.getString("BPOMcode");
                String ingredients = rs.getString("ingredients");
                String deskripsi = rs.getString("deskripsi");
                String caraPakai = rs.getString("caraPakai");

                Product product = null;
                DetailPesanan dp = null;
                ArrayList<Review> rvList = new ArrayList<>();
                try{
                    product = new Product(productID, namaProduct, brand, size, stok, harga, 
                                          BPOMcode, ingredients, deskripsi, caraPakai);
                    rvList = S_grabRvPrList(productID);
                    product.setReviewList(rvList);
                    dp = new DetailPesanan(quantity, product);
                } catch(Exception a){
                    System.out.println(a.getMessage());
                }

                listDP.add(dp);                
            } 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listDP;
    }

    // mengembalikan reviewList dari sebuah Product
    public ArrayList<Review> S_grabRvPrList(String productID){
        ArrayList<Review> reviewList = new ArrayList<>();
        String query = "CALL grabRvPrList('" + productID + "')";
        try{
            Statement s = koneksi.createStatement();
            ResultSet rs = s.executeQuery(query);

            while(rs.next()){
                String reviewID = rs.getString("reviewID");
                LocalDate tglReview = rs.getDate("tglReview").toLocalDate();
                String review = rs.getString("review");
                double rating = rs.getDouble("rating");

                Review inp = null;

                try{
                    inp = new Review(reviewID, tglReview, review, rating);
                } catch (Exception a){
                    System.out.println(a.getMessage());
                }

                reviewList.add(inp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return reviewList;
    }

    // mengembalikan semua Product
    public ArrayList<Product> S_grabAllProd(){
        ArrayList<Product> allP = new ArrayList<>();
        String query = "CALL grabAllProd()";
        try{
            Statement s = koneksi.createStatement();
            ResultSet rs = s.executeQuery(query);
            while(rs.next()){

                String productID = rs.getString("productID");
                String namaProduct = rs.getString("namaProduct");
                String brand = rs.getString("brand");
                double size = rs.getDouble("size");
                int stok = rs.getInt("stok");
                double harga = rs.getDouble("harga");
                String BPOMcode = rs.getString("BPOMcode");
                String ingredients = rs.getString("ingredients");
                String deskripsi = rs.getString("deskripsi");
                String caraPakai = rs.getString("caraPakai");

                Product product = null;
                ArrayList<Review> rvList = new ArrayList<>();
                try{
                    product = new Product(productID, namaProduct, brand, size, stok, harga, 
                                          BPOMcode, ingredients, deskripsi, caraPakai);
                    rvList = S_grabRvPrList(productID);
                    product.setReviewList(rvList);
                } catch(Exception a){
                    System.out.println(a.getMessage());
                }

                allP.add(product);                
            } 

        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return allP;
    }

    // Menambahkan produk baru ke database
    public void S_addProduct(Product p) {
        String query = "CALL addProduct(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = koneksi.prepareStatement(query);
            ps.setString(1, p.getNamaProduct());
            ps.setString(2, p.getBrand());
            ps.setDouble(3, p.getSize());
            ps.setInt   (4, p.getStok());
            ps.setDouble(5, p.getHarga());
            ps.setString(6, p.getBPOMcode());
            ps.setString(7, p.getIngredients());
            ps.setString(8, p.getDeskripsi());
            ps.setString(9, p.getCaraPakai());
            ps.executeUpdate();
            System.out.println("Produk berhasil ditambahkan!");
        } catch (SQLException e) {
            System.out.println("Gagal tambah produk: " + e.getMessage());
        }
    }

    // Mengupdate produk yang ada di database
    public void S_updateProduct(Product p) {
        String query = "CALL updateProduct(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = koneksi.prepareStatement(query);
            ps.setString(1, p.getProductID());
            ps.setString(2, p.getNamaProduct());
            ps.setString(3, p.getBrand());
            ps.setDouble(4, p.getSize());
            ps.setInt   (5, p.getStok());
            ps.setDouble(6, p.getHarga());
            ps.setString(7, p.getIngredients());
            ps.setString(8, p.getDeskripsi());
            ps.setString(9, p.getCaraPakai());
            ps.executeUpdate();
            System.out.println("Produk berhasil diupdate!");
        } catch (SQLException e) {
            System.out.println("Gagal update produk: " + e.getMessage());
        }
    }

    // Mengambil semua promo
    public ArrayList<Promo> S_grabAllPromo(){
        ArrayList<Promo> allP = new ArrayList<>();
        String query = "CALL grabAllPromo()";
        try{
            Statement s = koneksi.createStatement();
            ResultSet rs = s.executeQuery(query);
            while(rs.next()){
                Promo promo = new Promo(
                    rs.getString("promoCode"),
                    rs.getString("description"),
                    rs.getDate("startDate").toLocalDate(),
                    rs.getDate("endDate").toLocalDate(),
                    rs.getDouble("discountPct"),
                    rs.getInt("quota"),
                    rs.getDouble("minPurchase")
                );
                allP.add(promo);
            }
        } catch(SQLException e){
            System.out.println("Gagal ambil promo: " + e.getMessage());
        }
        return allP;
    }

    // Menambahkan promo baru
    public void S_addPromo(Promo p){
        String query = "CALL addPromo(?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement ps = koneksi.prepareStatement(query);
            ps.setString(1, p.getDescription());
            ps.setDate  (2, java.sql.Date.valueOf(p.getStartDate()));
            ps.setDate  (3, java.sql.Date.valueOf(p.getEndDate()));
            ps.setDouble(4, p.getDiscountPct());
            ps.setInt   (5, p.getQuota());
            ps.setDouble(6, p.getMinPurchase());
            ps.executeUpdate();
            System.out.println("Promo berhasil ditambahkan!");
        } catch(SQLException e){
            System.out.println("Gagal tambah promo: " + e.getMessage());
        }
    }

    // Mengupdate promo
    public void S_updatePromo(Promo p){
        String query = "CALL updatePromo(?, ?, ?)";
        try{
            PreparedStatement ps = koneksi.prepareStatement(query);
            ps.setString(1, p.getPromoCode());
            ps.setString(2, p.getDescription());
            ps.setDouble(3, p.getDiscountPct());
            ps.executeUpdate();
            System.out.println("Promo berhasil diupdate!");
        } catch(SQLException e){
            System.out.println("Gagal update promo: " + e.getMessage());
        }
    }

    // Tambah pesanan baru, return orderID yang dibuat
    public String S_addPesanan(String memberID, String sendAddress, String packingNumber, 
                               String courier, double shippingFee, String promoCode){
        String query = "CALL addPesanan(?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement ps = koneksi.prepareStatement(query);
            ps.setString(1, memberID);
            ps.setString(2, sendAddress);
            ps.setString(3, packingNumber);
            ps.setString(4, courier);
            ps.setDouble(5, shippingFee);
            ps.setString(6, promoCode); // bisa null
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString(1); // return orderID
            }
        } catch(SQLException e){
            System.out.println("Gagal tambah pesanan: " + e.getMessage());
        }
        return null;
    }

    // Tambah detail pesanan
    public void S_addDetailPesanan(String orderID, int quantity, String productID){
        String query = "CALL addDetailPesanan(?, ?, ?)";
        try{
            PreparedStatement ps = koneksi.prepareStatement(query);
            ps.setString(1, orderID);
            ps.setInt   (2, quantity);
            ps.setString(3, productID);
            ps.executeUpdate();
        } catch(SQLException e){
            System.out.println("Gagal tambah detail pesanan: " + e.getMessage());
        }
    }

    // Tambah review
    public void S_addReview(String memberID, String productID, String review, double rating){
        String query = "CALL addReview(?, ?, ?, ?)";
        try{
            PreparedStatement ps = koneksi.prepareStatement(query);
            ps.setString(1, memberID);
            ps.setString(2, productID);
            ps.setString(3, review);
            ps.setDouble(4, rating);
            ps.executeUpdate();
            System.out.println("Review berhasil ditambahkan!");
        } catch(SQLException e){
            System.out.println("Gagal tambah review: " + e.getMessage());
        }
    }
}