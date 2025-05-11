package skillfactory.DreamTeam.globus.it.dao.entities.profiles;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import skillfactory.DreamTeam.globus.it.dao.entities.GeneralEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.bank.BankAccountEntity;

@Getter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profiles")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ProfileEntity extends GeneralEntity {

    protected String name;

    @Setter
    @Size(min = 10, max = 12)
    private String inn;

    @Setter
    @Email
    private String email;

    private String phone;

    @OneToOne(mappedBy = "holder")
    private BankAccountEntity bankAccount;

    public void setPhone(String phone) {
        this.phone = formatPhone(phone);
    }

    public static String formatPhone(String phone) {
        String digits = phone.replaceAll("[^0-9]", "");
        if (digits.matches("^[78]\\d{10}$")) {
            return digits.replaceFirst(
                    "(\\d)(\\d{3})(\\d{3})(\\d{2})(\\d{2})",
                    "+$1($2)$3-$4-$5"
            );
        } else if (digits.matches("^\\d{10}$")) {
            return digits.replaceFirst(
                    "(\\d{3})(\\d{3})(\\d{2})(\\d{2})",
                    "+7($1)$2-$3-$4"
            );
        } else {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }
}
