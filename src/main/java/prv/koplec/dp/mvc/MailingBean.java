package prv.koplec.dp.mvc;

// Model
public interface MailingBean {
    public String getLast();
    public void setLast(String last);

    public String getFirst();
    public void setFirst(String first);

    public String getEmail();
    public void setEmail(String email);

    // business method
    public boolean doSubscribe();

    // register result
    public String getErrorString();
}
