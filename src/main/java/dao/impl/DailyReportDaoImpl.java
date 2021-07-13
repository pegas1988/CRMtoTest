package dao.impl;

import dao.DailyReportDao;
import org.springframework.stereotype.Repository;
import utility.PostgresUtil;
import entity.DailyReport;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DailyReportDaoImpl implements DailyReportDao {
    private static final String SELECT_ALL = "select from daily_report";
    private static final String SELECT_BY_DATE = "select * from daily_report where date_of_report = ?";
    private static final String CREATE_REPORT = "insert into daily_report (date_of_report, responsible_person) values (?,?)";

    public DailyReportDaoImpl() {
    }

    private DataSource dataSource;
    //private static final String SELECT_BY_PRODUCT = "";

    public DailyReportDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<DailyReport> findAll() {
        List<DailyReport> dailyReports = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL);
        ) {
            while (resultSet.next()) {
                DailyReport report = new DailyReport();
                report.setDate(resultSet.getDate("date_of_report"));
                report.setResponsiblePerson(resultSet.getString("responsible_person"));
                dailyReports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dailyReports;
    }
    @Override
    public List<DailyReport> findByCreatingDate(Date date) {
        List<DailyReport> dailyReports = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_DATE)) {
            preparedStatement.setDate(1, date);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DailyReport report = new DailyReport();
                report.setDate(resultSet.getDate("date_of_report"));
                report.setResponsiblePerson(resultSet.getString("responsible_person"));
                dailyReports.add(report);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dailyReports;
    }
    @Override
    public void createReport(DailyReport report) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_REPORT);
        ) {
            preparedStatement.setString(2, report.getResponsiblePerson());
            preparedStatement.setDate(1, (Date) report.getDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
