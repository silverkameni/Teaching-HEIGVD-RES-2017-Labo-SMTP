/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.util.ArrayList;
import java.util.List;

/*
 *@authors silver kameni && zacharie nguefack
 */
public class Mail {

    private String from;
    private final List<String> to = new ArrayList<>();
    private final List<String> cc = new ArrayList<>();
    private String subject;
    private String body;
    private String address;
    private String name;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> s) {
        this.to.addAll(s);
    }

    public void setCc(List<String> s) {
        this.cc.addAll(s);
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        if (name != null) {
            buffer.append("\"" + name + "\"");
        }
        if (address != null) {
            buffer.append("<" + address + ">");
        } else {
            return null;
        }
        return buffer.toString();
    }

}

