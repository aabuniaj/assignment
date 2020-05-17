package md2html.convert;

import md2html.functions.Heading;
import md2html.functions.Link;
import md2html.functions.Paragraph;

public class ConvertToHtmlUtil {

    public StringBuilder convertHeading(Heading heading, StringBuilder result) {
        return result.append("<h")
                .append(heading.getLevel())
                .append(">")
                .append(heading.getInput())
                .append("</h")
                .append(heading.getLevel())
                .append(">");
    }

    public StringBuilder convertParagraph(Paragraph paragraph, StringBuilder result) {
        //ignore new empty line
        if(paragraph.getInput().isEmpty()) {
            return result.append("\n\n");
        }
        //check if we need to continue with the paragraph
        if(result != null && result.length() > 0) {
            if(result.toString().endsWith("</p>")) {
                result.replace(result.length()-4, result.length(), "\n");
                return result.append(paragraph.getInput())
                        .append("</p>");
            }
        }
        //create paragraph
        return result.append("<p>")
                .append(paragraph.getInput())
                .append("</p>");
    }

    public StringBuilder convertLink(Link link, StringBuilder result) {
        result.append(link.getTextBeforeLink())
                .append("<a href=\"")
                .append(link.getUrl())
                .append("\">")
                .append(link.getLinkText())
                .append("</a>")
                .append(link.getTextAfterLink());
        return result;
    }
}
