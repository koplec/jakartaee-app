// 独習JSP&サーブレット8.3より演習
package prv.koplec.servlet.tag;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import jakarta.servlet.ServletContext;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

public class CsvReaderTag extends TagSupport{
    private String path = null;
    private String delimiter = ",";
    private final String encoding = "UTF-8";

    // 属性はSetterで渡す
    public void setPath(String path) {
        this.path = path;
    }

    public void setDelimiter(String delimeter) {
        this.delimiter = delimeter;
    }

    @Override
    public int doStartTag() throws JspException {
        // pageContextを使って、JSPの暗黙オブジェクトにアクセスできる
     ServletContext app = pageContext.getServletContext();
     try(BufferedReader buf = Files.newBufferedReader(Paths.get(app.getRealPath(path)), Charset.forName(encoding))){
        JspWriter out = pageContext.getOut();
        boolean title_line = true;
        out.println("<table class='table'>");
        while(buf.ready()){
            String line = buf.readLine();
            String[] data = line.split(this.delimiter);
            out.println("<tr>");
            for(String item : data){
                if(title_line){
                    out.println("<th>" + item + "</th>");
                }else{
                    out.println("<td>" + item + "</td>");
                }
            }
            out.println("</tr>");
            title_line = false;
        }
        out.println("<table>");
     }catch(Exception e){
        throw new JspException(e);
     }
     return SKIP_BODY;
    }


}
