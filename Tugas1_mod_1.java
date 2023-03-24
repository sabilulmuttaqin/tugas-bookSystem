package modul1;

import java.util.Scanner;
import java.util.regex.*;

class BookSystem {
    Scanner input = new Scanner(System.in);
    private String nama;
    private String alamat;
    private String noTelp;
    private String email;
    private int[][] room =
            {
    { 0, 101, 1000000}, 
    { 1, 102, 200000}, 
    { 0, 103, 300000},
    { 0, 104, 200000},
                
    };
    private int noRoom;

 

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNotelpon() {
        return noTelp;
    }

    public void setNotelpon(String notelp) {
        this.noTelp=  notelp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNoRoom() {
        return noRoom;
    }

    public void setNoroom(int noroom) {
        this.noRoom = noroom;
    }
    public boolean init() {
        
        String namaRegex = "^[A-Za-z\\s]+$";
        Pattern namaPattern = Pattern.compile(namaRegex);
        Matcher namaMatcher = namaPattern.matcher(getNama());
        boolean namaBenar = namaMatcher.matches();

        String emailRegex = "^[\\w!#$%&amp;'+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(getEmail());
        boolean emailBenar = emailMatcher.matches();

        String noTelpRegex = "^\\+62[0-9]{9,}$";
        Pattern phonePattern = Pattern.compile(noTelpRegex);
        Matcher phoneMatcher = phonePattern.matcher(getNotelpon());
        boolean noTelponBenar = phoneMatcher.matches();

        if (!namaBenar){
            System.out.println("Nama Invalid");
        }
        if (!noTelponBenar) {
            System.out.println("Nomor Hp Invalid!\n");
        }

        if (!emailBenar) {
            System.out.println("Email Invalid!\n ");
        }
        
        if (namaBenar && emailBenar && noTelponBenar) {
            return true;
        } else {
            return false;
        }
    }

     public void displayData() {
        System.out.println("Data : ");
        System.out.println("Nama : " + getNama());
        System.out.println("Alamat : " + getAlamat());
        System.out.println("Email  :  " + getEmail());
        System.out.println("No Telpon : " + getNotelpon());
    }

    public void BookRoom() {
        do {
            System.out.println(" Room List : ");
            for (int i = 0; i < 4; i++) {

                if (room[i][0] == 0) { 
                    System.out.println(i + 1 + " No kamar : " + room[i][1] + " Rp." + room[i][2]);

                } else {
                    System.out.println(i + 1 + " No kamar : " + room[i][1] + "  " + "(Tidak tersedia)");
                }

            }
            System.out.print(" Pilih No Room : ");
            setNoroom(input.nextInt());
            if (room[getNoRoom() - 1][0] == 1) {
                System.out.println("Kamar Yang DI pilih Tidak Tersedia!");
            }
        } while (room[getNoRoom() - 1][0] == 1);

    }

    public void DoPayment(int noRoom) {
        int duit = 0;
        int kembalian;
        System.out.println("======Pembayaran : =====");
        displayData();
        System.out.println("Kamar       : " + room[noRoom - 1][1]);
        System.out.println("Harga kamar : " + room[noRoom - 1][2]);
        do {
            System.out.print("Masukkan Nilai Uang : ");
            duit =input.nextInt();
            kembalian = duit - room[noRoom-1][2];
            if (duit >= room[noRoom - 1][2]) {
                System.out.println("Pembayaran Berhasil !");
                System.out.println("Kembalian: " + kembalian);
                room[noRoom - 1][0] = 1;
                displayData();
                System.out.println("Berhasil memesan kamar nomor " + room[noRoom-1][1]);
            } else { 
                System.out.println("Uang Yang di Masukkan Kurang!");
            }
            
        } while (duit < room[noRoom - 1][2]);
    }
}
    
public class Tugas1_mod_1{
    public static void main(String[] args) {
        int ulang;
        BookSystem obj = new BookSystem();
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("~~~~~~~~~~~Riyz Hotel~~~~~~~~~~");
            do{
        System.out.println("Registrasi Data : ");
        System.out.print("Masukkan nama : ");
        obj.setNama(input.next());
        System.out.print("Masukkan Alamat : ");
        obj.setAlamat(input.next());
        System.out.print("Masukkan Email (@gmail.com) : ");
        obj.setEmail(input.next());
        System.out.print("Maskuuan No Hp (+62) : ");
        obj.setNotelpon(input.next());
       
    }while(!obj.init());

            obj.BookRoom();
            obj.DoPayment(obj.getNoRoom());
            System.out.print("Apakah Ingin Memesan kamar lagi? (1/0) : ");
            ulang = input.nextInt();

        } while (ulang == 1);
  
    }
}
