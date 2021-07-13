package dao;

import entity.DailyReport;

import java.sql.Date;
import java.util.List;

public interface DailyReportDao {
    List<DailyReport> findAll();

    List<DailyReport> findByCreatingDate(Date date);

    void createReport(DailyReport report);

}
