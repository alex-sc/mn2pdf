package org.metanorma.fop;

import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.contentstream.PDFGraphicsStreamEngine;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.graphics.image.PDImage;


public class LineCatcher extends PDFGraphicsStreamEngine
{
    private final GeneralPath linePath = new GeneralPath();
    private int clipWindingRule = -1;

    public LineCatcher(PDPage page)
    {
        super(page);
    }

    public static void main(String[] args) throws IOException
    {
        String filepath = "D:\\Metanorma\\mn2pdf\\test.pdf";
        try (PDDocument document = PDDocument.load(new File(filepath)))
        {
            PDPage page = document.getPage(0);
            LineCatcher test = new LineCatcher(page);
            test.processPage(page);
            document.save(new File(filepath+"2.pdf"));
        }
    }

    @Override
    public void appendRectangle(Point2D p0, Point2D p1, Point2D p2, Point2D p3) throws IOException
    {
        System.out.println("appendRectangle");
        // to ensure that the path is created in the right direction, we have to create
        // it by combining single lines instead of creating a simple rectangle
        linePath.moveTo((float) p0.getX(), (float) p0.getY());
        linePath.lineTo((float) p1.getX(), (float) p1.getY());
        linePath.lineTo((float) p2.getX(), (float) p2.getY());
        linePath.lineTo((float) p3.getX(), (float) p3.getY());

        // close the subpath instead of adding the last line so that a possible set line
        // cap style isn't taken into account at the "beginning" of the rectangle
        linePath.closePath();
    }

    @Override
    public void drawImage(PDImage pdi) throws IOException
    {
        System.out.println("Width=" + pdi.getWidth());
        System.out.println("Height=" + pdi.getHeight());
    }

    @Override
    public void clip(int windingRule) throws IOException
    {
        // the clipping path will not be updated until the succeeding painting operator is called
        clipWindingRule = windingRule;

    }

    @Override
    public void moveTo(float x, float y) throws IOException
    {
        linePath.moveTo(x, y);
        linePath.lineTo(x, y);
        System.out.println("moveTo:" + "x=" + x + ", y=" + y);
    }

    @Override
    public void lineTo(float x, float y) throws IOException
    {
        linePath.lineTo(x, y);
        System.out.println("lineTo:" + "x=" + x + ", y=" + y);
    }

    @Override
    public void curveTo(float x1, float y1, float x2, float y2, float x3, float y3) throws IOException
    {
        linePath.curveTo(x1, y1, x2, y2, x3, y3);
        System.out.println("curveTo:" + "x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2 + ", x3=" + x3 + ", y3=" + y3);
    }

    @Override
    public Point2D getCurrentPoint() throws IOException
    {
        return linePath.getCurrentPoint();
    }

    @Override
    public void closePath() throws IOException
    {
        System.out.println("Closing path x=" + linePath.getCurrentPoint().getX() + ", y=" + + linePath.getCurrentPoint().getY());
        linePath.closePath();
    }

    @Override
    public void endPath() throws IOException
    {
        if (clipWindingRule != -1)
        {
            linePath.setWindingRule(clipWindingRule);
            getGraphicsState().intersectClippingPath(linePath);
            clipWindingRule = -1;
        }
        linePath.reset();

    }

    @Override
    public void strokePath() throws IOException
    {
        // do stuff
        System.out.println(linePath.getBounds2D());

        linePath.reset();
    }

    @Override
    public void fillPath(int windingRule) throws IOException
    {
        linePath.reset();
    }

    @Override
    public void fillAndStrokePath(int windingRule) throws IOException
    {
        linePath.reset();
    }

    @Override
    public void shadingFill(COSName cosn) throws IOException
    {
    }

    
}