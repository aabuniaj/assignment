package md2html.functions;

import md2html.convert.ConvertToHtmlUtil;

public class Paragraph implements Function {

    private String input = "";

    @Override
    public boolean find(String line) {
        return !line.isEmpty();
    }

    @Override
    public String getInput() {
        return this.input;
    }

    @Override
    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public StringBuilder convert(ConvertToHtmlUtil convertToHtmlUtil, StringBuilder result) {
        return convertToHtmlUtil.convertParagraph(this, result);
    }

}
