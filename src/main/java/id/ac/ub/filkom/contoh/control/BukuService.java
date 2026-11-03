package id.ac.ub.filkom.contoh.control;

import id.ac.ub.filkom.contoh.entity.Buku;
import id.ac.ub.filkom.contoh.entity.BukuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/** Control: mengatur logika bisnis dan aturan aplikasi buku. */
@Service
public class BukuService {

    private final BukuRepository bukuRepository;

    // Dependency injection lewat constructor
    public BukuService(BukuRepository bukuRepository) {
        this.bukuRepository = bukuRepository;
    }

    public List<Buku> cariBuku(String kata) {
        if (kata == null || kata.isBlank()) {
            return bukuRepository.findAll();
        }
        return bukuRepository.findByJudulContainingIgnoreCase(kata.trim());
    }

    public Buku tambahBuku(String idBuku, String judul, int stok) {
        if (stok < 0) {
            throw new IllegalArgumentException("Stok tidak boleh negatif");
        }
        return bukuRepository.save(new Buku(idBuku, judul, stok));
    }
}
