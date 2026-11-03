
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
@Table(name = "reservasi")
public class Reservasi {

    @Id
    @Column(name = "id_reservasi", length = 36)
    private String idReservasi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_buku", nullable = false)
    private Buku buku;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_anggota", nullable = false)
    private Anggota anggota;

    @Column(name = "tanggal_reservasi", nullable = false)
    private LocalDate tanggalReservasi;

    @Column(name = "status", length = 20, nullable = false)
    private String status;

    protected Reservasi() {
    }

    public Reservasi(Anggota anggota, Buku buku) {
        this.idReservasi = UUID.randomUUID().toString();
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalReservasi = LocalDate.now();
        this.status = "MENUNGGU";
    }

    public String getIdReservasi() {
        return idReservasi;
    }

    public Buku getBuku() {
        return buku;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public LocalDate getTanggalReservasi() {
        return tanggalReservasi;
    }

    public String getStatus() {
        return status;
    }
}