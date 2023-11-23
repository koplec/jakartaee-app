package prv.koplec.dp.mvc;

public class MailingBeanImpl implements MailingBean{
    private String last;
    private String first;
    private String email;


    @Override
    public boolean doSubscribe() {
        System.out.println("MailingBeanImpl doSubscribe process...");
        return false;
    }


    @Override
    public String getErrorString() {
        return "MailingBeanImpl Error";
    }


    public String getLast() {
        return last;
    }


    public void setLast(String last) {
        this.last = last;
    }


    public String getFirst() {
        return first;
    }


    public void setFirst(String first) {
        this.first = first;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    

}
