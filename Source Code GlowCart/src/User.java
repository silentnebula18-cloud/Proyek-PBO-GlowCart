/**
 * Nama File: User.java
 * Deskripsi: atribut dan metode dalam kelas User
 * Tanggal Buat: 23 Mei 2026
 * Kelas: C
 * Anggota: Annis Fakhiroh Akbar (24060124130110)   
            Binar Ridha Wiritanaya (24060124140143)  
            Christianna Olivia J. M. (24060124140168)
            Dian Aulya Dewiyani (24060124130059)
 */

public abstract class User {
    // == ATRIBUT ==
    protected String UserID, email, username, password, noHP;
    protected static int counterUser;

    // == KONSTRUKTOR ==
    // Konstruktor tanpa parameter
    public User(){
        counterUser++;
    }

    // Konstruktor dengan input parameter
    public User(String UserID, String email, String username, String password, String noHP){
        this.UserID = UserID;
        this.email = email;
        this.username = username;
        this.password = password;
        this.noHP = noHP;
        counterUser++;
    }

    // == SELEKTOR ==
    // Selektor untuk mengambil userID
    public String getUserID() {
        return UserID;
    }

    // Selektor untuk mengambil email
    public String getEmail() {
        return email;
    }

    // Selektor untuk mengambil username
    public String getUsername() {
        return username;
    }

    // Selektor untuk mengambil password
    public String getPassword() {
        return password;
    }

    // Selektor untuk mengambil noHP
    public String getNoHP() {
        return noHP;
    }

    // Selektor untuk mengambil counterUser
    public static int getCounterUser() {
        return counterUser;
    }

    // == METHOD ==
    // Method abstract untuk print role dari user
    public abstract void printRole();

    // Method untuk login
    public boolean login(String us, String pw){
        return this.username.equals(us) && this.password.equals(pw);
    }
} 