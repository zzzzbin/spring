package com.example.demo.domain.common;

import com.example.demo.domain.common.com.apress.springrecipes.court.Delayer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/reservationQuery")
public class ReservationQueryController {
    private final ReservationService reservationService;

    public ReservationQueryController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public void setupForm() {

    }

    @GetMapping("/all")
    public String getAll(Model model) {
        List<Reservation> reservations =  reservationService.queryAll();
        Delayer.randomDelay();
        model.addAttribute("reservations", reservations);
        return "reservationQuery";
    }


    @PostMapping
    public String getForm(@RequestParam("courtName") String courtName, Model model) {
        List<Reservation> reservations = java.util.Collections.emptyList();
        if (courtName != null) {
            Delayer.randomDelay();
            reservations = reservationService.query(courtName);
        }
        model.addAttribute("reservations", reservations);
        return "reservationQuery";
    }
}
