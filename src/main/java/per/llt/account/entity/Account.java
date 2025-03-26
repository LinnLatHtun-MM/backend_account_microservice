package per.llt.account.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account extends BaseEntity {

    @Id
    @Column(name="account_number",unique = true,nullable = false)
    private Long accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    // many account can have one customer and use fetch type to lazy to avoid EAGER loading and to get good performance
    @JoinColumn(name = "customer_id",nullable=false)
    private Customer customer;
}
