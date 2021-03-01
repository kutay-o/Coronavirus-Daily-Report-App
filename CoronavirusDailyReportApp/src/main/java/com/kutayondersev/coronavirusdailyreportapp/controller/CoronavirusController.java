package com.kutayondersev.coronavirusdailyreportapp.controller;

import com.kutayondersev.coronavirusdailyreportapp.service.CoronavirusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.NumberFormat;

@Controller
public class CoronavirusController {
    private CoronavirusService m_coronavirusService;

    public CoronavirusController(CoronavirusService coronavirusService) {
        m_coronavirusService = coronavirusService;
    }


    @GetMapping("/")
    public String CoronavirusTrackerInfo(Model model)
    {
        var reports = m_coronavirusService.getReports();
        int totalReport = reports.stream().mapToInt(rep -> rep.getTotalReport()).sum();
        int totalNewReport = reports.stream().mapToInt(rep -> rep.getDiffPrevDay()).sum();
        model.addAttribute("reportsInfo",reports);
        model.addAttribute("totalReport", NumberFormat.getInstance().format(totalReport));
        model.addAttribute("totalNewReport",NumberFormat.getInstance().format(totalNewReport));
        model.addAttribute("date", m_coronavirusService.getDate());

        return "Info";
    }

}
