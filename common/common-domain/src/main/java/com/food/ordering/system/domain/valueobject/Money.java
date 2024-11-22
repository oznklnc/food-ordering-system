package com.food.ordering.system.domain.valueobject;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

public class Money {

    public static final Money ZERO = new Money(BigDecimal.ZERO);
    private final BigDecimal amount;

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isGreaterThanZero() {
        return Optional.ofNullable(this.amount)
                .map(value -> value.compareTo(BigDecimal.ZERO) > 0)
                .orElse(false);
    }

    public boolean isGreaterThan(Money money) {
        return Optional.ofNullable(this.amount)
                .map(value -> value.compareTo(money.getAmount()) > 0)
                .orElse(false);
    }

    public Money add(Money money) {
        return new Money(setScale(this.amount.add(money.getAmount())));
    }

    public Money subtract(Money money) {
        return new Money(setScale(this.amount.subtract(money.getAmount())));
    }

    public Money multiply(int multiplier) {
        return new Money(setScale(this.amount.multiply(BigDecimal.valueOf(multiplier))));
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }

    private BigDecimal setScale(BigDecimal value) {
        return value.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
}
