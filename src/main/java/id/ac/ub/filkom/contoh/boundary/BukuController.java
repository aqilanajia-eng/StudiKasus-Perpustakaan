
package id.ac.ub.filkom.contoh.boundary;

import id.ac.ub.filkom.contoh.control.BukuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/** Boundary: halaman pengelolaan buku. */
@Controller
public class BukuController {

    private final BukuService bukuService;

    public BukuController(BukuService bukuService) {
        this.bukuService = bukuService;
    }

    @GetMapping("/buku")
    public String daftarBuku(
            @RequestParam(required = false) String kata,
            Model model) {

        model.addAttribute(
                "daftarBuku",
                bukuService.cariBuku(kata)
        );
        model.addAttribute("kata", kata);

        return "buku";
    }

    @PostMapping("/buku")
    public String tambahBuku(
            @RequestParam String idBuku,
            @RequestParam String judul,
            @RequestParam int stok) {

        bukuService.tambahBuku(idBuku, judul, stok);
        return "redirect:/buku";
    }
}

