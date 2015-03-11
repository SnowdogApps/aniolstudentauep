package pl.snowdog.aniolstudentauep.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.snowdog.aniolstudentauep.model.User;
import pl.snowdog.aniolstudentauep.model.UserExperience;

/**
 * Created by Bartek on 2015-02-26.
 */
public class Dao {

    static List<User> users;
    static List<String> tags;
    static List<Integer> connectedIds;

    static {
        connectedIds = new ArrayList<Integer>();

        users = new ArrayList<User>();

        List<UserExperience> experience1 = new ArrayList<UserExperience>();
        UserExperience exp11 = new UserExperience("Sportex", "2014 - ");
        UserExperience exp12 = new UserExperience("BZ WBK", "2012 - 2014");
        experience1.add(exp11);
        experience1.add(exp12);

        List<String> skills1 = new ArrayList<String>();
        skills1.add("własny biznes");
        skills1.add("kariera");
        skills1.add("pomoc w założeniu firmy");
        skills1.add("sport");
        skills1.add("organizacja zawodów");
        User user1 = new User(1, true, "Paweł", "Kaliszak",
                "http://www.ice-full.pl/files/fck/Image/kadra/pawel-ogorek.JPG",
                27, 2012, "Sportex", experience1, skills1);
        user1.setRating(5);
        user1.setTel("123456789");
        user1.setEmail("pawel@sportex.com");
        users.add(user1);

        List<UserExperience> experience2 = new ArrayList<UserExperience>();
        UserExperience exp21 = new UserExperience("BZ WBK", "2008 - ");
        experience2.add(exp21);
        List<String> skills2 = new ArrayList<String>();
        skills2.add("praca w korporacji");
        skills2.add("księgowość");
        User user2 = new User(2, true, "Agata", "Bukowska",
                "http://1.fwcdn.pl/p/13/19/1951319/359629.1.jpg",
                31, 2008, "BZ WBK", experience2, skills2);
        user2.setRating(4);
        user2.setTel("234567891");
        user2.setEmail("agata@wubek.pl");
        users.add(user2);

        List<UserExperience> experience3 = new ArrayList<UserExperience>();
        List<String> skills3 = new ArrayList<String>();
        skills3.add("własny biznes");
        skills3.add("organizacja juwenalii");
        skills3.add("sport");
        User user3 = new User(3, false, "Michał", "Walczal",
                "http://www.plan-aktor.com/zdjecia/rolnicki_michal_1_602.jpg",
                27, 2012, null, experience3, skills3);
        user3.setRating(0);
        user3.setTel("345678912");
        user3.setEmail("michal@buziaczek.pl");
        users.add(user3);

        List<UserExperience> experience4 = new ArrayList<UserExperience>();
        UserExperience exp333 = new UserExperience("D Love", "1974 ");
        experience4.add(exp333);
        List<String> skills4 = new ArrayList<String>();
        skills4.add("kariera");
        skills4.add("android");
        skills4.add("sport");

        User user4 = new User(4, true, "Roman", "Staszczyk",
                "http://cdn23.se.smcloud.net/t/pics/thumbnails/2010/02/02/MUNIEK_WT_07_640x480.jpg",
                40, 2012, null, experience4, skills4);
        user4.setRating(5);
        user4.setTel("345678912");
        user4.setEmail("muniek@staszczyk.pl");
        users.add(user4);

        List<UserExperience> experience5 = new ArrayList<UserExperience>();
        UserExperience exp555 = new UserExperience("Super Studio Line", "2012 ");
        UserExperience exp556 = new UserExperience("ASP", "2011 ");
        experience5.add(exp555);
        experience5.add(exp556);
        List<String> skills5 = new ArrayList<String>();
        skills5.add("praktyki");
        skills5.add("game design");
        skills5.add("pixel art");
        skills5.add("android");
        skills5.add("własny biznes");

        User user5 = new User(5, false, "Zuzia", "Głowacka",
                "http://beautifulskin.tychy.pl/wp-content/uploads/2012/04/TWARZ.jpg",
                21, 2012, null, experience5, skills5);
        user5.setRating(3);
        user5.setTel("345678913212");
        user5.setEmail("zuzia@buziaczek.pl");
        users.add(user5);

        tags = new ArrayList<String>();

        tags.add("własny biznes");
        tags.add("kariera");
        tags.add("pomoc w założeniu firmy");
        tags.add("praca w korporacji");
        tags.add("księgowość");
        tags.add("własny biznes");
        tags.add("organizacja juwenalii");
        tags.add("sport");
        tags.add("analiza biznesowa");
        tags.add("samozatrudnienie");
        tags.add("programowanie");
        tags.add("pomoc w projekcie");
        tags.add("organizacja warsztatów");
        tags.add("praktyki");
        tags.add("organizacja konferencji");
        tags.add("praca w banku");
        tags.add("bankowość");
        tags.add("game design");
        tags.add("pixel art");

        Collections.sort(tags);
    }

    public static void addConnected(int id) {
        connectedIds.add(id);
    }

    public static boolean isConnected(int id) {
        for (Integer cId : connectedIds) {
            if (cId != null && id == cId.intValue()) {
                return true;
            }
        }

        return false;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static List<User> getUsers(boolean angel, boolean student, List<String> skills, String name) {
        List<User> foundUsers = new ArrayList<User>();

        for (User user : getUsers()) {
            if (angel && student) {

            } else {
                if (angel != user.isAngel()) {
                    continue;
                }

                if (student != !user.isAngel() && !angel) {
                    continue;
                }
            }

            if (skills != null && skills.size() > 0) {
                boolean found = false;
                for (String skill : skills) {
                    for (String userSkill : user.getSkills()) {
                        if (skill.equalsIgnoreCase(userSkill)) {
                            foundUsers.add(user);
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        break;
                    }
                }
            } else {
                if (name == null || name.length() == 0) {
                    foundUsers.add(user);
                    continue;
                }
            }
            if (name != null &&
                    (user.getName().toLowerCase().contains(name.toLowerCase()) ||
                            user.getSurname().toLowerCase().contains(name.toLowerCase()))) {
                foundUsers.add(user);
                continue;
            }
        }

        return foundUsers;
    }

    public static User getUser(int id) {
        for (User user : getUsers()) {
            if (id == user.getId()) {
                return user;
            }
        }

        return null;
    }


    public static User addUser(User user) {
        if (user != null) {
            int maxId = 0;
            for (User user1 : getUsers()) {
                if (maxId <= user1.getId()) {
                    maxId = user1.getId() + 1;
                }
            }

            user.setId(maxId);
            users.add(user);
        }

        return user;
    }

    public static List<String> getTags() {
        return tags;
    }

    public static void addTag(String tag) {
        if (tag != null) {
            getTags().add(tag);
        }
    }

    public static void addTags(List<String> newTags) {
        if (newTags != null) {
            getTags().addAll(newTags);
        }
    }
}
