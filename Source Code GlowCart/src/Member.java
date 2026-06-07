/**
 * Nama File: Member.java
 * Deskripsi: atribut dan metode dalam kelas Member
 * Tanggal Buat: 23 Mei 2026
 * Kelas: C
 * Anggota: Annis Fakhiroh Akbar (24060124130110)   
            Binar Ridha Wiritanaya (24060124140143)  
            Christianna Olivia J. M. (24060124140168)
            Dian Aulya Dewiyani (24060124130059)
 */

import java.time.LocalDate;
import java.util.ArrayList;

public class Member extends User {
    // == ATRIBUT ==
    private String memberID;
    private int point;
    private LocalDate tglJoin;
    private ArrayList<Review> reviewList;
    private ArrayList<Pesanan> pesananList;
    private static int counterMember;

    // == KONSTRUKTOR ==
    // Konstruktor tanpa parameter
    public Member(){
        this.reviewList = new ArrayList<>();
        this.pesananList = new ArrayList<>();
        counterMember++;
    }

    // Konstruktor dengan input parameter
    public Member(String userID, String email, String username, String password,
                  String noHP, String memberID, int point, LocalDate tglJoin) {
        super(userID, email, username, password, noHP);
        this.memberID = memberID;
        this.point = point;
        this.tglJoin = tglJoin;
        this.reviewList = new ArrayList<>();
        this.pesananList = new ArrayList<>();
        counterMember++;
    }

    // == SELEKTOR ==
    // Selektor untuk mengambil memberID
    public String getMemberID() {
        return memberID;
    }

    // Selektor untuk mengambil point
    public int getPoint() {
        return point;
    }

    // Selektor untuk mengambil tglJoin
    public LocalDate getTglJoin() {
        return tglJoin;
    }

    // Selektor untuk mengambil list Review
    public ArrayList<Review> getReviewList() {
        return reviewList;
    }

    // Selektor untuk mengambil list Pesanan
    public ArrayList<Pesanan> getPesananList() {
        return pesananList;
    }

    // Selektor untuk mengambil counterMember
    public static int getCounterMember() {
        return counterMember;
    }

    // == METHOD ==
    // Method untuk menambahkan list Review
    public void setReviewList(ArrayList<Review> reviewList) {
        this.reviewList = reviewList;
    }

    // Method untuk menambahkan list Pesanan
    public void setPesananList(ArrayList<Pesanan> pesananList) {
        this.pesananList = pesananList;
    }

    // Method untuk menambahkan Review pada list Review
    public void addReview(Review R){
        reviewList.add(R);
    }

    // Method untuk menambahkan Pesanan pada list Pesanan
    public void addPesanan(Pesanan R){
        pesananList.add(R);
    }

    // Method untuk menghapus Pesanan pada list Pesanan
    public void removePesanan(String orderID) {
    for (int i = 0; i < pesananList.size(); i++) {
        if (pesananList.get(i).getOrderID().equals(orderID)) {
            pesananList.remove(i);
            break;
            }
        }
    }

    // Method untuk menghapus Review pada list Review
    public void removeReview(String reviewID) {
    for (int i = 0; i < reviewList.size(); i++) {
        if (reviewList.get(i).getReviewID().equals(reviewID)) {
            reviewList.remove(i);
            break;
            }
        }
    }

    // Method untuk mendapatkan Level Membership berdasarkan Point
    public void getLevelMembership(){
        if (getPoint() >= 0 && getPoint() <= 100){
            System.out.println("Pemula");
        }
        else if (getPoint() > 100 && getPoint() <= 200){
            System.out.println("Biasa");    
        }
        else if (getPoint() > 200){
            System.out.println("Master");       
        }
        else{
            System.out.println("Belum Terdaftar");
        }
    }

    // Method untuk mengeluarkan role
    @Override
    public void printRole() {
        System.out.println("Member");
    }
}