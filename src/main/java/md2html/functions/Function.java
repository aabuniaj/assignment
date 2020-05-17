package md2html.functions;

import md2html.convert.ConvertToHtmlUtil;

public interface Function {
    boolean find(String line);
    String getInput();
    void setInput(String input);
    StringBuilder convert(ConvertToHtmlUtil convertToHtmlUtil, StringBuilder result);
}
