package ptithcm.entity;

import javax.persistence.*;
import java.util.Collection;

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

    @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
    @JoinTable(
            name = "thongbao",
            joinColumns = { @JoinColumn(name = "mssv") },
            inverseJoinColumns = { @JoinColumn(name = "matb") }
    )
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

//    @Override
//    public String toString() {
//        return "Student{" +
//                "studentCode='" + studentCode + '\'' +
//                ", password='" + password + '\'' +
//                ", classCode='" + classCode + '\'' +
//                ", fullName='" + fullName + '\'' +
//                ", phone='" + phone + '\'' +
//                ", birthday='" + birthday + '\'' +
//                ", avt='" + avt + '\'' +
//                ", bch=" + bch +
//                ", bcs=" + bcs +
//                ", ctv=" + ctv +
//                ", gender=" + gender +
//                ", notifications=" + notifications +
//                '}';
//    }
}
