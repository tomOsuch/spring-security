package pl.tomaszosuch.springsecuritysample;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Quotation")
public class QuotationController {

    private List<Quotation> quotations;

    public QuotationController() {
        this.quotations = new ArrayList<>();
        quotations.add(new Quotation("content 1", "author 1 test"));
        quotations.add(new Quotation("content 2", "author 2 test"));
    }

    @GetMapping("/api")
    public List<Quotation> getQuotation() {
        return quotations;
    }

    @PostMapping("/api")
    public boolean addQuotation(@RequestBody Quotation quotation) {
        return quotations.add(quotation);
    }

    @DeleteMapping("/api")
    public void deleteQuotation(@RequestParam int index) {
        quotations.remove(index);
    }
}
