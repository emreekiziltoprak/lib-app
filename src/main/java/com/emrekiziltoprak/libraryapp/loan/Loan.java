package com.emrekiziltoprak.libraryapp.loan;

import com.emrekiziltoprak.libraryapp.book.Book;
import com.emrekiziltoprak.libraryapp.member.Member;
import lombok.Getter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;


@Getter
public class Loan {

    private static final int LOAN_PERIOD_DAYS = 14;
    private static final int MAX_RENEWALS = 1;
    private static final int MAX_EXTENSION_DAYS = 30;

    private final Book book;
   private final Member member;
   private final LocalDate borrowedAt;
   private LocalDate dueDate;
   private LocalDate returnedAt;
   private int renewCount;
   private final UUID id;

    public Loan(Book book, Member member, LocalDate borrowedAt) {
        this.book = Objects.requireNonNull(book, "book must not be null");
        this.member = Objects.requireNonNull(member, "member must not be null");
        this.borrowedAt = Objects.requireNonNull(borrowedAt, "borrowedat mustnt be null");
        this.dueDate = borrowedAt.plusDays(LOAN_PERIOD_DAYS);
        this.id = UUID.randomUUID();
        this.renewCount = 0;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "book=" + book +
                ", member=" + member +
                ", borrowedAt=" + borrowedAt +
                ", returnDate=" + dueDate +
                '}';
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Loan l)) return false;
        return this.id.equals(l.id);
    }

    public void returnBook (LocalDate dateTime) {

        Objects.requireNonNull(dateTime, "return date is missing");

        if(isActive() && (this.borrowedAt.isBefore(dateTime) ||this.borrowedAt.isEqual(dateTime))) {
            this.returnedAt = dateTime;
            return;
        }
        if(isActive() && this.borrowedAt.isAfter(dateTime)) {
            throw new IllegalArgumentException("returnDate cant be before than borrowedDate");
        }
        else if(!isActive())
            throw new IllegalStateException("already returned");


    }


    public void extendDate (LocalDate today, LocalDate dateTime) {

        if(!isActive()){
            throw new IllegalStateException("returned book cannot be extended");
        }
        else if(isOverdue(today)) {
            throw new IllegalStateException("overdue loan cannot be extended");
        }
        else if(renewCount==1) {
               throw new IllegalStateException("loan can only be extended at once");
        }
        else if(renewCount==0) {

            if(dateTime.equals(dueDate)) {
                throw new IllegalArgumentException("extendDate should be bigger than dueDate");
            }
            if(dateTime.isAfter(this.dueDate.plusDays(MAX_EXTENSION_DAYS))) {
                throw new IllegalArgumentException("extendDate should be less bigger than %d days".formatted(MAX_EXTENSION_DAYS));
            }
            if(dateTime.isBefore(this.dueDate)){
             throw new IllegalArgumentException("extendDate should be later than dueDate");
            }
            if(dateTime.isAfter(this.dueDate)){
                 this.dueDate = dateTime;
                 renewCount += 1;
             }
        }

     }

    public boolean isOverdue(LocalDate dateTime) {
        Objects.requireNonNull(dateTime, "date is required");

        if(!isActive())
        { return false; }
        else return this.dueDate.isBefore(dateTime);
    }

    public int overdueDays (LocalDate today) {
        Objects.requireNonNull(today, "date is required");
        LocalDate date = isActive() ? today : returnedAt;
        int overdays = Math.toIntExact(ChronoUnit.DAYS.between(dueDate, date));

        return Math.max(0,overdays);

    }

    public boolean isActive() {
        return returnedAt == null;
    }

}
