import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Booking {
    Scanner scanner = new Scanner(System.in);
    int rowKursi ;
    int nomorKursi;
    String namaCustomer;
    boolean[][] kursi = new boolean[5][6];
    ArrayList<String> logBooking = new ArrayList<>();

    public void Menu(){
        summary();
        System.out.println("Pilih menu yang akan diambil");
        System.out.println("1. Book kursi");
        System.out.println("2. Lihat semua status kursi");
        System.out.println("3. Lihat history log booked");
        System.out.print("Masukkan Pilihan : ");
        String pilihan = scanner.nextLine();
        switch (pilihan){
            case "1":
                bookKursi();
                break;
            case "2":
                statusKursi();
                break;
            case "3":
                historyLogBooking();
                break;
            default:
                System.out.println("Pilihan tidak tersedia, menu hanya tersedia dari nomor 1-3 \n");
                Menu();
                break;
        }
    }
    public void summary(){
        int kursiKosong=0;
        int kursiBooking=0;
        for(int i=0;i <5;i++){
            for(int j=0;j<6;j++){
                if(kursi[i][j] == false){
                    kursiKosong +=1;
                }else{
                    kursiBooking +=1;
                }
            }
        }
        System.out.println("Terdapat " + kursiKosong + " kursi yang tersedia dan " +
                kursiBooking + " kursi yang sudah dibooking");
    }
    public void bookKursi(){
        pilihRow();
        pilihKursi();
        if(kursi[rowKursi-1][nomorKursi-1]==false){ //arrayKursi[0][0] ==false
            System.out.print("Masukkan nama anda : ");
            namaCustomer = scanner.nextLine();

            kursi[rowKursi-1][nomorKursi-1]=true;
            System.out.println("Booking Berhasil");
            addHistoryLogBooking(namaCustomer,"berhasil",rowKursi-1,nomorKursi-1);

            Menu();

        }else{
            System.out.println("Maaf Kursi Sudah di Booking");
            addHistoryLogBooking(namaCustomer,"berhasil",rowKursi-1,nomorKursi-1);

            Menu();
        }
    }
    private void pilihRow(){
        while (true) {
            try {
                System.out.print("Pilih row : ");
                int pilihBaris = Integer.parseInt(scanner.nextLine());

                if (pilihBaris > 0 && pilihBaris <= 5) {
                    rowKursi = pilihBaris;
                    break;
                } else {
                    System.out.println("row yang tersedia hanya row 1 - 5");
                }
            }catch (NumberFormatException nFe){
                System.out.println("Input Anda Salah, Coba Lagi");
            }
        }
    }
    private void pilihKursi(){
        while (true) {
            try {
                System.out.print("Pilih nomor kursi: ");
                int pilihKursi = Integer.parseInt(scanner.nextLine());

                if (pilihKursi > 0 && pilihKursi <= 6) {
                    nomorKursi = pilihKursi;
                    break;

                } else {
                    System.out.println("Kursi yang tersedia hanya kursi nomor 1 - 6");
                }

            }catch (NumberFormatException nFe){
                System.out.println("Input Anda Salah, Coba Lagi");
            }
        }
    }

    public void statusKursi(){
        for(int i=0;i <5;i++){
            for(int j=0;j<6;j++){
                if(kursi[i][j] == false){
                    System.out.println("Row "+(i+1) +", Kursi " +(j+1) + ": Vacant"  );
                }else{
                    System.out.println("Row "+(i+1) +", Kursi " +(j+1) + ": Booked"  );
                }
            }
        }
        Menu();
    }
    public void historyLogBooking(){
        logBooking.forEach(isi-> System.out.println(isi));
    }
    public void addHistoryLogBooking(String nama,String succesFail,int row, int nokursi){
        LocalDate date = LocalDate.now();
        logBooking.add("Pada tanggal "+date+": Row "+ (row+1) + ", Kursi " +(nokursi+1) +" "+ succesFail + " " +
                "di book oleh "+nama);
    }
}