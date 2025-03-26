package per.llt.account.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", unique = true, nullable = false)
    private Long customerId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;


    /**
     * 🔗 One-to-Many relationship with Account 🔗
     **/
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Account> accounts;


//    @OneToMany(L)targetEntity = Account.class,cascade = CascadeType.AL
//    @JoinColumn(name ="foreignKeyCustomer",referencedColumnName = "customer_id")
//    private List<Account> accounts;
}
