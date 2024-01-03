package ptithcm.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "sinhvien")
public class Student {
    @Id
    @Column(name = "mssv")
    private String studentCode;

    private String password;

    @Column(name = "malop")
    private String classCode;

    @Column(name = "hoten")
    private String fullName;

    @Column(name = "sdt")
    private String phone;
    @Column(name = "ngaysinh")
    private String birthday;

    private String avt;

    private int bch;

    private int bcs;

    private int ctv;

    @Column(name = "gioitinh")
    private int gender;

    @ManyToMany(mappedBy="students",cascade = { CascadeType.ALL })
//    @JoinTable(
//            name = "thongbao",
//            joinColumns = { @JoinColumn(name = "mssv") },
//            inverseJoinColumns = { @JoinColumn(name = "matb") }
//    )
    private Collection<Notification> notifications;

    public Collection<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Collection<Notification> notifications) {
        this.notifications = notifications;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvt() {
        return avt;
    }

    public void setAvt(String avt) {
        this.avt = avt;
    }

    public int getBch() {
        return bch;
    }

    public void setBch(int bch) {
        this.bch = bch;
    }

    public int getBcs() {
        return bcs;
    }

    public void setBcs(int bcs) {
        this.bcs = bcs;
    }

    public int getCtv() {
        return ctv;
    }

    public void setCtv(int ctv) {
        this.ctv = ctv;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
