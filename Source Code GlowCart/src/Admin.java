 /**
 * Nama File: Admin.java
 * Deskripsi: atribut dan metode dalam kelas Admin
 * Tanggal Buat: 23 Mei 2026
 * Kelas: C
 * Anggota: Annis Fakhiroh Akbar (24060124130110)   
            Binar Ridha Wiritanaya (24060124140143)  
            Christianna Olivia J. M. (24060124140168)
            Dian Aulya Dewiyani (24060124130059)
 */

public class Admin extends User{
    // == ATRIBUT ==
    private String adminID, status;
    private static int counterAdmin;
    private static MySQLService service = new MySQLService();

    // == KONSTRUKTOR ==
    // Konstruktor tanpa parameter
    public Admin(){
        counterAdmin++;
    }

    // Konstruktor dengan input parameter
    public Admin(String UserID, String email, String username, String password, String noHP, String adminID, String status){
        super(UserID, email, username, password, noHP);
        this.adminID = adminID;
        this.status = status;
        counterAdmin++;
    }

    // == SELEKTOR ==
    // Selektor untuk mengambil adminID
    public String getAdminID() {
        return adminID;
    }

    // Selektor untuk mengambil status
    public String getStatus() {
        return status;
    }

    // Selektor untuk mengambil counterAdmin
    public static int getCounterAdmin() {
        return counterAdmin;
    }

    // == MUTATOR ==
    // Mutator untuk mengubah status
    public void setStatus(String status) {
        this.status = status;
    }

    // == METHOD ==
    // Method untuk menambahkan data produk baru
    public void addProduct(Product P){
        service.S_addProduct(P);
    }

    // Method untuk menambahkan data promo baru
    public void addPromo(Promo P){
        service.S_addPromo(P);
    }

    // Method untuk mengubah deskripsi promo 
    public void setPromo(Promo P1, String Desc, double discPct) {
        P1.setDescription(Desc);
        P1.setDiscount(discPct);
    }

    // Method untuk mengubah deskripsi produk 
    public void setProduct(Product P1, Product P2) {
        P1.setNamaProduct(P2.getNamaProduct());
        P1.setBrand(P2.getBrand());

        try{
            P1.setSize(P2.getSize());
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try{
            P1.setStok(P2.getStok());
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
        try{
            P1.setHarga(P2.getHarga());
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        P1.setIngredients(P2.getIngredients());
        P1.setDeskripsi(P2.getDeskripsi());
        P1.setCaraPakai(P2.getCaraPakai());
    }

    // Method untuk mengeluarkan role 
    @Override
    public void printRole() {
        System.out.println("Admin");
    }
}