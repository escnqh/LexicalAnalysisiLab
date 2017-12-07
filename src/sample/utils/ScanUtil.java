package sample.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;

/**
 * 读取工具类
 */
public class ScanUtil {

    private int ch;
    public int code;
    private StringBuffer strToken = new StringBuffer();
    private String[] retainWord = new String[]{"int", "if", "else", "return", "main", "void", "while", "break", "continue", "private", "public", "String", "class", "for", "boolean"};
    private String resultString = "Result is:\n";

    public ScanUtil() {

    }

    /**
     * 判断是否是字母
     *
     * @return
     */
    public boolean IsLetter() {
        if ((ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122)) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是数字
     *
     * @return
     */
    public boolean IsDigit() {
        if (ch >= 48 && ch <= 57) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是空格
     *
     * @param ch
     * @return
     */
    public boolean IsBC(int ch) {
        if (ch == 32) {
            return true;
        }
        return false;
    }

    /**
     * 连接字符
     *
     * @param ch
     */
    public void Concat(char ch) {
        strToken.append(ch);
    }

    /**
     * 判断是否是保留字
     *
     * @return
     */
    public int Reserve() {
        for (int i = 0; i < retainWord.length; i++) {
            if (strToken.toString().equals(retainWord[i])) {
                return 1;
            }
        }
        if (strToken.length() != 0) {
            if (strToken.charAt(0) >= '0' && strToken.charAt(0) <= '9') {
                return 3;
            } else if (strToken.charAt(0) == '=') {
                return 4;
            }
        } else if (strToken.toString().equals("")) {
            return 0;
            //一开始忘了空处理
        }

        return 2;
    }

    /**
     *
     */
    public void Retract() {
        code = Reserve();
        if (code == 1) {
            resultString += "('" + 1 + "','" + strToken + "')\n";
        } else if (code == 2) {
            resultString += "('" + 2 + "','" + strToken + "')\n";
        } else if (code == 3) {
            resultString += "('" + 3 + "','" + strToken + "')\n";
        } else if (code == 4) {
            resultString += "('" + 4 + "','" + strToken + "')\n";
        }
        strToken.delete(0, strToken.length());
    }

    /**
     * 读取工具
     *
     * @param string 源代码
     */
    public String scanner(String string) {
        BufferedReader br;
        try {
            br = new BufferedReader(new StringReader(string));
            while ((ch = br.read()) != -1) {
                //System.out.println("======="+(char)ch);
                if (IsBC(ch) == false) {
                    if (IsLetter()) {
                        if (IsLetter() == true || IsDigit() == true) {
                            Concat((char) ch);
                        }
                    } else if (IsDigit() == true) {
                        Concat((char) ch);
                    } else if (IsDigit()) {
                        Concat((char) ch);
                    } else if (ch == 61) {
                        if ((strToken.length() != 0) && (strToken.charAt(0) == '=')) {
                            strToken.append((char) ch);
                            resultString += "('" + 4 + "','" + strToken + "')\n";
                            strToken.delete(0, strToken.length());
                        } else {
                            strToken.append((char) ch);
                        }
                    } else if (ch == 42) {
                        Retract();
                        resultString += "('" + 4 + "','" + (char) ch + "')\n";// *
                    } else if (ch == 43) {
                        Retract();
                        resultString += "('" + 4 + "','" + (char) ch + "')\n";// +
                    } else if (ch == 45) {
                        Retract();
                        resultString += "('" + 4 + "','" + (char) ch + "')\n";// -
                    } else if (ch == 47) {
                        Retract();
                        resultString += "('" + 4 + "','" + (char) ch + "')\n"; // /
                    } else if (ch == 60) {
                        Retract();
                        resultString += "('" + 4 + "','" + (char) ch + "')\n";// <
                    }
//                    else if (ch == 61) {
//                        Retract();
//                        resultString += "('" + 4 + "','" + (char) ch + "')\n";// =
//                    }
                    else if (ch == 62) {
                        Retract();
                        resultString += "('" + 4 + "','" + (char) ch + "')\n";// >
                    } else if ((char) ch == ';') {
                        Retract();
                        resultString += "('" + 5 + "','" + (char) ch + "')\n";
                    } else if ((char) ch == '(') {
                        Retract();
                        resultString += "('" + 5 + "','" + (char) ch + "')\n";
                    } else if ((char) ch == ')') {
                        Retract();
                        resultString += "('" + 5 + "','" + (char) ch + "')\n";
                    } else if ((char) ch == '{') {
                        Retract();
                        resultString += "('" + 5 + "','" + (char) ch + "')\n";
                    } else if ((char) ch == '}') {
                        Retract();
                        resultString += "('" + 5 + "','" + (char) ch + "')\n";
                    } else if ((char) ch == ',') {
                        Retract();
                        resultString += "('" + 5 + "','" + (char) ch + "')\n";
                    }

                } else {
                    Retract();
                }

            }
        } catch (
                FileNotFoundException e1)

        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (
                IOException e)

        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return resultString;
    }

}
