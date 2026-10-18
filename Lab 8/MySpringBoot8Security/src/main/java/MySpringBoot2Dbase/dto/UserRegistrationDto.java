package MySpringBoot2Dbase.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegistrationDto {
    @NotBlank(message = "Введите имя")
    @Size(max = 80)
    private String firstName;

    @NotBlank(message = "Введите фамилию")
    @Size(max = 80)
    private String lastName;

    @NotBlank(message = "Введите электронную почту")
    @Email(message = "Введите корректный адрес электронной почты")
    @Size(max = 160)
    private String email;

    @NotBlank(message = "Введите пароль")
    @Size(min = 6, max = 100, message = "Пароль должен содержать от 6 до 100 символов")
    private String password;

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
