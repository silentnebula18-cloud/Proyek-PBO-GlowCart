/*Nama File: AppService.java */
/*Deskripsi: atribut dan metode dalam interface AppService */
/*Tanggal Buat: 23 Mei 2026 */
/*Kelas: C */
/*Anggota: Annis Fakhiroh Akbar (24060124130110)     */
/*         Binar Ridha Wiritanaya (24060124140143)   */
/*         Christianna Olivia J. M. (24060124140168) */
/*         Dian Aulya Dewiyani (24060124130059)      */

public interface AppService {
    //menampilkan semua atribut yang dimiliki Pesanan, Product, atau DetailPesanan
    public void printInfo();
    
    //mengembalikan True jika ditemukan sesuai kata kunci key
    public boolean search(String key);
} //end interface AppService