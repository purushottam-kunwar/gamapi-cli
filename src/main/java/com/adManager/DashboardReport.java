package com.adManager;

import com.google.api.ads.admanager.axis.utils.v202105.DateTimes;
import com.google.api.ads.admanager.axis.utils.v202105.ReportDownloader;
import com.google.api.ads.admanager.axis.utils.v202105.StatementBuilder;
import com.google.api.ads.admanager.axis.v202105.*;
import com.google.common.io.Files;
import com.google.common.io.Resources;

import java.io.File;
import java.net.URL;

public class DashboardReport {
    ReportQuery reportQuery = new ReportQuery();

    public void setReportQuery(ReportQuery reportQuery) {
        this.reportQuery = reportQuery;
        reportQuery.setDimensions(new Dimension[]{
                Dimension.DATE, Dimension.ORDER_ID
        });
        reportQuery.setColumns(new Column[]{
                Column.AD_SERVER_IMPRESSIONS,
                Column.AD_SERVER_CLICKS,
                Column.AD_SERVER_CTR,
                Column.AD_SERVER_CPM_AND_CPC_REVENUE
        });

        // Create statement to filter for an order.
        int orderId = 0;
        StatementBuilder statementBuilder = new StatementBuilder()
                .where("ORDER_ID = :orderId")
                .withBindVariableValue("orderId", orderId);

        //Set the filter statement
        reportQuery.setStatement(statementBuilder.toStatement());


        // Set the start and end dates or choose a dynamic date range type.
        reportQuery.setDateRangeType(DateRangeType.CUSTOM_DATE);
        reportQuery.setStartDate(
                DateTimes.toDateTime("2013-05-01T00:00:00", "America/New_York").getDate());
        reportQuery.setEndDate(
                DateTimes.toDateTime("2013-05-31T00:00:00", "America/New_York").getDate());

        //Create report jon
        ReportJob reportJob = new ReportJob();
        reportJob.setReportQuery(reportQuery);

        //Run report job



        //Download the report
        ReportDownloader reportDownloader = new ReportDownloader(reportService, reportJob.getId());
        reportDownloader.waitForReportReady();

        //Change to your file location
        File file = File.createTempFile("delivery-report-", ".csv.gz");
        System.out.printf("Downloading report to %s ...", file.toString());

        // Download the report
        ReportDownloadOptions  reportDownloadOptions = new ReportDownloadOptions();
        reportDownloadOptions.setExportFormat(ExportFormat.CSV_DUMP);
        reportDownloadOptions.setUseGzipCompression(true);
        URL url = reportDownloader.getDownloadUrl(reportDownloadOptions);
        Resources.asByteSource(url).copyTo(Files.asByteSink(file));

        System.out.println("done.");


        // Reading the report data
//        List rows = CsvFiles.getCsvDataArray(file, true);
//        for(String[] row: rows){ (row);
//        }
//            //additional row processing




    }


}
