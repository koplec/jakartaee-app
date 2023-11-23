package prv.koplec.dp.mvc;

public class MailingBeanFactory {
    public static MailingBean newInstance(){
        return new MailingBeanImpl();
    }
}
