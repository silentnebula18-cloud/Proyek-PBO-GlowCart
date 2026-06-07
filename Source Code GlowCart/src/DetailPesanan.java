/**
 * Nama File: DetailPesanan.java
 * Deskripsi: atribut dan metode dalam kelas DetailPesanan
 * Tanggal Buat: 23 Mei 2026
 * Kelas: C
 * Anggota: Annis Fakhiroh Akbar (24060124130110)   
            Binar Ridha Wiritanaya (24060124140143)  
            Christianna Olivia J. M. (24060124140168)
            Dian Aulya Dewiyani (24060124130059)
 */

public class DetailPesanan{
    /*********************ATRIBUT*********************/
    private int quantity;
    private Product product;

    /*********************METHOD*********************/
    /*------------------KONSTRUKTOR-----------------*/
    // konstruktor tanpa parameter
    public DetailPesanan(){
    }

    // konstruktor dengan parameter 
    public DetailPesanan(int quantity, Product product) throws Exception{
        if(quantity > 0 && quantity <= product.getStok()){
            this.quantity = quantity;
            this.product = product;
            int x = product.getStok() - quantity;
            this.product.setStok(x);
        }
        else{
            throw new Exception("Stok tidak mencukupi");
        }
    }

    /*------------------SELEKTOR-------------------*/
    // mengembalikan nilai atribut quantity
    public int getQuantity(){
        return this.quantity;
    }

    // mengembalikan nilai atribut product
    public Product getProduct(){
        return this.product;
    }
    
    /*------------------LAINNYA-------------------*/
    // print nilai quantity dan product
    public void printDetail(){
        System.out.println("Quantity   : " + this.quantity);
        this.product.printInfo(); 
    }
} // end of Class DetailPesanan