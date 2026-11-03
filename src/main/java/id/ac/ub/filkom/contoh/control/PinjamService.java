
package id.ac.ub.filkom.contoh.control;

import id.ac.ub.filkom.contoh.entity.Anggota;
import id.ac.ub.filkom.contoh.entity.Buku;
import id.ac.ub.filkom.contoh.entity.TransaksiPinjam;
import id.ac.ub.filkom.contoh.entity.AnggotaRepository;
import id.ac.ub.filkom.contoh.entity.BukuRepository;
import id.ac.ub.filkom.contoh.entity.TransaksiPinjamRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PinjamService {

    private final AnggotaRepository anggotaRepository;
    private final BukuRepository bukuRepository;
    private final TransaksiPinjamRepository transaksiRepository;

    public PinjamService(
            AnggotaRepository anggotaRepository,
            BukuRepository bukuRepository,
            TransaksiPinjamRepository transaksiRepository) {
        this.anggotaRepository = anggotaRepository;
        this.bukuRepository = bukuRepository;
        this.transaksiRepository = transaksiRepository;
    }

    // Mengambil daftar buku
    public List<Buku> lihatSemuaBuku() {
        return bukuRepository.findAll();
    }

    // Mengambil daftar anggota
    public List<Anggota> lihatSemuaAnggota() {
        return anggotaRepository.findAll();
    }

    // Proses peminjaman buku
    @Transactional
    public Bukti pinjamBuku(String idAnggota, String idBuku) {

        Anggota anggota = anggotaRepository.findById(idAnggota)
                .orElseThrow(() ->
                        new PinjamGagalException("Anggota tidak ditemukan"));

        Buku buku = bukuRepository.findById(idBuku)
                .orElseThrow(() ->
                        new PinjamGagalException("Buku tidak ditemukan"));

        if (!anggota.cekBatasPinjam()) {
            throw new PinjamGagalException(
                    "Batas peminjaman anggota sudah tercapai");
        }

        if (!buku.cekStok()) {
            throw new PinjamGagalException("Stok buku habis");
        }

        TransaksiPinjam transaksi =
                TransaksiPinjam.buat(anggota, buku);

        transaksiRepository.save(transaksi);

        buku.kurangiStok();
        bukuRepository.save(buku);

        return Bukti.dari(transaksi);
    }
}
