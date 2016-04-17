package com.flying.web.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "tb_msg")
public class Msg {

    // 主键id自增
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 发送者
    @Column(name = "sender")
    private String sender;

    // 接受者
    @Column(name = "receptor")
    private String receptor;

    // 消息内容
    @Column(name = "msg")
    private String msg;

    // 发送时间
    @Column(name = "sendDate")
    private Long sendDate;

    // 发送时间-显示
    @Transient
    private String sendDate4Display;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getSendDate() {
        return sendDate;
    }

    public void setSendDate(Long sendDate) {
        this.sendDate = sendDate;
    }

    public String getSendDate4Display() {
        return sendDate4Display;
    }

    public void setSendDate4Display(String sendDate4Display) {
        this.sendDate4Display = sendDate4Display;
    }

    public Msg(Long id, String sender, String receptor, String msg, Long sendDate) {
        super();
        this.id = id;
        this.sender = sender;
        this.receptor = receptor;
        this.msg = msg;
        this.sendDate = sendDate;
    }

    public Msg() {
        super();
    }

    @Override
    public String toString() {
        return "Msg [id=" + id + ", sender=" + sender + ", receptor=" + receptor + ", msg=" + msg
                + ", sendDate=" + sendDate + ", sendDate4Display=" + sendDate4Display + "]";
    }

}
