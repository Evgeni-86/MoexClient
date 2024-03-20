package org.moexclient.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class PagesController {
    @GetMapping("/")
    public String showStartPage() {
        return "index";
    }

    @GetMapping("/contracts-open-positions")
    public String showOpenPositionsPage() {
        return "contracts-open-positions";
    }

    @GetMapping("/open-positions-charts")
    public String showOpenPositionsChart() {
        return "open-positions-charts";
    }
}
