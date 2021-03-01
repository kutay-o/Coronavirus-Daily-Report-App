package com.kutayondersev.coronavirusdailyreportapp.service;

import com.kutayondersev.coronavirusdailyreportapp.entity.CoronavirusReportInfo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronavirusService {
    private static String DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<CoronavirusReportInfo> m_reports = new ArrayList<>();
    private String date;

    public List<CoronavirusReportInfo> getReports() {
        return m_reports;
    }

    public String getDate() {
        return date;
    }

    @PostConstruct
    public void getData() throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder().uri(URI.create(DATA_URL)).build();
        var httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader csv = new StringReader(httpResponse.body());
        CSVParser reports = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csv);

        List<CoronavirusReportInfo> temp = new ArrayList<>();

        List titles = reports.getHeaderNames();

        var day = titles.get(titles.size() - 1);
        this.date = String.valueOf(day);
        var prevDay = titles.get(titles.size() - 2);

        for(CSVRecord rec : reports)
        {
            var info = new CoronavirusReportInfo();
            info.setProvince(rec.get("Province/State"));
            info.setCountry(rec.get("Country/Region"));
            info.setTotalReport(Integer.parseInt(rec.get(String.valueOf(day))));
            var diff = Integer.parseInt(rec.get(String.valueOf(day))) - Integer.parseInt(rec.get(String.valueOf(prevDay)));
            info.setDiffPrevDay(diff);
            temp.add(info);
        }

        m_reports = temp;

    }
}
