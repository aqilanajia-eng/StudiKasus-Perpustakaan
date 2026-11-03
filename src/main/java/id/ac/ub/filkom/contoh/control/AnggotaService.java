package id.ac.ub.filkom.contoh.control;

import id.ac.ub.filkom.contoh.entity.Anggota;
import id.ac.ub.filkom.contoh.entity.AnggotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/** Control: mengatur logika untuk fitur anggota. */
@Service
public class AnggotaService {

    private final AnggotaRepository anggotaRepository;

    public AnggotaService(AnggotaRepository anggotaRepository) {
        this.anggotaRepository = anggotaRepository;
    }

    public List<Anggota> lihatSemuaAnggota() {
        return anggotaRepository.findAll();
    }

    // Menambahkan anggota baru
    public void tambahAnggota(String idAnggota, String nama, int batasPinjam) {
        Anggota anggota = new Anggota(idAnggota, nama, batasPinjam);
        anggotaRepository.save(anggota);
    }
}