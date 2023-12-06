package prv.koplec.servlet.tag;

import java.io.IOException;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.BodyContent;
import jakarta.servlet.jsp.tagext.BodyTagSupport;


public class CatNyanTag extends BodyTagSupport{

    @Override
    public int doAfterBody() throws JspException {
        try{
            // 出力用JSPWriter
            JspWriter out = getPreviousOut();
            // タグで囲まれている文字列取得
            BodyContent bodyContent = getBodyContent();
            String bodyText = bodyContent.getString();
            
            out.print(bodyText.replaceAll("。", "にゃん。"));

            bodyContent.clearBody();
            return SKIP_BODY;
        }catch(IOException e){
            e.printStackTrace();
            throw new JspException(e.getMessage());
        }
    }
}
