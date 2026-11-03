
package id.ac.ub.filkom.contoh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "transaksi_pinjam")
public class TransaksiPinjam {

    public static final String DIPINJAM = "DIPINJAM";

    @Id
    @Column(name = "id_transaksi", length = 36)
    private String idTransaksi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_anggota", nullable = false)
    private Anggota anggota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_buku", nullable = false)
    private Buku buku;

    @Column(name = "tanggal_pinjam", nullable = false)
    private LocalDate tanggalPinjam;

    @Column(name = "status", length = 20, nullable = false)
    private String status;

    protected TransaksiPinjam() { }

    private TransaksiPinjam(Anggota anggota, Buku buku) {
        this.idTransaksi = UUID.randomUUID().toString();
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalPinjam = LocalDate.now();
        this.status = DIPINJAM;
    }

    public static TransaksiPinjam buat(Anggota anggota, Buku buku) {
        TransaksiPinjam transaksi = new TransaksiPinjam(anggota, buku);
        anggota.catatPinjaman(transaksi);
        return transaksi;
    }

    public boolean isDipinjam() {
        return DIPINJAM.equals(status);
    }

    public String getIdTransaksi() { return idTransaksi; }
    public Anggota getAnggota() { return anggota; }
    public Buku getBuku() { return buku; }
    public LocalDate getTanggalPinjam() { return tanggalPinjam; }
    public String getStatus() { return status; }
}