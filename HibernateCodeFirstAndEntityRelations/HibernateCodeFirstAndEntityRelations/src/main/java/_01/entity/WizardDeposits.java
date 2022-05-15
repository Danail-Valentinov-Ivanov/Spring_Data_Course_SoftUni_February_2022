package _01.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "wizard_deposits")
public class WizardDeposits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 60, nullable = false)
    private String lastName;

    @Column(length = 1000)
    private String notes;

    private int age;

    @Column(name = "magic_wand_creator", length = 100)
    private String magicWandCreator;

    @Column(name = "magic_wand_size")
    private short MagicWandSize;

    @Column(name = "deposit_group", length = 20)
    private String depositGroup;

    @Column(name = "deposit_start_date", nullable = false)
    private LocalDateTime depositStartDate;

    @Column(name = "deposit_amount", nullable = false)
    private BigDecimal depositAmount;

    @Column(name = "deposit_interest", nullable = false)
    private BigDecimal depositInterest;

    @Column(name = "deposit_charge", nullable = false)
    private BigDecimal depositCharge;

    @Column(name = "deposit_expiration_date", nullable = false)
    private LocalDateTime depositExpirationDate;

    @Column(name = "is_deposit_expired")
    private boolean depositExpired;

    public WizardDeposits() {
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNotes() {
        return notes;
    }

    public int getAge() {
        return age;
    }

    public String getMagicWandCreator() {
        return magicWandCreator;
    }

    public short getMagicWandSize() {
        return MagicWandSize;
    }

    public String getDepositGroup() {
        return depositGroup;
    }

    public LocalDateTime getDepositStartDate() {
        return depositStartDate;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public BigDecimal getDepositInterest() {
        return depositInterest;
    }

    public BigDecimal getDepositCharge() {
        return depositCharge;
    }

    public LocalDateTime getDepositExpirationDate() {
        return depositExpirationDate;
    }

    public boolean isDepositExpired() {
        return depositExpired;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMagicWandCreator(String magicWandCreator) {
        this.magicWandCreator = magicWandCreator;
    }

    public void setMagicWandSize(short magicWandSize) {
        MagicWandSize = magicWandSize;
    }

    public void setDepositGroup(String depositGroup) {
        this.depositGroup = depositGroup;
    }

    public void setDepositStartDate(LocalDateTime depositStartDate) {
        this.depositStartDate = depositStartDate;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public void setDepositInterest(BigDecimal depositInterest) {
        this.depositInterest = depositInterest;
    }

    public void setDepositCharge(BigDecimal depositCharge) {
        this.depositCharge = depositCharge;
    }

    public void setDepositExpirationDate(LocalDateTime depositExpirationDate) {
        this.depositExpirationDate = depositExpirationDate;
    }

    public void setDepositExpired(boolean depositExpired) {
        this.depositExpired = depositExpired;
    }
}
