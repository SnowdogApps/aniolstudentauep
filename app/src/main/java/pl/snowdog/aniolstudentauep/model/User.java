package pl.snowdog.aniolstudentauep.model;

import java.util.List;

/**
 * Created by Bartek on 2015-02-26.
 */
public class User {

    int id;
    boolean angel;
    String name;
    String surname;
    String avatar;
    String tel;
    String email;
    int rating;
    int age;
    int graduationAge;
    String currentEmployer;
    List<UserExperience> experience;
    List<String> skills;

    public User(int id, boolean angel, String name, String surname, String avatar, int age,
                int graduationAge, String currentEmployer, List<UserExperience> experience,
                List<String> skills) {
        this.id = id;
        this.angel = angel;
        this.name = name;
        this.surname = surname;
        this.avatar = avatar;
        this.age = age;
        this.graduationAge = graduationAge;
        this.currentEmployer = currentEmployer;
        this.experience = experience;
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", angel=" + angel +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", age=" + age +
                ", graduationAge=" + graduationAge +
                ", currentEmployer='" + currentEmployer + '\'' +
                ", experience=" + experience +
                ", skills=" + skills +
                '}';
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAngel() {
        return angel;
    }

    public void setAngel(boolean angel) {
        this.angel = angel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGraduationAge() {
        return graduationAge;
    }

    public void setGraduationAge(int graduationAge) {
        this.graduationAge = graduationAge;
    }

    public String getCurrentEmployer() {
        return currentEmployer;
    }

    public void setCurrentEmployer(String currentEmployer) {
        this.currentEmployer = currentEmployer;
    }

    public List<UserExperience> getExperience() {
        return experience;
    }

    public void setExperience(List<UserExperience> experience) {
        this.experience = experience;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
