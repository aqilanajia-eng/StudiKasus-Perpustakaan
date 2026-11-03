
package id.ac.ub.filkom.contoh.control;

import id.ac.ub.filkom.contoh.entity.TransaksiPinjam;
import java.time.LocalDate;

public class Bukti {

    private String idTransaksi;
    private String namaAnggota;
    private String judulBuku;
    private LocalDate tanggalPinjam;
    private String status;

    public Bukti(String idTransaksi, String namaAnggota,
                 String judulBuku, LocalDate tanggalPinjam,
                 String status) {
        this.idTransaksi = idTransaksi;
        this.namaAnggota = namaAnggota;
        this.judulBuku = judulBuku;
        this.tanggalPinjam = tanggalPinjam;
        this.status = status;
    }

    public static Bukti dari(TransaksiPinjam transaksi) {
        return new Bukti(
                transaksi.getIdTransaksi(),
                transaksi.getAnggota().getNama(),
                transaksi.getBuku().getJudul(),
                transaksi.getTanggalPinjam(),
                transaksi.getStatus()
        );
    }

    public String getIdTransaksi() { return idTransaksi; }
    public String getNamaAnggota() { return namaAnggota; }
    public String getJudulBuku() { return judulBuku; }
    public LocalDate getTanggalPinjam() { return tanggalPinjam; }
    public String getStatus() { return status; }
}