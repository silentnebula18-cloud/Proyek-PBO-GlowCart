/**
 * Nama File: MySQLUtil.java
 * Deskripsi: atribut dan metode dalam MySQLUtil. 
 *            Kelas ini digunakan untuk membuat koneksi dengan database
 * Tanggal Buat: 06 Mei 2026
 * Kelas: C
 * Anggota: Annis Fakhiroh Akbar (24060124130110)   
            Binar Ridha Wiritanaya (24060124140143)  
            Christianna Olivia J. M. (24060124140168)
            Dian Aulya Dewiyani (24060124130059)
 */


import java.sql.*;

public class MySQLUtil {
    private static Connection koneksi;
    
    public static Connection getConnection(){

        if(koneksi == null){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Sesuaikan host, port, nama db
                String url = "jdbc:mysql://localhost:3306/glowcartdb";
                // sesuaikan username dan password
                String user = "root";
                String password = "";
                koneksi = DriverManager.getConnection(url, user, password);
            }catch(ClassNotFoundException cne){
                System.out.println("Gagal load driver : " + cne.getMessage());
            }catch(SQLException sqle){
                System.out.println("Gagal koneksi : " + sqle.getMessage());
            }
        }
        return koneksi;
    }
    
    public static void closeConnection(){
        if(koneksi != null){
            try{
                koneksi.close();
                koneksi = null;
            } catch(SQLException e){
                System.out.println("Gagal menutup koneksi: " + e.getMessage());
            }
            
        }
    }
}
