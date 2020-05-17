package md2html;

import md2html.convert.ConvertToHtmlUtil;
import md2html.functions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Markdown File Conversion Program");

        if (args.length == 0) {
            throw new IllegalArgumentException("Please enter a file to convert...");
        }

        //Start conversion
        StringBuilder convertedData = startConversion(args[0]);

        createHTMLFile(convertedData);
    }

    private static StringBuilder startConversion(String file) {
        Scanner scanner;
        ConvertToHtmlUtil convertToHtmlUtil = new ConvertToHtmlUtil();
        try {
            scanner = new Scanner(new File(file));
        } catch(FileNotFoundException fnf) {
            fnf.printStackTrace();
            return null;
        }
        String line;
        StringBuilder result = new StringBuilder();
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();

            result = findFunction(line).convert(convertToHtmlUtil, result);
        }
        return result;
    }

    private static Function findFunction(String line) {
        Function heading = new Heading();
        Function link = new Link();
        Function paragraph = new Paragraph();

        if(link.find(line)) {
            link.setInput(line);
            return link;
        } else if (heading.find(line)) {
            heading.setInput(line);
            return heading;
        } else {
            paragraph.setInput(line);
            return paragraph;
        }
    }

    private static void createHTMLFile(StringBuilder convertedData) throws IOException {
        BufferedWriter writer = null;
        File converted = new File("converted.html");
        try {
            writer = new BufferedWriter(new FileWriter(converted));
            if(convertedData!=null) {
                System.out.println(convertedData.toString());
                writer.append(convertedData);
            } else {
                System.out.println("Program wasn't able to convert markdown file...");
                writer.append("Program wasn't able to convert markdown file...");
            }
        } finally {
            if(writer != null) {
                writer.close();
            }
        }
    }
}
