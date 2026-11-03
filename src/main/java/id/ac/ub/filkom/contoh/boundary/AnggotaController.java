
package id.ac.ub.filkom.contoh.boundary;

import id.ac.ub.filkom.contoh.control.AnggotaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/** Boundary: halaman pengelolaan anggota. */
@Controller
public class AnggotaController {

    private final AnggotaService anggotaService;

    public AnggotaController(AnggotaService anggotaService) {
        this.anggotaService = anggotaService;
    }

    @GetMapping("/anggota")
    public String lihatAnggota(Model model) {
        model.addAttribute(
                "daftarAnggota",
                anggotaService.lihatSemuaAnggota()
        );
        return "anggota";
    }

    @PostMapping("/anggota")
    public String tambahAnggota(
            @RequestParam String idAnggota,
            @RequestParam String nama,
            @RequestParam int batasPinjam) {

        anggotaService.tambahAnggota(
                idAnggota, nama, batasPinjam
        );
        return "redirect:/anggota";
    }
}
