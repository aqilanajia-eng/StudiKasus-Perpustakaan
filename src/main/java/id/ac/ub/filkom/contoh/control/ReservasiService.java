
package id.ac.ub.filkom.contoh.control;

import id.ac.ub.filkom.contoh.entity.Anggota;
import id.ac.ub.filkom.contoh.entity.Buku;
import id.ac.ub.filkom.contoh.entity.Reservasi;
import id.ac.ub.filkom.contoh.entity.AnggotaRepository;
import id.ac.ub.filkom.contoh.entity.BukuRepository;
import id.ac.ub.filkom.contoh.entity.ReservasiRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservasiService {

    private final AnggotaRepository anggotaRepository;
    private final BukuRepository bukuRepository;
    private final ReservasiRepository reservasiRepository;

    public ReservasiService(
            AnggotaRepository anggotaRepository,
            BukuRepository bukuRepository,
            ReservasiRepository reservasiRepository) {
        this.anggotaRepository = anggotaRepository;
        this.bukuRepository = bukuRepository;
        this.reservasiRepository = reservasiRepository;
    }

    @Transactional
    public Reservasi buatReservasi(String idAnggota, String idBuku) {

        Anggota anggota = anggotaRepository.findById(idAnggota)
                .orElseThrow(() ->
                        new PinjamGagalException("Anggota tidak ditemukan"));

        Buku buku = bukuRepository.findById(idBuku)
                .orElseThrow(() ->
                        new PinjamGagalException("Buku tidak ditemukan"));

        if (buku.cekStok()) {
            throw new PinjamGagalException(
                    "Buku masih tersedia. Silakan lakukan peminjaman.");
        }

        Reservasi reservasi = new Reservasi(anggota, buku);

        return reservasiRepository.save(reservasi);
    }
}
