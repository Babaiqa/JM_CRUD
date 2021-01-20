package MyCRUDApp.model;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "Name", nullable = false)
    private String name;
    @Column(name = "Surname", nullable = false)
    private String surname;
    @Column (name = "Profession", nullable = false)
    private String profession;

    public User() {

    }

    public User(Long id, String name, String surname, String profession) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.profession = profession;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return getName() + getSurname() + "is a" + getProfession();
    }
}
