import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

public class Main {

    public static void  main(String[] args) throws IOException {


        //Open and Read file
        File r = new File("./input_scode.txt");
        FileInputStream temp = new FileInputStream(r);
        StringBuilder tem = new StringBuilder();
        for(int i = 0; i != -1; i = temp.read()){
            if(i == 0)continue;
            tem.append((char)i);
        }
        //Lexer
        List<Map<String, String>> res = lexer(tem);
        //Output
        System.out.println("{Token=Lexeme}");
        for(Object x : res){
            System.out.println(x);
        }


    }
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public static List<Map<String, String>> lexer(StringBuilder t){
        //Return Var
        List<Map<String, String>> res = new ArrayList<>();
        //Token Check
        StringBuilder curr = new StringBuilder();
        String Separator = "(){}[],:;";
        String Operator = "=/-*+<>";
        List<String> keywords = Arrays.asList(
                "and",      "asm",     "auto",      "bool",      "break",    "case",
                "catch",    "char",    "class",     "const",     "continue", "return",
                "do",       "double",  "else",      "enum",      "explicit", "extern",
                "false",    "float",   "for",       "friend",    "goto",     "if",
                "int",      "long",    "namespace", "new",       "not",      "nullptr",
                "operator", "or",      "private",   "protected", "public",   "return",
                "short",    "signed",  "sizeof",    "static",    "struct",   "main",
                "get",      "put",     "switch",    "template",  " this",    "throw",
                "true",     "try",     "typedef",   "typeid",    "typename", "unsigned",
                "using",    "virtual", "void",      "while",     "real",     "function",
                "boolean",  "real");
        //Lexer
        for(int i = 0; i < t.length(); i++){
            char temp = t.charAt(i);
            if(temp == ' ') continue;
            if(Separator.indexOf(temp) != -1 ||
            Operator.indexOf(temp) != -1){
                StringBuilder finalCurr = curr;
                Map<String, String> pair = new HashMap<>();
                if(keywords.contains(curr.toString())){
                    pair.put("Keyword", finalCurr.toString());
                    curr = new StringBuilder();
                } else if (isNumeric(curr.toString())) {
                    pair.put("Real", finalCurr.toString());
                    curr = new StringBuilder();
                } else {
                    if(!curr.isEmpty()){
                        pair.put("Identifier", finalCurr.toString());
                        curr = new StringBuilder();
                    }
                }
                res.add(pair);
                Map<String, String> t1 = new HashMap<>();
                if(Separator.indexOf(temp) != -1){
                    t1.put("Separator", String.valueOf(temp));
                }else{
                    t1.put("Operator", String.valueOf(temp));
                }
                res.add(t1);
            }else {
                curr.append(t.charAt(i));
            }
        }
        return res;
    }

}