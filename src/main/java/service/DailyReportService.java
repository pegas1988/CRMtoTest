package service;

import dao.DailyReportDao;
import dao.impl.DailyReportDaoImpl;
import entity.DailyReport;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class DailyReportService {
    DailyReportDao dailyReportDaoImpl = new DailyReportDaoImpl();

    public List<DailyReport> findAll() {
        return dailyReportDaoImpl.findAll();
    }

    public List<DailyReport> findByCreatingDay(Date date) {
        return dailyReportDaoImpl.findByCreatingDate(date);
    }

    public void createDailyReport(DailyReport dailyReport) {
        dailyReportDaoImpl.createReport(dailyReport);
    }
}
