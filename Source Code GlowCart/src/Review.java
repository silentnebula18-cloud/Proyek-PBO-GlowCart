/**
 * Nama File: Review.java
 * Deskripsi: atribut dan metode dalam kelas Review
 * Tanggal Buat: 23 Mei 2026
 * Kelas: C
 * Anggota: Annis Fakhiroh Akbar (24060124130110)   
            Binar Ridha Wiritanaya (24060124140143)  
            Christianna Olivia J. M. (24060124140168)
            Dian Aulya Dewiyani (24060124130059)
 */

import java.time.LocalDate;

public class Review {
    /*************ATRIBUT********************/
    private String reviewID;
    private LocalDate tglReview;
    private String review;
    private double rating;

    /*****************METHOD********************/
    //============Konstruktor=============
    //Konstruktor tanpa Parameter
    public Review(){

    }

    //Konstruktor dengan Parameter
    public Review(String reviewID, LocalDate tglReview, String review, double rating) throws Exception{
        //validasi rating harus antara 0 sampai 5
        if (rating < 0 || rating > 5){
            throw new Exception("Gagal membuat review: Rating harus antara 0 s/d 5");
        }
        this.reviewID = reviewID;
        this.tglReview = tglReview;
        this.review = review;
        this.rating = rating;
    }

    //================Selektor==============
    //Mengembalikan nilai atribut reviewID
    public String getReviewID(){
        return reviewID;
    }

    //Mengembalikan nilai atribut tglReview
    public LocalDate gettglReview(){
        return tglReview;
    }

    //Mengembalikan isi komentar review
    public String getReview(){
        return review;
    }

    //Mengembalikan nilai rating
    public double getRating(){

        return rating;
    }

    //================Lainnya==============
    //Menampilkan informasi review
    public void cetakReview(){
        System.out.println("ID Review : " + reviewID);
        System.out.println("Tanggal   : " + tglReview);
        System.out.println("Komentar  : " + review);
        System.out.println("Rating    : " + rating);
    }
}//end of class Review