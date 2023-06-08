package ru.kata.spring.boot_security.demo.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Поле не должно быть пустым")
    @Column(name = "first_name")
    private String userName;
    @NotEmpty(message = "Поле не должно быть пустым")
    @Column(name = "last_name")
    private String lastName;
    @Min(value = 12,message = "Мы не можем предоставить доступ к порталу, пользователям не достигшим 12 лет")
    @Max(value = 110,message = "Философский камень был уничтожен, Вам не может быть больше 110 лет, если Вы - не Альбус Дамблдор")
    private int age;
    @NotEmpty(message = "Поле не должно быть пустым")
    private String password;
    @NotEmpty(message = "Поле не должно быть пустым")
    @Email(message = "Убедитесь, что почта записана правильно")
    private String email;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN) //ленивая загрузка
    private List<Role> roles;

    public User() {
    }

    public User(String userName, String lastName, int age, String password, String email,List<Role> roles) {
        this.userName = userName;
        this.lastName = lastName;
        this.age = age;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() { //аккаунт не просрочен
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //аккаунт не заблокирован
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { //пароль не просрочен
        return true;
    }

    @Override
    public boolean isEnabled() { //аккаунт включён, работает
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + userName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}