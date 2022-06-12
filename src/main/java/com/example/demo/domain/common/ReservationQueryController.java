package com.example.demo.domain.common;

import com.example.demo.domain.common.com.apress.springrecipes.court.Delayer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.concurrent.Callable;

@Controller
@RequestMapping("/reservationQuery")
@Slf4j
public class ReservationQueryController {
    private final ReservationService reservationService;
    @Autowired
    private TaskExecutor taskExecutor;

    public ReservationQueryController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public void setupForm() {

    }

    @GetMapping("/all")
    public DeferredResult<String> getAll(Model model) {
        final DeferredResult<String> result = new DeferredResult<>();
        taskExecutor.execute(() -> {
            log.info("Deferred task started."+ Thread.currentThread().getName());
            List<Reservation> reservations = reservationService.queryAll();
            Delayer.randomDelay();
            model.addAttribute("reservations", reservations);
            result.setResult("reservationQuery");
            log.info("Deferred task ended."+ Thread.currentThread().getName());
        });
        return result;
    }

    @GetMapping("/all1")
    public Callable<String> getAll1(Model model) {
        return () -> {
            log.info("Deferred task started."+ Thread.currentThread().getName());
            List<Reservation> reservations = reservationService.queryAll();
            Delayer.randomDelay();
            model.addAttribute("reservations", reservations);
            log.info("Deferred task ended."+ Thread.currentThread().getName());
            return "reservationQuery";
        };
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
