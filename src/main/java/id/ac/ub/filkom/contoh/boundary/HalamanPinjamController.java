
package id.ac.ub.filkom.contoh.boundary;

import id.ac.ub.filkom.contoh.control.Bukti;
import id.ac.ub.filkom.contoh.control.PinjamService;
import id.ac.ub.filkom.contoh.control.PinjamGagalException;
import id.ac.ub.filkom.contoh.control.ReservasiService;
import id.ac.ub.filkom.contoh.entity.Reservasi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HalamanPinjamController {

    private final PinjamService pinjamService;
    private final ReservasiService reservasiService;

    public HalamanPinjamController(
            PinjamService pinjamService,
            ReservasiService reservasiService) {
        this.pinjamService = pinjamService;
        this.reservasiService = reservasiService;
    }

    // Menampilkan halaman peminjaman
    @GetMapping("/pinjam")
    public String halamanPinjam(Model model) {
        model.addAttribute(
                "daftarBuku",
                pinjamService.lihatSemuaBuku());

        model.addAttribute(
                "daftarAnggota",
                pinjamService.lihatSemuaAnggota());

        return "pinjam";
    }

    // Memproses peminjaman buku
    @PostMapping("/pinjam")
    public String pilihBuku(
            @RequestParam String idAnggota,
            @RequestParam String idBuku,
            Model model) {

        try {
            Bukti bukti =
                    pinjamService.pinjamBuku(idAnggota, idBuku);

            model.addAttribute("bukti", bukti);
            return "bukti";

        } catch (PinjamGagalException e) {

            model.addAttribute("pesan", e.getMessage());

            if ("Stok buku habis".equals(e.getMessage())) {
                model.addAttribute("bisaReservasi", true);
                model.addAttribute("idBukuReservasi", idBuku);
                model.addAttribute("idAnggotaReservasi", idAnggota);
            }

            model.addAttribute(
                    "daftarBuku",
                    pinjamService.lihatSemuaBuku());

            model.addAttribute(
                    "daftarAnggota",
                    pinjamService.lihatSemuaAnggota());

            return "pinjam";
        }
    }

    // Memproses reservasi buku
    @PostMapping("/reservasi")
    public String buatReservasi(
            @RequestParam String idBuku,
            @RequestParam String idAnggota,
            Model model) {

        try {
            Reservasi reservasi =
                    reservasiService.buatReservasi(idAnggota, idBuku);

            model.addAttribute(
                    "pesan",
                    "Reservasi berhasil dibuat. Status: "
                            + reservasi.getStatus());

        } catch (PinjamGagalException e) {

            model.addAttribute("pesan", e.getMessage());
        }

        model.addAttribute(
                "daftarBuku",
                pinjamService.lihatSemuaBuku());

        model.addAttribute(
                "daftarAnggota",
                pinjamService.lihatSemuaAnggota());

        return "pinjam";
    }
}