
package id.ac.ub.filkom.contoh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

/** Entity: satu objek Anggota = satu baris di tabel "anggota". */
@Entity
@Table(name = "anggota")
public class Anggota {

    @Id
    @Column(name = "id_anggota", length = 10)
    private String idAnggota;

    @Column(name = "nama", length = 100, nullable = false)
    private String nama;

    @Column(name = "batas_pinjam")
    private int batasPinjam;

    @OneToMany(mappedBy = "anggota")
    private List<TransaksiPinjam> daftarPinjam = new ArrayList<>();

    protected Anggota() { }

    public Anggota(String idAnggota, String nama, int batasPinjam) {
        this.idAnggota = idAnggota;
        this.nama = nama;
        this.batasPinjam = batasPinjam;
    }

    public String getIdAnggota() { return idAnggota; }
    public String getNama() { return nama; }
    public int getBatasPinjam() { return batasPinjam; }

    // Mengecek apakah anggota masih boleh meminjam buku
    public boolean cekBatasPinjam() {
        long jumlahPinjamanAktif = daftarPinjam.stream()
                .filter(TransaksiPinjam::isDipinjam)
                .count();

        return jumlahPinjamanAktif < batasPinjam;
    }

    // Mencatat transaksi peminjaman anggota
    public void catatPinjaman(TransaksiPinjam transaksi) {
        daftarPinjam.add(transaksi);
    }
}