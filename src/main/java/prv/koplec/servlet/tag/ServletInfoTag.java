package prv.koplec.servlet.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.jsp.JspContext;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.DynamicAttributes;
import jakarta.servlet.jsp.tagext.JspFragment;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

public class ServletInfoTag extends SimpleTagSupport implements DynamicAttributes{
    private JspFragment row;
    public void setRow(JspFragment row) {
        this.row = row;
    }

    private Map<String, String> map; //動的属性を保存
    public ServletInfoTag(){
        map = new HashMap<>();
    }
    @Override
    public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
        map.put(localName, value.toString()); 
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspContext context = getJspContext();
        for(String name : map.keySet()){
            if(map.get(name).equals("true")){
                context.setAttribute("name", name);
                context.setAttribute("value", System.getProperty(name));
                row.invoke(null);//nullはcontext.getOutと同じ
            }
        }
    }
}
