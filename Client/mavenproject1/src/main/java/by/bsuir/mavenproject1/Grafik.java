package by.bsuir.mavenproject1;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class Grafik {
    public static CategoryDataset createDataset(double[]mass)
{
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    dataset.addValue(mass[0], "Степендия", "Январь");
    dataset.addValue(mass[1], "Степендия", "Февраль");
    dataset.addValue(mass[2], "Степендия", "Март");
    dataset.addValue(mass[3], "Степендия", "Апрель");
    dataset.addValue(mass[4], "Степендия", "Май");
    dataset.addValue(mass[5], "Степендия", "Июнь");
    dataset.addValue(mass[6], "Степендия", "Июль");
    dataset.addValue(mass[7], "Степендия", "Август");
    dataset.addValue(mass[8], "Степендия", "Сентябрь");
    dataset.addValue(mass[9], "Степендия", "Октябрь");
    dataset.addValue(mass[10], "Степендия", "Ноябрь");
    dataset.addValue(mass[11], "Степендия", "Декабрь");
    return dataset;
}
    public static JFreeChart createChart(CategoryDataset dataset)
{
     JFreeChart chart = ChartFactory.createBarChart( "Степендия в месяц", 
                      null,                   // x-axis label
                      "Доход",                // y-axis label
                      dataset, PlotOrientation.VERTICAL, true, true, true);

    chart.setBackgroundPaint(Color.white);
    CategoryPlot plot = (CategoryPlot) chart.getPlot();
    NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    BarRenderer renderer = (BarRenderer) plot.getRenderer();
    renderer.setDrawBarOutline(false);
    chart.getLegend().setFrame(BlockBorder.NONE);
    return chart;
}
    
}
