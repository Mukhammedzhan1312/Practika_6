import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

interface IReportBuilder {
    void setHeader(String header);
    void setContent(String content);
    void setFooter(String footer);
    void addSection(String sectionName, String sectionContent);
    void setStyle(ReportStyle style);
    Report getReport();
}


class TextReportBuilder implements IReportBuilder {
    private Report report = new Report();

    @Override
    public void setHeader(String header) {
        report.setHeader("Text Report Header: " + header);
    }

    @Override
    public void setContent(String content) {
        report.setContent("Content: " + content);
    }

    @Override
    public void setFooter(String footer) {
        report.setFooter("Text Report Footer: " + footer);
    }

    @Override
    public void addSection(String sectionName, String sectionContent) {
        report.addSection("Section: " + sectionName + "\n" + sectionContent);
    }

    @Override
    public void setStyle(ReportStyle style) {
    }

    @Override
    public Report getReport() {
        return report;
    }
}

class HtmlReportBuilder implements IReportBuilder {
    private Report report = new Report();

    @Override
    public void setHeader(String header) {
        report.setHeader("<h1>" + header + "</h1>");
    }

    @Override
    public void setContent(String content) {
        report.setContent("<p>" + content + "</p>");
    }

    @Override
    public void setFooter(String footer) {
        report.setFooter("<footer>" + footer + "</footer>");
    }

    @Override
    public void addSection(String sectionName, String sectionContent) {
        report.addSection("<h2>" + sectionName + "</h2><p>" + sectionContent + "</p>");
    }

    @Override
    public void setStyle(ReportStyle style) {
        report.setStyle(style);
    }

    @Override
    public Report getReport() {
        return report;
    }
}

class ReportStyle {
    private String backgroundColor;
    private String fontColor;
    private int fontSize;

    public ReportStyle(ReportStyleType styleType) {
        switch (styleType) {
            case DEFAULT:
                this.backgroundColor = "white";
                this.fontColor = "black";
                this.fontSize = 12;
                break;
            case CUSTOM:
                this.backgroundColor = "lightgrey";
                this.fontColor = "darkblue";
                this.fontSize = 14;
                break;
        }
    }
}


class Report {
    private String header;
    private String content;
    private String footer;
    private List<String> sections = new ArrayList<>();
    private ReportStyle style;

    public void setHeader(String header) {
        this.header = header;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public void addSection(String section) {
        sections.add(section);
    }

    public void setStyle(ReportStyle style) {
        this.style = style;
    }

    public void export(LogLevel level) {
        Path filePath = Paths.get("C:/Users/VICTUS/Desktop/2 - 3 курс сабактар отчет/report.txt");

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(header).append(System.lineSeparator());
        reportBuilder.append(content).append(System.lineSeparator());
        for (String section : sections) {
            reportBuilder.append(section).append(System.lineSeparator());
        }
        reportBuilder.append(footer).append(System.lineSeparator());

        String logMessage = reportBuilder.toString();

        if (level.ordinal() >= LogLevel.INFO.ordinal()) {
            try {
                Files.write(filePath, logMessage.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class ReportDirector {
    public void constructReport(IReportBuilder builder, ReportStyle style) {
        builder.setHeader("Annual Report");
        builder.setContent("This is the content of the report.");
        builder.addSection("Introduction", "This section introduces the report.");
        builder.addSection("Data", "This section contains data.");
        builder.setFooter("End of Report");
        builder.setStyle(style);
    }

    public IReportBuilder getBuilder(ReportType type) {
        switch (type) {
            case TEXT:
                return new TextReportBuilder();
            case HTML:
                return new HtmlReportBuilder();
            default:
                throw new IllegalArgumentException("Unsupported report type");
        }
    }
}


enum ReportType {
    TEXT,
    HTML,
    PDF
}

enum ReportStyleType {
    DEFAULT,
    CUSTOM
}

public class Main_2 {
    public static void main(String[] args) {
        ReportDirector director = new ReportDirector();
        IReportBuilder textBuilder = director.getBuilder(ReportType.TEXT);
        ReportStyle textStyle = new ReportStyle(ReportStyleType.DEFAULT);
        director.constructReport(textBuilder, textStyle);
        Report textReport = textBuilder.getReport();
        textReport.export(LogLevel.INFO);
    }
}
