package com.kutayondersev.coronavirusdailyreportapp.entity;


public class CoronavirusReportInfo {
    private String m_province;
    private String m_country;
    private int m_totalReport;
    private int m_diffPrevDay;

    public String getProvince() {
        return m_province;
    }

    public void setProvince(String province) {
        m_province = province;
    }

    public String getCountry() {
        return m_country;
    }

    public void setCountry(String country) {
        m_country = country;
    }

    public int getTotalReport() {
        return m_totalReport;
    }

    public void setTotalReport(int totalReport) {
        m_totalReport = totalReport;
    }

    public int getDiffPrevDay() {
        return m_diffPrevDay;
    }

    public void setDiffPrevDay(int diffPrevDay) {
        m_diffPrevDay = diffPrevDay;
    }


}
