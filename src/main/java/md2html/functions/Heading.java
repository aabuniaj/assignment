package md2html.functions;

import md2html.convert.ConvertToHtmlUtil;

import java.util.Scanner;

public class Heading implements Function {

    private static final int MAX_HEADING = 6;

    private int level = 0;
    private String input = "";

    @Override
    public boolean find(String line) {
        level = 0;
        if (line.isEmpty()) {
            return false;
        }

        //Get Heading level
        String[] lineSplit = line.split(" ");
        //check start of line if has heading hash

        //There is no heading hash
        if(lineSplit[0].isEmpty()) {
            return false;
        }

        for (char letter : lineSplit[0].toCharArray()) {
            if (letter != '#') {
                return false;
            }
            level++;
            if (level > MAX_HEADING) {
                return false;
            }
        }
        return true;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String getInput() {
        return input;
    }

    @Override
    public void setInput(String input) {
        String[] splitHeadingInput = input.split(" ");
        this.input = input.substring(splitHeadingInput[0].length()+1);
    }

    @Override
    public StringBuilder convert(ConvertToHtmlUtil convertToHtmlUtil, StringBuilder result) {
        return convertToHtmlUtil.convertHeading(this, result);
    }
}

