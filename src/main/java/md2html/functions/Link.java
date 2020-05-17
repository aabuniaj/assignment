package md2html.functions;

import md2html.convert.ConvertToHtmlUtil;

public class Link implements Function {

    private static final int MAX_HEADING = 6;

    private StringBuilder textBeforeLink = new StringBuilder();
    private String linkText = "";
    private String url = "";
    private StringBuilder textAfterLink = new StringBuilder();
    private String input = "";

    @Override
    public boolean find(String line) {
        int level = 0;
        if(line.indexOf('[') != -1 &&
                line.indexOf(']') != -1 &&
                line.indexOf('(') != -1 &&
                line.indexOf(')') != -1) {
            textBeforeLink.append(line, 0, line.indexOf('['));
            linkText = line.substring(line.indexOf('[')+1, line.indexOf(']'));
            url = line.substring(line.indexOf('(')+1, line.indexOf(')'));
            textAfterLink.append(line.substring(line.indexOf(')')+1));
            if(line.startsWith("#")) {
                //get heading levels
                for (char letter : line.toCharArray()) {
                    if (letter != '#') {
                        break;
                    }
                    //remove heading hash since we are going to manually replace by <h>
                    textBeforeLink.replace(0, level+1, "");
                    level++;
                    if (level > MAX_HEADING) {
                        break;
                    }
                }
                textBeforeLink.insert(0, "<h"+level+">");
                textAfterLink.append("</h")
                        .append(level)
                        .append(">\n\n");
            } else {
                //it's a paragraph. Check if there is <h> exists
                if(!textBeforeLink.toString().startsWith("<h") &&
                    !textAfterLink.toString().endsWith("</h")) {
                    textBeforeLink.insert(0, "<p>");
                    textAfterLink.append("</p>");
                }
            }
            return !linkText.isEmpty() && !url.isEmpty();
        } else {
            return false;
        }
    }

    @Override
    public String getInput() {
        return input;
    }

    @Override
    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public StringBuilder convert(ConvertToHtmlUtil convertToHtmlUtil, StringBuilder result) {
        return convertToHtmlUtil.convertLink(this, result);
    }

    public String getTextBeforeLink() {
        return this.textBeforeLink.toString();
    }

    public String getLinkText() {
        return this.linkText;
    }

    public String getUrl() {
        return this.url;
    }

    public String getTextAfterLink() {
        return this.textAfterLink.toString();
    }
}
