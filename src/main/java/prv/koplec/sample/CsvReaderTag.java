package prv.koplec.sample;

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
    private String delimeter = ",";
    private String encoding = "UTF-8";

    @Override
    public int doStartTag() throws JspException {
        // pageContextはJSP内の暗黙オブジェクトにアクセスできる
        ServletContext ctx = pageContext.getServletContext();
        try(BufferedReader buf = Files.newBufferedReader(
            Paths.get(ctx.getRealPath(path)), Charset.forName(encoding))){
            JspWriter out = pageContext.getOut();
            // 出力行がタイトル行であるかの判定
            boolean title_line = true;
            out.print("<table class='table'>");
            while(buf.ready()){
                String line = buf.readLine();
                // split
                String[] data = line.split(this.delimeter);
                out.println("<tr>");
                for (String s : data) {
                    if(title_line){
                        out.println("<th>" + s + "</th>");
                    }else{
                        out.println("<td>" + s + "</td>");
                    }
                }
                out.println("</tr>");
                title_line = false;
            }

            out.print("</table>");
        }catch(Exception e){
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
